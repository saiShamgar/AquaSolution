package com.PG.testingapp.UI;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.hardware.usb.UsbRequest;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.PG.testingapp.Adapters.MenuItemsAdapter;
import com.PG.testingapp.Adapters.ValueEditionDetailsAdapter;
import com.PG.testingapp.LoginActivity;
import com.PG.testingapp.R;
import com.PG.testingapp.Utils.AppUtils;
import com.PG.testingapp.Utils.GpsLocation;
import com.PG.testingapp.Utils.SharedPreferenceConfig;
import com.PG.testingapp.Utils.UsbDriver;
import com.PG.testingapp.model.Events;
import com.PG.testingapp.model.ValueEditionDetaillsModel;
import com.felhr.usbserial.UsbSerialDevice;
import com.felhr.usbserial.UsbSerialInterface;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static android.hardware.usb.UsbManager.EXTRA_PERMISSION_GRANTED;

public class MenuActivity extends AppCompatActivity implements  GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{
    private static final int REQUEST_CODE = 123;
    private Context mContext;
    private GoogleApiClient googleApiClient = null;
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;
    private RecyclerView recyclerview_in_grid;
    private MenuItemsAdapter adapter;

    private ImageView logout;
    private SharedPreferenceConfig config;

    boolean doubleBackToExitPressedOnce = false;

    private static UsbDriver Usb_Driver_class;

    private static final String ACTION_USB_ATTACHED  = "android.hardware.usb.action.USB_DEVICE_ATTACHED";
    private static final String ACTION_USB_DETACHED  = "android.hardware.usb.action.USB_DEVICE_DETACHED";
    private static final String ACTION_USB_PERMISSION  = "com.blecentral.USB_PERMISSION";
    private BroadcastReceiver broadcastReceiver;
    private int VID,PID;

    private byte[] bytes;
    private static int TIMEOUT = 0;
    private boolean forceClaim = true;


    /*
     * Initiate a control transfer to request the first configuration
     * descriptor of the device.
     */
    //Type: Indicates whether this is a read or write
    // Matches USB_ENDPOINT_DIR_MASK for either IN or OUT
    private static final int REQUEST_TYPE = 0x80;
    //Request: GET_CONFIGURATION_DESCRIPTOR = 0x06
    private static final int REQUEST = 0x06;
    //Value: Descriptor Type (High) and Index (Low)
    // Configuration Descriptor = 0x2
    // Index = 0x0 (First configuration)
    private static final int REQ_VALUE = 0x200;
    private static final int REQ_INDEX = 0x00;
    private static final int LENGTH = 64;
    private String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mContext = MenuActivity.this;

        config=new SharedPreferenceConfig(this);

        checkLocation();
        init();


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.showCustomOkCancelDialog(mContext, "", getString(R.string.logout), "YES", "NO",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                config.writeLoginPreference("");
                                config.writeLoginEmpId("");
                               Intent logout=new Intent(MenuActivity.this, LoginActivity.class);
                               logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                               startActivity(logout);
                               finish();

                            }
                        },
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
            }
        });
    }

  

    void init() {
        enableGps();

        startService(new Intent(mContext, GpsLocation.class));
        recyclerview_in_grid=findViewById(R.id.recyclerview_in_grid);

        logout=findViewById(R.id.logout);
        adapter=new MenuItemsAdapter(this);
        recyclerview_in_grid.setHasFixedSize(true);
        recyclerview_in_grid.setLayoutManager(new GridLayoutManager(this,3));
        recyclerview_in_grid.setAdapter(adapter);

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void enableGps() {
        googleApiClient = new GoogleApiClient.Builder(mContext)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true); //this is the key ingredient


        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                final LocationSettingsStates state = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can initialize location
                        // requests here.

                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(MenuActivity.this, REQUEST_CHECK_SETTINGS);

                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.

                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.

                        break;
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 2) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                enableGps();
            } else {
                checkLocation();
            }
        }
    }

    void checkLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE}, 2);
            }

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
// Check for the integer request code originally supplied to startResolutionForResult().
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        checkLocation();
                        Log.d("myLocation", "onActivityResult");
                        break;
                    case Activity.RESULT_CANCELED:
                        AppUtils.showToast(mContext, "Please turn on location");
                        enableGps();
                        break;
                }
                break;
        }
    }

    void startSerialConnection(UsbManager usbManager, UsbDevice device) {
        UsbInterface intf = device.getInterface(0);
        UsbDeviceConnection connection = usbManager.openDevice(device);
        connection.claimInterface(device.getInterface(0), true);

        new Thread(new Runnable() {

            @TargetApi(Build.VERSION_CODES.KITKAT)
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            @Override
            public void run() {

                UsbEndpoint endpoint = null;

                for (int i = 0; i < intf.getEndpointCount(); i++) {
                    if (intf.getEndpoint(i).getDirection() == UsbConstants.USB_DIR_OUT) {
                        endpoint = intf.getEndpoint(i);
                        break;
                    }
                }


                byte[] buffer1 = new byte[200];

                connection.bulkTransfer(endpoint, buffer1,  LENGTH, 3000);


                UsbRequest request = new UsbRequest(); // create an URB
                boolean initilzed = request.initialize(connection, endpoint);

                if (!initilzed) {
                    Log.e("USB CONNECTION FAILED", "Request initialization failed for reading");
                    return;
                }
                while (true) {
                    int bufferMaxLength = endpoint.getMaxPacketSize();
                    Charset charset=Charset.forName("UTF-8");
                    ByteBuffer buffer = ByteBuffer.allocate(bufferMaxLength);
                    if (request.queue(buffer, bufferMaxLength)) {
                        if (connection.requestWait().equals(request)) {

//                            byte[] bytes=new byte[buffer.remaining()];
//                                buffer.get(bytes);
                               result =new String(buffer.array(), 0, buffer.position());
                            Log.e("Weight data  ", result);

                        }
                    }
                }

            }
        }).start();

    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        broadcastReceiver=new BroadcastReceiver() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equalsIgnoreCase(ACTION_USB_DETACHED)) {
                    Toast.makeText(mContext,"usb Dettached",Toast.LENGTH_LONG).show();
                }else if (action.equalsIgnoreCase(ACTION_USB_ATTACHED)) {
                    checkUSB();
                    Toast.makeText(mContext,"usb Attached",Toast.LENGTH_LONG).show();


                }else if (action.equalsIgnoreCase(ACTION_USB_PERMISSION)) {
                    synchronized (this) {
                        UsbDevice device = (UsbDevice)intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                        if (intent.getBooleanExtra(EXTRA_PERMISSION_GRANTED, false)) {
                          //  AppUtils.showToast(mContext,"USB permission granted");
                            UsbManager usbManager = getSystemService(UsbManager.class);
                            Map<String, UsbDevice> connectedDevices = usbManager.getDeviceList();
                            for (UsbDevice device11 : connectedDevices.values()) {
                                if (device11.getVendorId() == VID && device11.getProductId() == PID) {
                                    Log.i("hsduhf", "Device found: " + device11.getDeviceName());
                                 //   Toast.makeText(getApplicationContext(),"count "+device11.getInterfaceCount(),Toast.LENGTH_LONG).show();
                                    startSerialConnection(usbManager, device11);
                                    break;
                                }
                            }
                        } else {
                            AppUtils.showToast(mContext,"USB permission not granted");
                            //Permission revoked by user
                        }
                    }
                }
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_USB_ATTACHED);
        filter.addAction(ACTION_USB_DETACHED);
        filter.addAction(ACTION_USB_PERMISSION);
        filter.addAction(EXTRA_PERMISSION_GRANTED);
        registerReceiver(broadcastReceiver,filter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        unregisterReceiver(broadcastReceiver);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void checkUSB(){
        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        // Get the list of attached devices
        HashMap<String, UsbDevice> devices = manager.getDeviceList();
        // Iterate over all devices
        Iterator<String> it = devices.keySet().iterator();
        while (it.hasNext()) {
            String deviceName = it.next();
            UsbDevice device = devices.get(deviceName);
           //  VID = String.valueOf(device.getVendorId());
           //  PID = String.valueOf(device.getProductId());
             PID = device.getProductId();
             VID = device.getVendorId();


           // AppUtils.showToast(mContext,PID+" "+VID);


           // String RESULT = Integer.toHexString(device.getInterface(0)).toUpperCase();
            if (!manager.hasPermission(device)) {
                PendingIntent mPermissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);
                manager.requestPermission(device, mPermissionIntent);

            } else {




                //user permission already granted; prceed to access USB device
            }
        }
    }

    @Subscribe
    public void getMessage(Events.ActivityServiceMessage message){
        Log.e("EventMessage",message.getLatitude()+" Time "+message.getTime());
        Log.e("EventMessage",message.getAddress());
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
