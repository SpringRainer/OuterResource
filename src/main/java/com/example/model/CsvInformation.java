package com.example.model;

import java.sql.Timestamp;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2022/9/3 17:14
 */
public class CsvInformation {
    private Integer ID;
    private String Title;
    private Timestamp Create_Date;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Timestamp getCreate_Date() {
        return Create_Date;
    }

    public void setCreate_Date(Timestamp create_Date) {
        Create_Date = create_Date;
    }

    @Override
    public String toString() {
        return "CsvInformation{" +
                "ID=" + ID +
                ", Title='" + Title + '\'' +
                ", Create_Date=" + Create_Date +
                '}'+'\n';
    }
}
