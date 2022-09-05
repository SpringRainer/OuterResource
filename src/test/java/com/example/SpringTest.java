package com.example;

import com.example.service.ChangeFileFormatUtils;
import com.example.service.CsvInsertService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2022/9/3 17:46
 */

@SpringBootTest(classes = CsvTransformMain.class)
@RunWith(SpringRunner.class)
public class SpringTest {
    private CsvInsertService csvInsertService;

    public CsvInsertService getCsvInsertService() {
        return csvInsertService;
    }

    @Autowired
    public void setCsvInsertService(CsvInsertService csvInsertService) {
        this.csvInsertService = csvInsertService;
    }

    @Test
    public void testCsv() throws IOException {
        File file = new File("D:\\csvFiles\\mysqlData.csv");
        File file1 = ChangeFileFormatUtils.repair(file);

        for (int i = 1; i < 4; i++) {
            getCsvInsertService().insertNRows(10000*i, file1);
        }
    }
}
