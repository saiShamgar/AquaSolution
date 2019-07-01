package com.PG.testingapp.model.Soaking;

import java.io.Serializable;

public class Soaking_Grid_two_model implements Serializable {

    String date;
    String tub_no;
    String no_of_nets;
    String total_weight;
    String total_tare_weight;
    String net_weight;
    String net_tare_weight;
    String Grade;

    public Soaking_Grid_two_model() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTub_no() {
        return tub_no;
    }

    public void setTub_no(String tub_no) {
        this.tub_no = tub_no;
    }

    public String getNo_of_nets() {
        return no_of_nets;
    }

    public void setNo_of_nets(String no_of_nets) {
        this.no_of_nets = no_of_nets;
    }

    public String getTotal_weight() {
        return total_weight;
    }

    public void setTotal_weight(String total_weight) {
        this.total_weight = total_weight;
    }

    public String getTotal_tare_weight() {
        return total_tare_weight;
    }

    public void setTotal_tare_weight(String total_tare_weight) {
        this.total_tare_weight = total_tare_weight;
    }

    public String getNet_weight() {
        return net_weight;
    }

    public void setNet_weight(String net_weight) {
        this.net_weight = net_weight;
    }

    public String getNet_tare_weight() {
        return net_tare_weight;
    }

    public void setNet_tare_weight(String net_tare_weight) {
        this.net_tare_weight = net_tare_weight;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }
}
