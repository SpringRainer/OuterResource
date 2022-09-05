package com.example.model;

import java.util.Arrays;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2022/9/4 15:01
 */
public class FileItem {
    private String filename;
    private String url;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "FileItem{" +
                "filename='" + filename + '\'' +
                ", url='" + url + '\'' +
                ", filecontent=" +
                '}'+'\n';
    }
}
