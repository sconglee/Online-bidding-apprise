package com.avic.controller;

import com.avic.common.constant.BidConstant;
import com.avic.common.utils.PDFUtil;
import com.avic.common.utils.ZipUtil;
import com.avic.model.ExpertScoreSheet;
import com.avic.model.httovo.ExpertScoreSheetInsert;
import com.avic.model.httovo.ExpertScoreSheetPagination;
import com.avic.service.ExpertScoreSheetService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


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
    public synchronized Map<String, Object> download(@RequestBody ExpertScoreSheetPagination expertScoreSheetPagination) throws InterruptedException{
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("success", "true");
        modelMap.put("msg", "生成pdf成功！！");

        // 存放生成的所有pdf的地址
        ArrayList<String> pdfPathList = new ArrayList<>();
        String osName = System.getProperty("os.name");

        // 获取当前登录专家名称
        String loginUsername = expertScoreSheetPagination.getExpertName();

        // 保存项目名称 ，用于zip文件命名
        String projectNameForZip = null;

        try {
            // 1、获取数据
            // 1.1、查询expertScoreSheet，获取数据
            List<ExpertScoreSheet> expertScoreSheetList = expertScoreSheetService.getExpertScoreSheetList(expertScoreSheetPagination);
            if (expertScoreSheetList.size() == 0) {
                modelMap.put("success", "false");
                modelMap.put("msg", "不存在对应的评标打分结果数据，请先打分再执行下载pdf操作！");
                return modelMap;
            }
            // 保存项目名称 ，用于zip文件命名
            projectNameForZip = expertScoreSheetList.get(0).getProjectName();

            // 1.2、组装数据格式
            ExpertScoreSheetInsert resultData = expertScoreSheetService.getExpertScoreSheetInsertToWeb(expertScoreSheetList);

            // 2、把数据写入到pdf中，生成最终pdf
            int comAndPointListLength = resultData.getExpertScoreSheetComAndPointList().size();
            int tempTimes = comAndPointListLength / BidConstant.companyNumberSinglePDF;
            int createPDFTimes = (comAndPointListLength % BidConstant.companyNumberSinglePDF) == 0 ? tempTimes : tempTimes + 1;
            logger.info("公司数量为：" + comAndPointListLength + "; 每3家进行分页，共需要 " + createPDFTimes + " 次生成pdf。");

            for (int i = 0; i < createPDFTimes; i++) {
                ExpertScoreSheetInsert createPDFData = expertScoreSheetService.getExpertScoreSheetDataForCreatePDF(resultData,i);
                // 定义pdf文件名称
                String newPdfFile;
                if (osName.toLowerCase().startsWith("win")) {
                    newPdfFile = BidConstant.constantPrePathForWin + createPDFData.getProjectName() + "_" + loginUsername + "_" + i + "" + BidConstant.constantSufPath;
                } else {
                    newPdfFile = BidConstant.constantPrePathForLinux + createPDFData.getProjectName() + "_" + loginUsername + "_" + i + "" + BidConstant.constantSufPath;
                }
                // 把当前pdf的路径放到pdfPathList中给前端下载pdf使用
                pdfPathList.add(newPdfFile);

                // 3、生成pdf文件
                File file = new File(newPdfFile);
                file.createNewFile();

                // 向pdf文件中写入数据
                PDFUtil pdfUtil = new PDFUtil(file);
                pdfUtil.generatePDF(createPDFData, loginUsername);
            }

        } catch (Exception e) {
            System.out.println("生成pdf异常，信息为：" + e.getMessage());
            modelMap.put("success", "false");
            modelMap.put("msg", "生成pdf失败！！");
        }

        logger.info("生成的文件路径为：" + pdfPathList.toString());

        // 3、把存放pdf的文件夹打包
        String zipFilePath;
        if (osName.toLowerCase().startsWith("win")) {
            // 获取zip文件路径
            String zipFileUrl = expertScoreSheetService.getSaveZipPath(BidConstant.constantPrePathForWinZip, projectNameForZip, loginUsername);

            zipFilePath = "http://192.168.1.1/WEB/"+ projectNameForZip + "_" + loginUsername + ".zip";
            //zipFilePath = "http://192.168.1.2/WEB/"+ projectNameForZip + "_" + loginUsername + ".zip";
            // zipFilePath = "http://192.168.1.3/WEB/"+ projectNameForZip + "_" + loginUsername + ".zip";
            // zipFilePath = "http://localhost:8080/WEB/" + projectNameForZip + "_" + loginUsername + ".zip";
            ZipUtil.createZip(BidConstant.constantPrePathForWin, zipFileUrl, true);


        } else {
            // zipFilePath = "http://192.168.1.71/WEB/"+ projectNameForZip + ".zip";
            zipFilePath = "http://36.110.92.44/WEB/"+ projectNameForZip + ".zip";
            ZipUtil.createZip(BidConstant.constantPrePathForLinux, BidConstant.constantPrePathForLinuxZip + projectNameForZip + "_" + loginUsername + ".zip",true);
        }
        modelMap.put("data", zipFilePath);

        return modelMap;
    }


}
