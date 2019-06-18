package com.PG.testingapp.InterFace;

import com.PG.testingapp.model.FactoryWeighment.FTLotNumbers;
import com.PG.testingapp.model.Processes_data;
import com.PG.testingapp.model.ValueEdition.LotNoDetails_VD;

public interface OnRadioButtonClick {
    void onRadioClick(LotNoDetails_VD processes_data);

    void onRadioClickFT(FTLotNumbers processes_data);

}
