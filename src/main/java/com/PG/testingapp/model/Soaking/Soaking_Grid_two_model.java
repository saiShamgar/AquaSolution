package com.PG.testingapp.model.Soaking;

import java.io.Serializable;

public class Soaking_Grid_two_model implements Serializable {

    String date;
    int tub_no;
    int no_of_nets;
    float total_weight;
    float total_tare_weight;
    float net_weight;
    float net_tare_weight;
    String Grade;

    public Soaking_Grid_two_model() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTub_no() {
        return tub_no;
    }

    public void setTub_no(int tub_no) {
        this.tub_no = tub_no;
    }

    public int getNo_of_nets() {
        return no_of_nets;
    }

    public void setNo_of_nets(int no_of_nets) {
        this.no_of_nets = no_of_nets;
    }

    public float getTotal_weight() {
        return total_weight;
    }

    public void setTotal_weight(float total_weight) {
        this.total_weight = total_weight;
    }

    public float getTotal_tare_weight() {
        return total_tare_weight;
    }

    public void setTotal_tare_weight(float total_tare_weight) {
        this.total_tare_weight = total_tare_weight;
    }

    public float getNet_weight() {
        return net_weight;
    }

    public void setNet_weight(float net_weight) {
        this.net_weight = net_weight;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public float getNet_tare_weight() {
        return net_tare_weight;
    }

    public void setNet_tare_weight(float net_tare_weight) {
        this.net_tare_weight = net_tare_weight;
    }
}
