package com.avic.controller;

import com.avic.common.utils.CountFinalScore;
import com.avic.common.utils.TimeUtil;
import com.avic.model.ExpertScoreSheet;
import com.avic.model.FinalScoreSheet;
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


    @RequestMapping("")
    @ResponseBody
    public Map<String, Object> getAllProjectInfo() {
        Map map = new HashMap();
        List<FinalScoreSheet> finalScoreSheetList = finalScoreSheetService.getAllProject();
        map.put("projectInfo", finalScoreSheetList);
        map.put("msg", "查询成功！");
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
        finalScoreSheet1.setTotalScore((Integer) mapScore.get("totalScore"));
        finalScoreSheet1.setIsGenerate(1);
        finalScoreSheet1.setUpdateTime(TimeUtil.getTimeByDefautFormat());

        int isUpdate = finalScoreSheetService.updateFinalScoreSheet(finalScoreSheet1);
        if (isUpdate == 1) {
            map.put("msg", "得分表生成成功！");
        } else {
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

            FinalScoreSheet finalScoreSheet1 = finalScoreSheetService.getFinalScoreSheetByProjectNumberAndCompanyName(finalScoreSheet);

            map.put("totalItems", totalItems);
            map.put("itemWeight", itemWeight);
            map.put("pointList", pointList);
            map.put("finalScoreSheet", finalScoreSheet1);
            map.put("msg", "查询得分表详情成功！");
        } else {
            map.put("msg", "查询得分表详情失败！");
        }
        return map;

    }


}
