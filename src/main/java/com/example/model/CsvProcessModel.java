package com.example.model;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2022/9/3 22:25
 */
public class CsvProcessModel {
    private String filename;
    private int total;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CsvProcessModel{" +
                "filename='" + filename + '\'' +
                ", total=" + total +
                '}';
    }
}
