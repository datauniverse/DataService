package com.ab.in.gov.data.api;

/**
 * Created by abhil on 16-10-2016.
 */
public class Train {
    private String trainCode;
    private String trainName;

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public Train() {
    }

    public Train(String trainCode, String trainName) {
        this.trainCode = trainCode;
        this.trainName = trainName;
    }
}
