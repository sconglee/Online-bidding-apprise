package com.avic.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avic.common.constant.BidConstant;
import com.avic.common.utils.PDFUtil;
import com.avic.model.ExpertScoreSheet;
import com.avic.model.httovo.ExpertScoreSheetInsert;
import com.avic.model.httovo.ExpertScoreSheetPagination;
import com.avic.service.ExpertScoreSheetService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 操作PDF
 */
@Controller
@RequestMapping("/pdf")
public class PDFController {
    private final static Log logger = LogFactory.getLog(PDFController.class);

    @Autowired
    private ExpertScoreSheetService expertScoreSheetService;

    /**
    * @Author xulei
    * @Description 利用模板生成pdf
    * @Date 15:09 2019/11/16/016
    * @Param 项目名称、项目编号、专家名称
    * @return void
    **/
    @RequestMapping("/download")
    @ResponseBody
    public Map<String, Object> download(@RequestBody ExpertScoreSheetPagination expertScoreSheetPagination) {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("success", "true");
        modelMap.put("msg", "生成pdf成功！！");

        // 存放生成的所有pdf的地址
        ArrayList<String> pdfPathList = new ArrayList<>();

        try {
            // 1、获取数据
            // 1.1、查询expertScoreSheet，获取数据
            List<ExpertScoreSheet> expertScoreSheetList = expertScoreSheetService.getExpertScoreSheetList(expertScoreSheetPagination);
            if (expertScoreSheetList.size() == 0) {
                modelMap.put("success", "false");
                modelMap.put("msg", "不存在对应的评标打分结果数据，请先打分再执行下载pdf操作！");
                return modelMap;
            }
            // 1.2、组装数据格式
            ExpertScoreSheetInsert resultData = expertScoreSheetService.getExpertScoreSheetInsertToWeb(expertScoreSheetList);

            // 2、把数据写入到pdf中，生成最终pdf
            int comAndPointListLength = resultData.getExpertScoreSheetComAndPointList().size();
            int createPDFTimes = (comAndPointListLength % BidConstant.companyNumberSinglePDF) == 0 ?
                    comAndPointListLength / BidConstant.companyNumberSinglePDF : comAndPointListLength / BidConstant.companyNumberSinglePDF + 1;

            for (int i = 0; i < createPDFTimes; i++) {
                System.out.println("这是第 " + i + " 次开始生成pdf:");
                ExpertScoreSheetInsert createPDFData = expertScoreSheetService.getExpertScoreSheetDataForCreatePDF(resultData,i);
                System.out.println(createPDFData.toString());

                // 定义pdf文件名称
                String newPdfFile = "";
                String osName = System.getProperty("os.name");
                if (osName.toLowerCase().startsWith("win")) {
                    newPdfFile = BidConstant.constantPrePathForWin + createPDFData.getProjectName() + "_" + i + "" + BidConstant.constantSufPath;
                } else {
                    newPdfFile = BidConstant.constantPrePathForLinux + createPDFData.getProjectName() + "_" + i + "" + BidConstant.constantSufPath;
                }
                // 把当前pdf的路径放到pdfPathList中给前端下载pdf使用
                pdfPathList.add(newPdfFile);

                // 3、生成pdf文件
                File file = new File(newPdfFile);
                file.createNewFile();

                // 向pdf文件中写入数据
                PDFUtil pdfUtil = new PDFUtil(file);
                pdfUtil.generatePDF(createPDFData);
            }
            modelMap.put("data", pdfPathList);

        } catch (Exception e) {
            System.out.println("生成pdf异常，信息为：" + e.getMessage());
            modelMap.put("success", "false");
            modelMap.put("msg", "生成pdf失败！！");
        }

        return modelMap;
    }


}
