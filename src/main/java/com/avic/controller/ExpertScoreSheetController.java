package com.avic.controller;

import com.avic.common.utils.TimeUtil;
import com.avic.mapper.ExpertScoreSheetMapper;
import com.avic.mapper.ScoreSheetTemplateMapper;
import com.avic.model.ExpertScoreSheet;
import com.avic.model.FinalScoreSheet;
import com.avic.model.ScoreSheetTemplate;
import com.avic.model.httovo.ExpertScoreSheetComAndPoint;
import com.avic.model.httovo.ExpertScoreSheetInsert;
import com.avic.model.httovo.ExpertScoreSheetPagination;
import com.avic.service.ExpertScoreSheetService;
import com.avic.service.FinalScoreSheetService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    protected static final Log logger = LogFactory.getLog(ExpertScoreSheetController.class);

    @Autowired
    private ExpertScoreSheetService expertScoreSheetService;

    @Autowired
    private FinalScoreSheetService finalScoreSheetService;

    @Autowired
    private ScoreSheetTemplateMapper scoreSheetTemplateMapper;

    @Autowired
    private ExpertScoreSheetMapper expertScoreSheetMapper;


    /**
     * @Author xulei
     * @Description 修改版本：专家登陆系统后，展示待打分项目信息，在一个表单中同时展示所有单位
     * 查询到系统中唯一一个生效状态的模板，前端展示
     * @Date 11:28 2019/11/13
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

        //2、使用项目名称 + 项目编号 + 专家用户名 查询expertScoreSheet表，如果有则说明已经打过分，status = 0; 未打分 status = 1
        expertScoreSheetPagination.setProjectName(scoreSheetTemplate.getProjectName());
        expertScoreSheetPagination.setProjectNumber(scoreSheetTemplate.getProjectNumber());
        Integer count = expertScoreSheetService.findScoreSheetTotalCount(expertScoreSheetPagination);
        if (count > 0) {
            // 已打分
            scoreSheetTemplate.setStatus(0);
        } else {
            // 未打分
            scoreSheetTemplate.setStatus(1);
        }

        modelMap.put("success", "true");
        modelMap.put("data", scoreSheetTemplate);
        return modelMap;
    }

    @RequestMapping(value = "insertExpertScoreSheet")
    @ResponseBody
    public Map<String, Object> insertExpertScoreSheet(@RequestBody List<ExpertScoreSheet> expertScoreSheetList ) {
        Map<String, Object> modelMap = new ModelMap();
        modelMap.put("success", "false");
        modelMap.put("msg", "专家评分失败！！");

        // 1、批量保存专家打分结果
        Integer insertFlag = expertScoreSheetService.insertExpertScoreSheetForeach(expertScoreSheetList);
        if (insertFlag > 0) {
            modelMap.put("success", "true");
            modelMap.put("msg", "专家评分成功！");
        }

        // 2、向表finalscoresheet中写入数据
        List<FinalScoreSheet> finalScoreSheetList = new ArrayList<>();
        for (ExpertScoreSheet expertScoreSheet : expertScoreSheetList) {
            FinalScoreSheet finalScoreSheet = new FinalScoreSheet();
            finalScoreSheet.setProjectName(expertScoreSheet.getProjectName());
            finalScoreSheet.setProjectNumber(expertScoreSheet.getProjectNumber());
            finalScoreSheet.setCompanyName(expertScoreSheet.getCompanyName());
            finalScoreSheet.setCreateTime(TimeUtil.getTimeByDefautFormat());
            finalScoreSheet.setUpdateTime(TimeUtil.getTimeByDefautFormat());

            finalScoreSheetList.add(finalScoreSheet);
        }
        // 3、校验finalscoresheet是否已经存在，如果不存在则insert。
        FinalScoreSheet result = finalScoreSheetService.findFinalScoreSheetByCondtion(finalScoreSheetList.get(0));
        if (result == null) {
            finalScoreSheetService.insertFinalScoreSheetPagination(finalScoreSheetList);
        }

        return modelMap;
    }

    @RequestMapping(value = "insertExpertScoreSheetTest")
    @ResponseBody
    public Map<String, Object> insertExpertScoreSheetTest(@RequestBody ExpertScoreSheetInsert expertScoreSheetInsert ) {
        Map<String, Object> modelMap = new ModelMap();
        modelMap.put("success", "false");
        modelMap.put("msg", "专家评分失败！！");

        List<ExpertScoreSheetComAndPoint> expertScoreSheetComAndPointList = expertScoreSheetInsert.getExpertScoreSheetComAndPointList();
        if (expertScoreSheetComAndPointList.size() == 0) {
            return  modelMap;
        }

        // 1、组装expertScoreSheet、FinalScoreSheet数据
        HashMap<String,List> hashMapResult = expertScoreSheetService.getInsertExpertScoreSheetData(expertScoreSheetInsert);
        List<ExpertScoreSheet> expertScoreSheetList = new ArrayList<>();
        expertScoreSheetList = hashMapResult.get("expertScoreSheet");
        List<FinalScoreSheet> finalScoreSheetList = new ArrayList<>();
        finalScoreSheetList = hashMapResult.get("finalScoreSheet");

        // 1、批量保存专家打分结果
        Integer insertFlag = expertScoreSheetService.insertExpertScoreSheetForeach(expertScoreSheetList);
        if (insertFlag > 0) {
            modelMap.put("success", "true");
            modelMap.put("msg", "专家评分成功！");
        }

        // 2、向表finalscoresheet中写入数据
        // 2.1 校验finalscoresheet是否已经存在，如果不存在则insert。
        FinalScoreSheet result = null;
        result = finalScoreSheetService.findFinalScoreSheetByCondtion(finalScoreSheetList.get(0));
        if (result == null) {
            finalScoreSheetService.insertFinalScoreSheetPagination(finalScoreSheetList);
        }

        return modelMap;
    }


    /**
     * @Author xulei
     * @Description 根据projectName  projectNumber expertName批量获取专家打分结果
     * @Date 9:11 2019/11/14/014
     * @Param [expertScoreSheetPagination]
     * @return java.util.List<com.avic.model.ExpertScoreSheet>
     **/
    @RequestMapping("/getExpertScoreSheetList")
    @ResponseBody
    public Map<String, Object> getExpertScoreSheetList(@RequestBody ExpertScoreSheetPagination expertScoreSheetPagination) {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        // 1、查询expertScoreSheet，获取数据
        List<ExpertScoreSheet> expertScoreSheetList = expertScoreSheetService.getExpertScoreSheetList(expertScoreSheetPagination);
        if (expertScoreSheetList.size() == 0) {
            modelMap.put("success", "false");
            modelMap.put("msg", "不存在对应的评标打分结果数据，请先打分再查看！");
            return modelMap;
        }

        modelMap.put("success", "true");
        modelMap.put("total", expertScoreSheetList.size());
        modelMap.put("data", expertScoreSheetList);
        return modelMap;
    }

    /**
     * @Author xulei
     * @Description 根据projectName  projectNumber expertName批量获取专家打分结果
     * @Date 9:11 2019/11/14/014
     * @Param [expertScoreSheetPagination]
     * @return java.util.List<com.avic.model.ExpertScoreSheet>
     **/
    @RequestMapping("/getExpertScoreSheetListTest")
    @ResponseBody
    public Map<String, Object> getExpertScoreSheetListTest(@RequestBody ExpertScoreSheetPagination expertScoreSheetPagination) {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        // 1、查询expertScoreSheet，获取数据
        List<ExpertScoreSheet> expertScoreSheetList = expertScoreSheetService.getExpertScoreSheetList(expertScoreSheetPagination);
        if (expertScoreSheetList.size() == 0) {
            modelMap.put("success", "false");
            modelMap.put("msg", "不存在对应的评标打分结果数据，请先打分再查看！");
            return modelMap;
        }

        // 2、组装数据格式
        ExpertScoreSheetInsert expertScoreSheetInsert = expertScoreSheetService.getExpertScoreSheetInsertToWeb(expertScoreSheetList);
        modelMap.put("success", "true");
        modelMap.put("total", expertScoreSheetInsert.getExpertScoreSheetComAndPointList().size());
        modelMap.put("data", expertScoreSheetInsert);
        return modelMap;
    }


    /**
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @Author xulei
     * @Description 新增专家打分记录
     * @Date 16:33 2019/10/18/018
     * @Param []
     **/
    @RequestMapping(value = "updateExpertScoreSheet")
    @ResponseBody
    public Map<String, Object> updateExpertScoreSheet(@RequestBody ExpertScoreSheet expertScoreSheet, HttpServletRequest request ) {
        Map<String, Object> modelMap = new ModelMap();
        modelMap.put("success", "false");
        modelMap.put("msg", "新增数据失败！！");

        // 1、从session中获取登录系统的专家用户名，拼接对象数据，然后插入数据库
        HttpSession session = request.getSession();
        String expertName = (String) session.getAttribute("userName");
        logger.info("登录系统时保存的用户名信息，用户名是：" + expertName);
        /*if (!expertName.isEmpty()) {
            expertScoreSheet.setExpertName(expertName);
        }*/

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
    * @Description 专家登陆系统后，展示待打分列表
     * 1、向expertScoreSheet表中写入数据（如果不存在）
     * 2、分页查询，前端展示
    * @Date 16:31 2019/10/28/028
    * @Param [paginationRequest]
    * @return java.util.Map<java.lang.String,java.lang.Object>
    **/
    /*@RequestMapping("/getExpertScoreSheetListPagination")
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

        // 1、获取当前专家需要打分的expertScoreSheet列表，并插入expertScoreSheet表中
        List<ExpertScoreSheet> expertScoreSheetList = expertScoreSheetService.getExpertScoreSheetFromTemplate(expertScoreSheetPagination.getExpertName());
        for (ExpertScoreSheet expertScoreSheet : expertScoreSheetList) {
            System.out.println("teset: " + expertScoreSheet.getCompanyName());
        }
        // 根据projectName、projectNumber、companyName、 expertName四个字段查询expertScoreSheet表，如果不存在就insert。
        expertScoreSheetMapper.insertExpertScoreSheetForeach(expertScoreSheetList);

        // 2、查询分页数据展示列表
        modelMap = expertScoreSheetService.findScoreSheetPaginationInfo(scoreSheetTemplate,expertScoreSheetPagination);

        return modelMap;
    }*/

    /**
     * @Author xulei
     * @Description 展示待打分列表
     * @Date 16:31 2019/10/28/028
     * @Param [paginationRequest]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **//*
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
    }*/


}
