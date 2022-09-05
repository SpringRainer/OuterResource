package com.example.controller;

import com.example.model.CsvProcessModel;
import com.example.model.CsvRealTimePercentModel;
import com.example.service.ChangeFileFormatUtils;
import com.example.service.CsvInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2022/9/3 16:51
 */

@Controller
public class CsvController {
    private CsvInsertService csvInsertService;

    private CsvRealTimePercentModel csvRealTimePercentModel = null;

    public CsvInsertService getCsvInsertService() {
        return csvInsertService;
    }

    @Autowired
    public void setCsvInsertService(CsvInsertService csvInsertService) {
        this.csvInsertService = csvInsertService;
    }

    @RequestMapping("/transformProcess")
    public String csvPage() {
        return "csvProcessPage";
    }

    @PostMapping("/uploadCsvFile")
    @ResponseBody
    public CsvProcessModel uploadCsvFileAndDoInsert(@RequestParam("file") MultipartFile multipartFile, @RequestParam("fileName") String filename) throws IOException, ParseException, InterruptedException {
        System.out.println("调用上传api");

        multipartFile.transferTo(Paths.get("D:\\csvFiles\\".concat(filename)));
        int total = getCsvInsertService()
                .getTotalRow("D:\\csvFiles\\modify\\".concat(filename));

        CsvProcessModel csvProcessModel = new CsvProcessModel();
        csvProcessModel.setFilename(filename);
        csvProcessModel.setTotal(total);
        return csvProcessModel;
    }

    // 获取实时处理进度
    @PostMapping("/getRealTimeProcess")
    @ResponseBody
    public CsvRealTimePercentModel getRealTimeProcess(@RequestParam("filename") String filename
            , @RequestParam("total") int total, HttpServletRequest request) {
        csvRealTimePercentModel = new CsvRealTimePercentModel();
        Integer currentPercent = getCsvInsertService().getCurrentRowProcess(total);
        csvRealTimePercentModel.setFilename(filename);
        csvRealTimePercentModel.setCurrentPercent(currentPercent);
        return csvRealTimePercentModel;
    }

    @PostMapping("/startTransform")
    @ResponseBody
    public String startTransform(@RequestParam("fileName") String filename, @RequestParam("num") int num) throws IOException, InterruptedException {
        File file = new File("D:\\csvFiles\\".concat(filename));

        // 解析文件之前先进行整理
        ChangeFileFormatUtils.repair(file);

        File file1 = new File("D:\\csvFiles\\modify\\".concat(filename));
        getCsvInsertService().insertNRows(num, file1);
        return "success";
    }
}