package com.example.service;

import com.example.mapper.CsvTransformMapper;
import com.example.model.CsvInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2022/9/3 17:48
 */
@Service
@Transactional
public class CsvInsertService {

    private CsvTransformMapper csvTransformMapper;

    private static int currentRow = 0;

    public CsvInsertService() {
        System.out.println("构造方法执行");
    }


    @Autowired
    public void setCsvTransformMapper(CsvTransformMapper csvTransformMapper) {
        this.csvTransformMapper = csvTransformMapper;
    }

    public CsvTransformMapper getCsvTransformMapper() {
        return csvTransformMapper;
    }

    public void insertNRows(int num, File file) throws IOException {
        long start = System.currentTimeMillis();
        InputStream inputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        List<CsvInformation> list = new LinkedList<>();
        CsvInformation csvInformation = null;
        String line;
        String[] split;
        while ((line = bufferedReader.readLine()) != null) {
            split = line.split(",");
            currentRow++;
            csvInformation = new CsvInformation();
            csvInformation.setTitle(split[1]);
            csvInformation.setCreate_Date(Timestamp.valueOf(split[2].concat(":00").replaceAll("/", "-")));
            list.add(csvInformation);
            if (currentRow % num == 0) {
                getCsvTransformMapper().insertInformation(list);
                list.clear();
            }

        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        System.out.println("单次批量插入条数 :"+num+" 耗时: "+(end - start)+ "毫秒");
    }

    public int getTotalRow(String path) throws IOException {
        InputStream inputStream = new FileInputStream(path);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        int total = 0;
        while (bufferedReader.readLine() != null) {
            total++;
        }
        return total;
    }

    // 实时获取进度百分比数
    public int getCurrentRowProcess(int total) {
        int process = currentRow*100/total;
        if (currentRow >= total) {
            currentRow = 0;
        }
        System.out.println(process);
        return process;
    }
}
