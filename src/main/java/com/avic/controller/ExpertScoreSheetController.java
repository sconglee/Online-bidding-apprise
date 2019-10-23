package com.avic.controller;

import com.avic.common.utils.TimeUtil;
import com.avic.model.ExpertScoreSheet;
import com.avic.service.ExpertScoreSheetService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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

    /**
    * @Author xulei
    * @Description 新增专家打分记录
    * @Date 16:33 2019/10/18/018
    * @Param []
    * @return java.util.Map<java.lang.String,java.lang.Object>
    **/
    @RequestMapping(value = "insertExpertScoreSheet")
    @ResponseBody
    public Map<String, Object> insertExpertScoreSheet(@RequestBody ExpertScoreSheet expertScoreSheet, HttpSession session) {
        Map<String, Object> modelMap = new ModelMap();
        modelMap.put("success", "false");
        modelMap.put("msg", "新增数据失败！！");

        // 从session中获取登录系统的专家用户名，拼接对象数据，然后插入数据库
        // String expertName = (String) session.getAttribute("userName");
        expertScoreSheet.setExpertName("xlllllllll");

        expertScoreSheet.setCreateTime(TimeUtil.getTimeByDefautFormat());
        expertScoreSheet.setUpdateTime(TimeUtil.getTimeByDefautFormat());
        Integer insertFlag = expertScoreSheetService.insertExpertScoreSheet(expertScoreSheet);
        if (insertFlag > 0) {
            logger.info("保存专家打分结果成功，具体信息为：" + expertScoreSheet.toString());
            modelMap.put("success", "true");
            modelMap.put("msg", "保存专家评标打分结果成功！！");
            return modelMap;

        } else {
            modelMap.put("success", "false");
            modelMap.put("msg","保存专家评标打分结果失败！！");
        }
        
        return modelMap;
    }



}
