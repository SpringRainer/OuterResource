package com.example.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2022/9/4 15:05
 */

public class ChangeFileFormatUtils {
    public static File repair(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        File file1 = new File(file.getParent().concat("\\modify\\"));

        if (!file1.exists()) {
            file1.mkdirs();
        }

        File file2 = new File(file1.getPath().concat("\\").concat(file.getName()));
        if (!file2.exists()) {
            file2.createNewFile();
        }

        OutputStream outputStream = new FileOutputStream(file2);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        String tmp;
        while ((tmp = bufferedReader.readLine()) != null) {
            if ("ID,Title,Create Date".equals(tmp)) {
                continue;
            }

            if (tmp.split(",").length < 3) {
                bufferedWriter.write(tmp.trim().replace("\n", ""));
                if ((tmp = bufferedReader.readLine()) != null) {
                    bufferedWriter.write(tmp);
                }
                bufferedWriter.write("\n");
            } else {
                bufferedWriter.write(tmp.concat("\n"));
            }
        }
        if (file.delete()) {
            System.out.println("源文件已删除");
        }

        bufferedWriter.close();
        outputStreamWriter.close();
        outputStream.close();
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        return file2;
    }
}
