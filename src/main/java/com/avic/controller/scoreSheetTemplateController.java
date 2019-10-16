/*
package com.avic.controller;

import com.avic.common.constant.BidConstant;
import com.avic.common.utils.TimeUtil;
import com.avic.model.ScoreSheetTemplate;
import com.avic.service.ScoreSheetTemplateService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.spi.LoggerFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

*/
/**
 * @ClassName scoreSheetTemplateController
 * @Description 评分表模板
 * @Author xulei
 * @Date 2019/10/16/016 10:45
 * @Version 1.0
 **//*

@Controller(value = "/template")
public class scoreSheetTemplateController {

    private static final Log logger = LogFactory.getLog(scoreSheetTemplateController.class);

    @Autowired
    private ScoreSheetTemplateService scoreSheetTemplateService;

   */
/**
   * @Author xulei
   * @Description 新增评标打分模板
   * @Date 15:51 2019/10/16
   * @Param [scoreSheetTemplate]
   * @return java.util.Map<java.lang.String,java.lang.Object>
   **//*

   @RequestMapping("/insertTemplate")
   @ResponseBody
    public Map<String, Object> insertScoreSheetTemplate(ScoreSheetTemplate scoreSheetTemplate) {
       Map<String, Object> modelMap = new HashMap<String, Object>();

        // 先查询数据库是否已经存在，如果存在则报错
        ScoreSheetTemplate result = scoreSheetTemplateService.findTemplateByProjectNameAndNumber(scoreSheetTemplate);
        if (result == null || result.getRemove().equals(BidConstant.TEMPLATE_REMOVE)) {
            // 数据库中不存在，执行insert操作
            logger.info("新增评标打分模板，具体数据为：" + scoreSheetTemplate.toString());
            scoreSheetTemplateService.createScoreSheetTemplate(scoreSheetTemplate);
            modelMap.put("success", "true");
        } else {
            modelMap.put("success", "false");
            modelMap.put("msg","已经存在该评标打分模板,请到修改页面进行修改相关内容！！");
        }

        modelMap.put("success", "false");
        modelMap.put("msg", "新增数据失败！！");

        return modelMap;
    }

    */
/**
    * @Author xulei
    * @Description 获取所有有效的评标打分模板信息
    * @Date 15:59 2019/10/16/016
    * @Param []
    * @return void
    **//*

   @RequestMapping()
   @ResponseBody
    public void findAllScoreSheetTemplate() {
       Map<String, Object> modelMap = new HashMap<String, Object>();

       List<ScoreSheetTemplate> scoreSheetTemplateList = new ArrayList<>();
       scoreSheetTemplateList = scoreSheetTemplateService.findAllScoreSheetTemplate();
       if (!scoreSheetTemplateList.isEmpty()) {
           modelMap.put("success", "true");
           modelMap.put("data", scoreSheetTemplateList);
       } else {

       }
       for (ScoreSheetTemplate scoreSheetTemplate : scoreSheetTemplateList) {
            System.out.println("项目名称是：" + scoreSheetTemplate.getProjectName() + ";    项目编号是：" + scoreSheetTemplate.getProjectNumber()
                    + ";  项目状态是：" + BidConstant.templateStatus.get(scoreSheetTemplate.getStatus())
                    + ";   模板生成时间：" + scoreSheetTemplate.getCreateTime());
        }
    }

    */
/*
     * @Author xulei
     * @Description 修改模板
     * 根据项目名称 + 项目编号的组合查询条件，修改评标打分模板
     * @Date 13:36 2019/10/16/016
     * @Param [scoreSheetTemplate]
     * @return void
     **//*

    public void updateScoreSheetTemplate() {
        ScoreSheetTemplate scoreSheetTemplate = new ScoreSheetTemplate();
        scoreSheetTemplate.setProjectName("XX工程GS-1307 高速电视测量系统改造(二次)");
        scoreSheetTemplate.setProjectNumber("0730-196012JP0027/02");
        scoreSheetTemplate.setScoredComName("西安" + "," + "江西" + "," + "山东建筑");
        scoreSheetTemplate.setStatus(BidConstant.TEMPLATE_NO_ACTIVE);
        scoreSheetTemplate.setUpdateTime(TimeUtil.getTimeByDefautFormat());

        scoreSheetTemplateMapper.updateScoreSheetTemplate(scoreSheetTemplate);
    }


    */
/*
     * @Author xulei
     * @Description 根据项目名称和项目编号查询模板
     *   后期可以修改为根据id查询
     * @Date 13:38 2019/10/16/016
     * @Param [scoreSheetTemplate]
     * @return com.avic.model.ScoreSheetTemplate
     **//*

    public void findTemplateByProjectNameAndNumber() {

        ScoreSheetTemplate scoreSheetTemplate = new ScoreSheetTemplate();
        scoreSheetTemplate.setProjectName("XX工程GS-1307 高速电视测量系统改造(二次)");
        scoreSheetTemplate.setProjectNumber("0730-196012JP0027/04");

        ScoreSheetTemplate result = scoreSheetTemplateMapper.findTemplateByProjectNameAndNumber(scoreSheetTemplate);
        if (result != null && result.getDelete() != BidConstant.TEMPLATE_DELTER) {
            System.out.println("根据项目名称和项目编号查询评标打分模板成功：");
            System.out.println(result.getSequenceNumber());
            System.out.println(result.toString());
        } else {
            System.out.println("根据项目名称和项目编码查询失败！！");
        }

    }

    */
/*
     * @Author xulei
     * @Description 使模板生效或者失效
     *   前端至少给后端传送三个参数：
     *   项目名称  项目编号  状态值
     * @Date 13:39 2019/10/16/016
     * @Param [scoreSheetTemplate]
     * @return void
     **//*

    public void enableEffectiveOrNot() {
        ScoreSheetTemplate scoreSheetTemplate = new ScoreSheetTemplate();
        scoreSheetTemplate.setProjectName("XX工程GS-1307 高速电视测量系统改造(二次)");
        scoreSheetTemplate.setProjectNumber("0730-196012JP0027/04");

        // 先去数据查询数据
        ScoreSheetTemplate result = scoreSheetTemplateMapper.findTemplateByProjectNameAndNumber(scoreSheetTemplate);
        if (result != null && result.getDelete() != BidConstant.TEMPLATE_DELTER) {
            System.out.println("根据项目名称和项目编号查询评标打分模板成功：");
            System.out.println(result.toString());

            //查询成功，修改数据，update数据库
            if (result.getStatus() == BidConstant.TEMPLATE_NO_ACTIVE) {
                // 设置为0 生效
                result.setStatus(BidConstant.TEMPLATE_ACTIVE);
            } else {
                // 设置为1 失效
                result.setStatus(BidConstant.TEMPLATE_NO_ACTIVE);
            }
            System.out.println(result.toString());

            result.setUpdateTime(TimeUtil.getTimeByDefautFormat());
            result.setCreateTime(TimeUtil.getTimeByDefautFormat());
            System.out.println(result.toString());

            scoreSheetTemplateMapper.enableEffectiveOrNot(result);

        } else {
            System.out.println("该项目名称和项目编码对应的评分模板不存在，请确认后重试！！");
        }
    }

    */
/*
     * @Author xulei
     * @Description 删除模板--硬删除
     *  根据评分表模板中项目名称 + 项目编码锁定唯一模板，进行删除
     * @Date 13:37 2019/10/16/016
     * @Param [scoreSheetTemplate]
     * @return void
     **//*

    public void deleteScoreSheetTemplate() {
        ScoreSheetTemplate scoreSheetTemplate = new ScoreSheetTemplate();
        scoreSheetTemplate.setProjectName("XX工程GS-1307 高速电视测量系统改造(二次)");
        scoreSheetTemplate.setProjectNumber("0730-196012JP0027/05");

        scoreSheetTemplateMapper.deleteScoreSheetTemplate(scoreSheetTemplate);
        System.out.println("删除成功！！");
    }




}
*/
