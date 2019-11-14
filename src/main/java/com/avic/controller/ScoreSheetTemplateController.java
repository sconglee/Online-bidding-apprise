package com.avic.controller;

import com.avic.common.constant.BidConstant;
import com.avic.common.utils.TimeUtil;
import com.avic.model.ExpertScoreSheet;
import com.avic.model.ScoreSheetTemplate;
import com.avic.model.httovo.PaginationRequest;
import com.avic.service.ExpertScoreSheetService;
import com.avic.service.ScoreSheetTemplateService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName scoreSheetTemplateController
 * @Description 评分表模板
 * @Author xulei
 * @Date 2019/10/16/016 10:45
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/template")
public class ScoreSheetTemplateController {

    private static final Log logger = LogFactory.getLog(ScoreSheetTemplateController.class);

    @Autowired
    private ScoreSheetTemplateService scoreSheetTemplateService;

    @Autowired
    private ExpertScoreSheetService expertScoreSheetService;

   /**
   * @Author xulei
   * @Description 新增评标打分模板
   * @Date 15:51 2019/10/16
   * @Param [scoreSheetTemplate]
   * @return java.util.Map<java.lang.String,java.lang.Object>
   **/
   @RequestMapping(value = "/insert",method = RequestMethod.POST)
   @ResponseBody
    public Map<String, Object> insertScoreSheetTemplate(@RequestBody ScoreSheetTemplate scoreSheetTemplate) {
       Map<String, Object> modelMap = new HashMap<String, Object>();
       modelMap.put("success", "false");
       modelMap.put("msg", "新增数据失败！！");

        // 先查询数据库是否已经存在，如果存在则报错
        ScoreSheetTemplate result = scoreSheetTemplateService.findTemplateByProjectNameAndNumber(scoreSheetTemplate);
        if (result == null || result.getRemove().equals(BidConstant.TEMPLATE_REMOVE)) {
            // 数据库中不存在，执行insert操作
            logger.info("新增评标打分模板，具体数据为：" + scoreSheetTemplate.toString());
            scoreSheetTemplate.setCreateTime(TimeUtil.getTimeByDefautFormat());
            scoreSheetTemplate.setUpdateTime(TimeUtil.getTimeByDefautFormat());
            scoreSheetTemplateService.createScoreSheetTemplate(scoreSheetTemplate);
            modelMap.put("success", "true");
            modelMap.put("msg", "新增评标打分模板成功！！");
            return modelMap;

        } else {
            modelMap.put("success", "false");
            modelMap.put("msg","已经存在该评标打分模板,请到修改页面进行修改相关内容！！");
        }

        return modelMap;
    }

    /**
    * @Author xulei
    * @Description 获取所有有效的评标打分模板信息
    * @Date 15:59 2019/10/16/016
    * @Param []
    * @return void
    **/
   @RequestMapping("/findTemplatePagination")
   @ResponseBody
    public Map<String, Object> findTemplatePagination(@RequestBody PaginationRequest paginationRequest) {
       Map<String, Object> modelMap = new HashMap<String, Object>();

       //1、根据前端参数->查询ScoreSheetTemplate表
       int whichPage = paginationRequest.getPage();
       int everyNumber = paginationRequest.getColumns();
       paginationRequest.setStartNumber((whichPage - 1) * everyNumber);


       List<ScoreSheetTemplate> scoreSheetTemplateList = new ArrayList<>();
       // 只查询当前处于未被删除的模板
       // 2、查询数据总数
       Integer count = scoreSheetTemplateService.findTemplateTotalCount();

       // 3、查询当页的数据信息
       scoreSheetTemplateList = scoreSheetTemplateService.findTemplatePagination(paginationRequest);

       if (!scoreSheetTemplateList.isEmpty()) {
           modelMap.put("success", "true");
           modelMap.put("page", paginationRequest.getPage());
           modelMap.put("count", count);
           modelMap.put("total", (int)Math.ceil((double) count / everyNumber));
           modelMap.put("data", scoreSheetTemplateList);
       } else {
           modelMap.put("success", "false");
           modelMap.put("msg", "数据为空！！");
       }

       return modelMap;
    }

    /**
    * @Author xulei
    * @Description 修改评分模板
    * @Date 16:24 2019/10/16/016
    * @Param [scoreSheetTemplate]
    * @return java.util.Map<java.lang.String,java.lang.Object>
    **/
    @RequestMapping(value = "/update")
    @ResponseBody
    public Map<String, Object> updateScoreSheetTemplate(@RequestBody ScoreSheetTemplate scoreSheetTemplate) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", "false");
        modelMap.put("msg", "新增数据失败！！");

        // 先查询数据库是否已经存在，如果存在则报错
        ScoreSheetTemplate result = scoreSheetTemplateService.findTemplateById(scoreSheetTemplate);
        if (result == null || result.getRemove().equals(BidConstant.TEMPLATE_REMOVE)) {
            // 数据库中不存在，报错
            modelMap.put("success", "false");
            modelMap.put("msg","数据库中不存在该评标打分模板,请到新建页面进行新增评标打分模板！！");
            return  modelMap;

        } else {
            scoreSheetTemplate.setUpdateTime(TimeUtil.getTimeByDefautFormat());
            logger.info("修改评标打分模板，具体数据为：" + scoreSheetTemplate.toString());
            scoreSheetTemplateService.updateScoreSheetTemplate(scoreSheetTemplate);

            // 根据projectName、projectNumber、两个字段查询expertScoreSheet表，如果存在就update。
            /*List<ExpertScoreSheet> expertScoreSheetList = expertScoreSheetService.getExpertScoreSheetFromTemplate("");
            if (expertScoreSheetList.size() > 0) {
                expertScoreSheetService.updateExpertScoreSheetForeach(expertScoreSheetList);
            }*/

            // 根据projectName、projectNumber、两个字段查询expertScoreSheet表，如果存在就批量删除。
            ExpertScoreSheet expertScoreSheet = new ExpertScoreSheet();
            expertScoreSheet.setProjectName(scoreSheetTemplate.getProjectName());
            expertScoreSheet.setSequenceNumber(scoreSheetTemplate.getSequenceNumber());
            expertScoreSheetService.deleteExpertScoreByProjectNameAndProjectNumber(expertScoreSheet);

            modelMap.put("success", "true");
            modelMap.put("msg", "修改评标打分模板成功！！");
        }

        return modelMap;
    }


   /**
   * @Author xulei
   * @Description 根据模板id查询评标打分模板
    * 对应前端 “查看”功能
   * @Date 9:05 2019/10/17/017
   * @Param [scoreSheetTemplate]
   * @return void
   **/
    @RequestMapping(value = "/findTemplateById")
    @ResponseBody
    public Map<String, Object> findTemplateById(@RequestBody ScoreSheetTemplate scoreSheetTemplate) {
        Map<String, Object> modelMap = new HashMap<>();

        logger.info("查询条件是模板id为：" + scoreSheetTemplate.getId());
        ScoreSheetTemplate resultTemplate = scoreSheetTemplateService.findTemplateById(scoreSheetTemplate);
        if (resultTemplate == null) {
            modelMap.put("success", "false");
            modelMap.put("msg", "没有找到对应的评标打分模板！");
        } else {
            modelMap.put("success", "true");
            modelMap.put("data", resultTemplate);
        }

        return modelMap;
    }

    /**
    * @Author xulei
    * @Description 针对评标打分模板：生效或者失效
    * @Date 9:22 2019/10/17/017
    * @Param []
    * @return void
    **/
    @RequestMapping(value = "/enableEffectiveOrNot")
    @ResponseBody
    public Map<String, Object> enableEffectiveOrNot(@RequestBody ScoreSheetTemplate scoreSheetTemplate) {

        Map<String, Object> modelMap = scoreSheetTemplateService.enableEffectiveOrNot(scoreSheetTemplate);

        return modelMap;
    }

    /**
    * @Author xulei
    * @Description 删除模板--硬删除
     * 根据评分表模板中项目名称 + 项目编码锁定唯一模板，进行删除
    * @Date 9:57 2019/10/17/017
    * @Param []
    * @return void
    **/
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String, Object> deleteScoreSheetTemplate(@RequestBody ScoreSheetTemplate scoreSheetTemplate) {
        Map<String, Object> modelMap = new HashMap<>();

        Boolean result = scoreSheetTemplateService.deleteScoreSheetTemplate(scoreSheetTemplate);
        if (result) {
            modelMap.put("success", "true");
            modelMap.put("msg", "删除成功！！");
        } else {
            modelMap.put("success", "false");
            modelMap.put("msg", "删除失败！");
        }

        return modelMap;
    }




}
