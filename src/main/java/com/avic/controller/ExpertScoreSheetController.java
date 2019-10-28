package com.avic.controller;

import com.avic.common.utils.TimeUtil;
import com.avic.model.ExpertScoreSheet;
import com.avic.model.ScoreSheetTemplate;
import com.avic.model.httovo.PaginationRequest;
import com.avic.model.FinalScoreSheet;
import com.avic.service.ExpertScoreSheetService;
import com.avic.service.FinalScoreSheetService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

        // 从session中获取登录系统的专家用户名，拼接对象数据，然后插入数据库
        // String expertName = (String) session.getAttribute("userName");
        expertScoreSheet.setExpertName("xlllllllll");

        expertScoreSheet.setCreateTime(TimeUtil.getTimeByDefautFormat());
        expertScoreSheet.setUpdateTime(TimeUtil.getTimeByDefautFormat());

        // 向表finalscoresheet中写入数据
        FinalScoreSheet finalScoreSheet = new FinalScoreSheet();
        finalScoreSheet.setProjectName(expertScoreSheet.getProjectName());
        finalScoreSheet.setProjectNumber(expertScoreSheet.getProjectNumber());
        finalScoreSheet.setCompanyName(expertScoreSheet.getCompanyName());
        finalScoreSheet.setCreateTime(TimeUtil.getTimeByDefautFormat());
        finalScoreSheet.setUpdateTime(TimeUtil.getTimeByDefautFormat());
        finalScoreSheetService.insertFinalScoreSheet(finalScoreSheet);

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

    @RequestMapping(value = "getexportscore")
    @ResponseBody
    public Map<String, Object> getAllExportScore(@RequestBody String projectNumber, PaginationRequest paginationRequest) {
        Map map = new HashMap();
        if (projectNumber != null && projectNumber.length() != 0) {
            int whichPage = paginationRequest.getPage();
            int everyNumber = paginationRequest.getColumns();
            paginationRequest.setStartNumber((whichPage - 1) * everyNumber);
            int count = expertScoreSheetService.getExportScoreCount(projectNumber);

            List<ExpertScoreSheet> expertScoreSheetList = expertScoreSheetService.getExpertScoreByProjectNumberAndPagination(projectNumber, paginationRequest);
            if (!expertScoreSheetList.isEmpty()) {
                map.put("page", paginationRequest.getPage());
                map.put("count", count);
                map.put("total", (int) Math.ceil((double) count / everyNumber));
                map.put("expertScoreSheetList", expertScoreSheetList);
                map.put("msg", "查询打分列表成功！");
                map.put("success", "true");
            } else {
                map.put("msg", "没有查询到打分表！");
                map.put("success", "false");
            }
        } else {
            map.put("msg", "无效的项目编号！");
            map.put("success", "false");
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
            map.put("success", "true");
            map.put("msg", "查询打分详情信息成功！");
        } else {
            map.put("success", "false");
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
    @RequestMapping("/sendTemplatePagination")
    @ResponseBody
    public Map<String, Object> sendScoreSheetTemplateToExpertPagination(@RequestBody PaginationRequest paginationRequest) {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        //1、根据前端参数->查询ScoreSheetTemplate表
        int whichPage = paginationRequest.getPage();
        int everyNumber = paginationRequest.getColumns();
        paginationRequest.setStartNumber((whichPage - 1) * everyNumber);


        List<ExpertScoreSheet> expertScoreSheetList = new ArrayList<>();
        // 只查询当前处于未打分的表
        // 2、查询数据总数
        Integer count = expertScoreSheetService.findScoreSheetTotalCount();

        // 3、查询当页的数据信息
        expertScoreSheetList = expertScoreSheetService.findScoreSheetPagination(paginationRequest);

        if (!expertScoreSheetList.isEmpty()) {
            modelMap.put("success", "true");
            modelMap.put("page", paginationRequest.getPage());
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
