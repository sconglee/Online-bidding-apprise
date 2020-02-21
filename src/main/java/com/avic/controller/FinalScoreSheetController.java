package com.avic.controller;

import com.avic.common.utils.CountFinalScore;
import com.avic.common.utils.TimeUtil;
import com.avic.model.ExpertScoreSheet;
import com.avic.model.FinalScoreSheet;
import com.avic.model.httovo.PaginationRequest;
import com.avic.service.ExpertScoreSheetService;
import com.avic.service.FinalScoreSheetService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sconglee
 * @date 2019/10/25
 */
@Controller
@RequestMapping("/score")
public class FinalScoreSheetController {
    private final static Log logger = LogFactory.getLog(FinalScoreSheetController.class);

    @Autowired
    private FinalScoreSheetService finalScoreSheetService;
    @Autowired
    private ExpertScoreSheetService expertScoreSheetService;


    @RequestMapping("/getprojectinfo")
    @ResponseBody
    public Map<String, Object> getProjectInfo(@RequestBody PaginationRequest paginationRequest) {
        Map map = new HashMap();
        int whichPage = paginationRequest.getPage();
        int everyNumber = paginationRequest.getColumns();
        paginationRequest.setStartNumber((whichPage - 1) * everyNumber);
        int count = finalScoreSheetService.getProjectCount();

        List<Map<String, Object>> finalScoreSheetList = finalScoreSheetService.getProjectByPagination(paginationRequest);
        if (!finalScoreSheetList.isEmpty()) {
            map.put("page", paginationRequest.getPage());
            map.put("count", count);
            map.put("total", (int) Math.ceil((double) count / everyNumber));
            map.put("projectInfo", finalScoreSheetList);
            map.put("msg", "查询成功！");
            map.put("success", true);
        } else {
            map.put("msg", "没有查询到项目信息！");
            map.put("success", false);
        }

        return map;
    }

    @RequestMapping("/generate")
    @ResponseBody
    public Map<String, Object> generateFinalScore(@RequestBody FinalScoreSheet finalScoreSheet) {
        Map map = new HashMap();
        ExpertScoreSheet expertScoreSheet = new ExpertScoreSheet();
        expertScoreSheet.setProjectNumber(finalScoreSheet.getProjectNumber());
        expertScoreSheet.setCompanyName(finalScoreSheet.getCompanyName());

        List<ExpertScoreSheet> expertScoreSheetList = expertScoreSheetService.getScoreInfoByProjectNumberAndCompanyName(expertScoreSheet);
        List<String> pointList = new ArrayList<>();
        for (ExpertScoreSheet expertScoreSheet1 : expertScoreSheetList) {
            pointList.add(expertScoreSheet1.getPoint());
            if (expertScoreSheet1.getPoint() == null) {
                map.put("success", false);
                map.put("msg", "至少有一个专家未提交打分！");
                return map;
            }
        }
        logger.info(expertScoreSheetList.size());
        logger.info(expertScoreSheetList.get(0));
        //String itemWeight = expertScoreSheetList.get(0).getItemWeight();
        //Map mapScore = CountFinalScore.getItemAverageAndTotalScore(pointList, itemWeight);
        Map mapScore = CountFinalScore.getExpertAverageAndTotalScore(pointList);
        //Map mapScore = CountFinalScore.getItemAverageAndTotalScoreExceptFourExpert(pointList);

        //注意区分java7（false）、java8（"false"）
       if (mapScore.get("flag") == false) {
            map.put("success", false);
            map.put("msg", mapScore.get("msg"));
            return map;
        }

        List<String> finalScoreList = (List<String>) mapScore.get("finalScore");

        FinalScoreSheet finalScoreSheet1 = new FinalScoreSheet();
        finalScoreSheet1.setProjectNumber(finalScoreSheet.getProjectNumber());
        finalScoreSheet1.setCompanyName(finalScoreSheet.getCompanyName());
        finalScoreSheet1.setAverageScore(StringUtils.collectionToDelimitedString(finalScoreList,","));
        finalScoreSheet1.setTotalScore((Double) mapScore.get("totalScore"));
        finalScoreSheet1.setIsGenerate(1);
        finalScoreSheet1.setUpdateTime(TimeUtil.getTimeByDefautFormat());


        int isUpdate = finalScoreSheetService.updateFinalScoreSheet(finalScoreSheet1);
        if (isUpdate == 1) {
            map.put("success", true);
            map.put("msg", "得分表生成成功！");
        } else {
            map.put("success", false);
            map.put("msg", "得分表生成失败！");
        }
        return map;
    }

    @RequestMapping("/info")
    @ResponseBody
    public Map<String, Object> getFinalScoreInfo(@RequestBody FinalScoreSheet finalScoreSheet) {
        Map map = new HashMap();
        String projectNumber = finalScoreSheet.getProjectNumber();
        String companyName = finalScoreSheet.getCompanyName();

        if (projectNumber != null & companyName != null) {
            ExpertScoreSheet expertScoreSheet = new ExpertScoreSheet();
            expertScoreSheet.setProjectNumber(projectNumber);
            expertScoreSheet.setCompanyName(companyName);

            List<ExpertScoreSheet> expertScoreSheetList = expertScoreSheetService.getScoreInfoByProjectNumberAndCompanyName(expertScoreSheet);
            List<String> pointList = new ArrayList<>();
            List<String> expertNameList = new ArrayList<>();
            for (ExpertScoreSheet expertScoreSheet1 : expertScoreSheetList) {
                pointList.add(expertScoreSheet1.getPoint());
                expertNameList.add(expertScoreSheet1.getExpertName());
            }
            String itemWeight = expertScoreSheetList.get(0).getItemWeight();
            String totalItems = expertScoreSheetList.get(0).getTotalItems();
            String itemCount = expertScoreSheetList.get(0).getItemCount();
            String sequenceNumber = expertScoreSheetList.get(0).getSequenceNumber();
            String evaluIndexDesc = expertScoreSheetList.get(0).getEvaluIndexDesc();

            FinalScoreSheet finalScoreSheet1 = finalScoreSheetService.getFinalScoreSheetByProjectNumberAndCompanyName(finalScoreSheet);

            if (itemWeight != null & totalItems != null & finalScoreSheet1 != null) {
                map.put("totalItems", totalItems);
                map.put("evaluIndexDesc", evaluIndexDesc);
                map.put("itemWeight", itemWeight);
                map.put("itemCount", itemCount);
                map.put("sequenceNumber", sequenceNumber);
                map.put("expertNameList", expertNameList);
                map.put("pointList", pointList);
                map.put("finalScoreSheet", finalScoreSheet1);
                map.put("msg", "查询得分表详情成功！");
                map.put("success", true);
            } else {
                map.put("msg", "还没有生成得分表！");
                map.put("success", false);
            }
        } else {
            map.put("msg", "查询得分表详情失败！");
            map.put("success", false);
        }
        return map;

    }


}
