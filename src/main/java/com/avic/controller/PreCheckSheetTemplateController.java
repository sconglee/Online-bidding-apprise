package com.avic.controller;

import com.avic.common.utils.TimeUtil;
import com.avic.model.PreCheckSheetTemplate;
import com.avic.model.httovo.PaginationRequest;
import com.avic.service.PreCheckSheetTemplateService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PreCheckSheetTemplateController
 * @Description 处理“预审表”相关操作
 * @Author xulei
 * @Date 2020/2/19/019 14:05
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/prechecksheet")
public class PreCheckSheetTemplateController {
    private static final Log logger = LogFactory.getLog(PreCheckSheetTemplateController.class);

    @Autowired
    private PreCheckSheetTemplateService preCheckSheetTemplateService;

    /**
    * @Author xulei
    * @Description 新增预审表
    * @Date 14:18 2020/2/19/019
    * @Param [preCheckSheetTemplate]
    * @return Map<String,Object>
    **/
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insertPreCheckSheetTemplate(@RequestBody PreCheckSheetTemplate preCheckSheetTemplate) {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("success", "false");
        modelMap.put("msg", "新增数据失败！！");

        preCheckSheetTemplate.setCreateTime(TimeUtil.getTimeByDefautFormat());
        preCheckSheetTemplate.setUpdateTime(TimeUtil.getTimeByDefautFormat());
        Integer insertResult = preCheckSheetTemplateService.insertPreCheckSheetTemplate(preCheckSheetTemplate);
        if (insertResult > 0) {
            modelMap.put("success", "true");
            modelMap.put("msg", "新增数据成功！！");
        }

        return modelMap;
    }

    /**
    * @Author xulei
    * @Description 修改预审表
    * @Date 14:25 2020/2/19/019
    * @Param [preCheckSheetTemplate]
    * @return java.util.Map<java.lang.String,java.lang.Object>
    **/
    @RequestMapping(value = "/update")
    @ResponseBody
    public Map<String, Object> updatePreCheckSheetTemplate(@RequestBody PreCheckSheetTemplate preCheckSheetTemplate) {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("success", "false");
        modelMap.put("msg", "更新数据失败！！");

        // 1、根据id查询数据库中是否存在该信息，如果存在则修改；不存在则报错
        PreCheckSheetTemplate resultTemplate = preCheckSheetTemplateService.findPreCheckSheetTemplateById(preCheckSheetTemplate.getId());
        if (resultTemplate == null) {
            // 2、数据库中不存在，报错
            modelMap.put("success", "false");
            modelMap.put("msg","数据库中不存在该预审表信息,请到新建预审表页面进行新增预审表信息！！");
            return  modelMap;
        }
        // 3、更新预审表
        preCheckSheetTemplate.setUpdateTime(TimeUtil.getTimeByDefautFormat());
        logger.info("修改预审表信息，具体数据为：" + preCheckSheetTemplate.toString());
        preCheckSheetTemplateService.updatePreCheckSheetTemplate(preCheckSheetTemplate);
        modelMap.put("success", "true");
        modelMap.put("msg", "更新数据成功！！");
        return modelMap;
    }

    /**
    * @Author xulei
    * @Description 查看预审表详情--根据id
    * @Date 15:15 2020/2/19/019
    * @Param [preCheckSheetTemplate]
    * @return java.util.Map<java.lang.String,java.lang.Object>
    **/
    @RequestMapping(value = "/findbyid")
    @ResponseBody
    public Map<String, Object> findPreCheckSheetTemplateById(@RequestBody PreCheckSheetTemplate preCheckSheetTemplate) {
        Map<String, Object> modelMap = new HashMap<>();

        logger.info("查询预审表的提交为id，该id的值为：" + preCheckSheetTemplate.getId());
        PreCheckSheetTemplate resultTemplate = preCheckSheetTemplateService.findPreCheckSheetTemplateById(preCheckSheetTemplate.getId());
        if (resultTemplate == null) {
            modelMap.put("success", "false");
            modelMap.put("msg", "不存在符合条件的预审表！");
        } else {
            modelMap.put("success", "true");
            modelMap.put("data", resultTemplate);
        }
        return modelMap;
    }

    /**
    * @Author xulei
    * @Description 删除预审表--根据id
    * @Date 15:23 2020/2/19/019
    * @Param [id]
    * @return java.util.Map<java.lang.String,java.lang.Object>
    **/
    @RequestMapping(value = "/deletebyid")
    @ResponseBody
    public Map<String, Object> deletePreCheckSheetTemplateById(@RequestBody PreCheckSheetTemplate preCheckSheetTemplate) {
        Map<String, Object> modelMap = new HashMap<>();

        // 1、根据id查询数据库中是否存在该信息，如果存在则删除；不存在则报错
        PreCheckSheetTemplate resultTemplate = preCheckSheetTemplateService.findPreCheckSheetTemplateById(preCheckSheetTemplate.getId());
        if (resultTemplate == null) {
            // 2、数据库中不存在，报错
            modelMap.put("success", "false");
            modelMap.put("msg","不存在所要删除的预审表信息");
            return  modelMap;
        }
        // 3、删除预审表
        logger.info("所要删除的预审表的id的值为：" + preCheckSheetTemplate.getId());
        Boolean result = preCheckSheetTemplateService.deletePreCheckSheetTemplateById(preCheckSheetTemplate.getId());
        if (result) {
            modelMap.put("success", "true");
            modelMap.put("msg", "删除数据成功！！");
        } else {
            modelMap.put("success", "false");
            modelMap.put("msg", "删除数据失败！！");
        }
        return modelMap;
    }


    @RequestMapping(value = "/findallpagination")
    @ResponseBody
    public Map<String, Object> findPreCheckSheetTemplatePagination(@RequestBody PaginationRequest paginationRequest) {
        Map<String, Object> modelMap = new HashMap<>();

        // 1、根据前端传入的参数->查询PreCheckSheetTemplate表
        int whichPage = paginationRequest.getPage();
        int everyNumber = paginationRequest.getColumns();
        paginationRequest.setStartNumber((whichPage - 1) * everyNumber);
        List<PreCheckSheetTemplate> preCheckSheetTemplateList = new ArrayList<>();
        preCheckSheetTemplateList = preCheckSheetTemplateService.findPreCheckSheetTemplatePagination(paginationRequest);

        // 2、查询当前数据库中的总条数
        Integer count = preCheckSheetTemplateService.findTemplateTotalCount();

        // 3、封装数据
        if (!preCheckSheetTemplateList.isEmpty()) {
            modelMap.put("success", "true");
            modelMap.put("page", whichPage);
            modelMap.put("count", count);
            modelMap.put("total", (int) Math.ceil((double) count / everyNumber));
            modelMap.put("data", preCheckSheetTemplateList);
        } else {
            modelMap.put("success", "false");
            modelMap.put("msg", "数据为空！");
        }
        return modelMap;
    }



}
