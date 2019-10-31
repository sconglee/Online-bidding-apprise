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
        }
        String itemWeight = expertScoreSheetList.get(0).getItemWeight();
        Map mapScore = CountFinalScore.getFinalScore(pointList, itemWeight);
        List<String> finalScoreList = (List<String>) mapScore.get("finalScore");

        FinalScoreSheet finalScoreSheet1 = new FinalScoreSheet();
        finalScoreSheet1.setProjectNumber(finalScoreSheet.getProjectNumber());
        finalScoreSheet1.setCompanyName(finalScoreSheet.getCompanyName());
        finalScoreSheet1.setAverageScore(StringUtils.collectionToDelimitedString(finalScoreList,","));
        finalScoreSheet1.setTotalScore((Float) mapScore.get("totalScore"));
        finalScoreSheet1.setIsGenerate(1);
        finalScoreSheet1.setUpdateTime(TimeUtil.getTimeByDefautFormat());
        logger.info(StringUtils.collectionToDelimitedString(finalScoreList,","));
        logger.info(((Float) mapScore.get("totalScore")));


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
            for (ExpertScoreSheet expertScoreSheet1 : expertScoreSheetList) {
                pointList.add(expertScoreSheet1.getPoint());
            }
            String itemWeight = expertScoreSheetList.get(0).getItemWeight();
            String totalItems = expertScoreSheetList.get(0).getTotalItems();
            String itemCount = expertScoreSheetList.get(0).getItemCount();
            String sequenceNumber = expertScoreSheetList.get(0).getSequenceNumber();

            FinalScoreSheet finalScoreSheet1 = finalScoreSheetService.getFinalScoreSheetByProjectNumberAndCompanyName(finalScoreSheet);

            if (itemWeight != null & totalItems != null & finalScoreSheet1 != null) {
                map.put("totalItems", totalItems);
                map.put("itemWeight", itemWeight);
                map.put("itemCount", itemCount);
                map.put("sequenceNumber", sequenceNumber);
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
