package com.avic.controller;

import com.avic.common.utils.TimeUtil;
import com.avic.mapper.ScoreSheetTemplateMapper;
import com.avic.model.ExpertScoreSheet;
import com.avic.model.FinalScoreSheet;
import com.avic.model.ScoreSheetTemplate;
import com.avic.model.httovo.ExpertScoreSheetPagination;
import com.avic.model.httovo.PaginationRequest;
import com.avic.service.ExpertScoreSheetService;
import com.avic.service.FinalScoreSheetService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ExpertScoreSheetController
 * @Description 专家打分
 * @Author xulei
 * @Date 2019/10/18/018 16:29
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/expertScore")
public class ExpertScoreSheetController {
    private final static Log logger = LogFactory.getLog(ExpertScoreSheetController.class);

    @Autowired
    private ExpertScoreSheetService expertScoreSheetService;

    @Autowired
    private FinalScoreSheetService finalScoreSheetService;

    @Autowired
    private ScoreSheetTemplateMapper scoreSheetTemplateMapper;

    /**
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @Author xulei
     * @Description 新增专家打分记录
     * @Date 16:33 2019/10/18/018
     * @Param []
     **/
    @RequestMapping(value = "updateExpertScoreSheet")
    @ResponseBody
    public Map<String, Object> updateExpertScoreSheet(@RequestBody ExpertScoreSheet expertScoreSheet, HttpSession session) {
        Map<String, Object> modelMap = new ModelMap();
        modelMap.put("success", "false");
        modelMap.put("msg", "新增数据失败！！");

        // 1、从session中获取登录系统的专家用户名，拼接对象数据，然后插入数据库
        // String expertName = "xlllllllll";
        String expertName = (String) session.getAttribute("userName");
        logger.info("保存用户登录信息，用户名是：" + expertName);

        expertScoreSheet.setExpertName(expertName);
        expertScoreSheet.setCreateTime(TimeUtil.getTimeByDefautFormat());
        expertScoreSheet.setUpdateTime(TimeUtil.getTimeByDefautFormat());

        // 2、向表finalscoresheet中写入数据
        FinalScoreSheet finalScoreSheet = new FinalScoreSheet();
        finalScoreSheet.setProjectName(expertScoreSheet.getProjectName());
        finalScoreSheet.setProjectNumber(expertScoreSheet.getProjectNumber());
        finalScoreSheet.setCompanyName(expertScoreSheet.getCompanyName());
        finalScoreSheet.setCreateTime(TimeUtil.getTimeByDefautFormat());
        finalScoreSheet.setUpdateTime(TimeUtil.getTimeByDefautFormat());
        // 2.1 校验finalscoresheet是否已经存在，如果不存在则insert。
        FinalScoreSheet finalScoreSheet1 = null;
        finalScoreSheet1 = finalScoreSheetService.findFinalScoreSheetByCondtion(finalScoreSheet);
        if (finalScoreSheet1 == null) {
            finalScoreSheetService.insertFinalScoreSheet(finalScoreSheet);
        }

        Integer insertFlag = expertScoreSheetService.updateExpertScoreSheet(expertScoreSheet);
        if (insertFlag > 0) {
            logger.info("保存专家打分结果成功，具体信息为：" + expertScoreSheet.toString());
            modelMap.put("success", "true");
            modelMap.put("msg", "保存专家评标打分结果成功！！");
            return modelMap;

        } else {
            modelMap.put("success", "false");
            modelMap.put("msg", "保存专家评标打分结果失败！！");
        }

        return modelMap;
    }

    @RequestMapping(value = "getexportscore", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getAllExportScore(@RequestBody ExpertScoreSheetPagination expertScoreSheetPagination) {
        Map map = new HashMap();
        String projectNumber = expertScoreSheetPagination.getProjectNumber();
        if (projectNumber != null && projectNumber.length() != 0) {
            int whichPage = expertScoreSheetPagination.getPage();
            int everyNumber = expertScoreSheetPagination.getColumns();
            expertScoreSheetPagination.setStartNumber((whichPage - 1) * everyNumber);
            int count = expertScoreSheetService.getExportScoreCount(projectNumber);

            List<Map<String, Object>> expertScoreSheetList = expertScoreSheetService.getExpertScoreByProjectNumberAndPagination(expertScoreSheetPagination);
            if (!expertScoreSheetList.isEmpty()) {
                map.put("page", expertScoreSheetPagination.getPage());
                map.put("count", count);
                map.put("total", (int) Math.ceil((double) count / everyNumber));
                map.put("expertScoreSheetList", expertScoreSheetList);
                map.put("msg", "查询打分列表成功！");
                map.put("success", true);
            } else {
                map.put("msg", "没有查询到打分表！");
                map.put("success", false);
            }
        } else {
            map.put("msg", "无效的项目编号！");
            map.put("success", false);
        }
        return map;
    }

    @RequestMapping(value = "getscoreinfo")
    @ResponseBody
    public Map<String, Object> getScoreInfo(@RequestBody ExpertScoreSheet expertScoreSheet) {
        Map map = new HashMap();
        ExpertScoreSheet expertScoreSheet1 = expertScoreSheetService.getScoreInfoByExpertNameAndCompanyName(expertScoreSheet);
        if (expertScoreSheet1 != null) {
            map.put("expertScoreSheet", expertScoreSheet1);
            map.put("success", true);
            map.put("msg", "查询打分详情信息成功！");
        } else {
            map.put("success", false);
            map.put("msg", "查询打分详情信息失败！");
        }

        return map;
    }

    /**
    * @Author xulei
    * @Description 下发评标打分模板
    * @Date 16:31 2019/10/28/028
    * @Param [paginationRequest]
    * @return java.util.Map<java.lang.String,java.lang.Object>
    **/
    @RequestMapping("/getExpertScoreSheetListPagination")
    @ResponseBody
    public Map<String, Object> getExpertScoreSheetListPagination(@RequestBody ExpertScoreSheetPagination expertScoreSheetPagination) {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        // 1、到scoreSheetTemplate表中查询唯一生效的模板
        ScoreSheetTemplate scoreSheetTemplate = null;
        scoreSheetTemplate = scoreSheetTemplateMapper.sendScoreSheetTemplateToExpert();
        if (scoreSheetTemplate == null) {
            modelMap.put("success", "false");
            modelMap.put("msg", "不存在处于生效状态的评标打分模板，请联系项目经理！");
            return modelMap;
        }

        //1、根据上面scoreSheetTemplate中的name和number，查询expertScoreSheet表
        int whichPage = expertScoreSheetPagination.getPage();
        int everyNumber = expertScoreSheetPagination.getColumns();
        expertScoreSheetPagination.setStartNumber((whichPage - 1) * everyNumber);

        logger.info("专家打分列表的查询条件：projectName：" + scoreSheetTemplate.getProjectName()
                + "  ,projectNumber:" + scoreSheetTemplate.getProjectNumber());
        expertScoreSheetPagination.setProjectName(scoreSheetTemplate.getProjectName());
        expertScoreSheetPagination.setProjectNumber(scoreSheetTemplate.getProjectNumber());


        List<ExpertScoreSheet> expertScoreSheetList = new ArrayList<>();
        // 只查询当前处于未打分的表
        // 2、查询数据总数
        Integer count = expertScoreSheetService.findScoreSheetTotalCount(expertScoreSheetPagination);
        logger.info("总数为：" + count);

        // 3、查询当页的数据信息
        expertScoreSheetList = expertScoreSheetService.findScoreSheetPagination(expertScoreSheetPagination);
        logger.info("数据为：" + expertScoreSheetList.size());

        if (!expertScoreSheetList.isEmpty()) {
            modelMap.put("success", "true");
            modelMap.put("page", expertScoreSheetPagination.getPage());
            modelMap.put("count", count);
            modelMap.put("total", (int)Math.ceil((double) count / everyNumber));
            modelMap.put("data", expertScoreSheetList);
        } else {
            modelMap.put("success", "false");
            modelMap.put("msg", "数据为空！！");
        }

        return modelMap;
    }

}
