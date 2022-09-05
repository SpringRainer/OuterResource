package com.example.model;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2022/9/4 9:23
 */
public class CsvRealTimePercentModel {
    private String filename;
    private float currentPercent;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public float getCurrentPercent() {
        return currentPercent;
    }

    public void setCurrentPercent(float currentPercent) {
        this.currentPercent = currentPercent;
    }

    @Override
    public String toString() {
        return "CsvRealTimePercentModel{" +
                "filename='" + filename + '\'' +
                ", currentPercent=" + currentPercent +
                '}'+'\n';
    }
}
