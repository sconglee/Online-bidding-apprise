package com.avic.service.impl;

import com.avic.common.constant.BidConstant;
import com.avic.common.utils.MD5;
import com.avic.common.utils.TimeUtil;
import com.avic.mapper.ExpertScoreSheetMapper;
import com.avic.mapper.ScoreSheetTemplateMapper;
import com.avic.model.ExpertScoreSheet;
import com.avic.model.ScoreSheetTemplate;
import com.avic.model.httovo.PaginationRequest;
import com.avic.service.ExpertScoreSheetService;
import com.avic.service.ScoreSheetTemplateService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName scoreSheetTemplateServiceImpl
 * @Description TODO
 * @Author xulei
 * @Date 2019/10/16/016 11:03
 * @Version 1.0
 **/
@Service
public class ScoreSheetTemplateServiceImpl implements ScoreSheetTemplateService {
    private static final Log logger = LogFactory.getLog(ScoreSheetTemplateServiceImpl.class);

    @Autowired
    private ScoreSheetTemplateMapper scoreSheetTemplateMapper;

    @Autowired
    private ExpertScoreSheetMapper expertScoreSheetMapper;

    @Autowired
    private ExpertScoreSheetService expertScoreSheetService;

    /**
    * @Author xulei
    * @Description 获取所有有效的评标打分模板信息
    * @Date 9:56 2019/10/17/017
    * @Param []
    * @return java.util.List<com.avic.model.ScoreSheetTemplate>
    **/
    @Override
    public Integer findTemplateTotalCount() {
        logger.info("获取评分表模板信息列表--总数：");
        return scoreSheetTemplateMapper.findTemplateTotalCount();
    }

    /**
    * @Author xulei
    * @Description 分页查询
    * @Date 9:18 2019/10/25/025
    * @Param [paginationRequest]
    * @return java.util.List<com.avic.model.ScoreSheetTemplate>
    **/
    @Override
    public List<ScoreSheetTemplate> findTemplatePagination(PaginationRequest paginationRequest) {
        logger.info("获取评分表模板信息列表--分页查询：");
        return scoreSheetTemplateMapper.findTemplatePagination(paginationRequest);
    }

    /**
    * @Author xulei
    * @Description 新增评标打分模板
    * @Date 9:56 2019/10/17/017
    * @Param [scoreSheetTemplate]
    * @return java.lang.Integer
    **/
    @Override
    public Integer createScoreSheetTemplate(ScoreSheetTemplate scoreSheetTemplate) {
        logger.info("添加新的评分表模板：");
        return scoreSheetTemplateMapper.insertScoreSheetTemplate(scoreSheetTemplate);
    }

    /**
    * @Author xulei
    * @Description 修改评分模板
    * @Date 9:55 2019/10/17/017
    * @Param [scoreSheetTemplate]
    * @return void
    **/
    @Override
    public void updateScoreSheetTemplate(ScoreSheetTemplate scoreSheetTemplate) {
        logger.info("修改评分表模板");
        scoreSheetTemplateMapper.updateScoreSheetTemplate(scoreSheetTemplate);
    }

    /**
    * @Author xulei
    * @Description * @Description 根据项目名称 + 项目编号 查询评标打分模板
     *     * 对应前端 “查看”功能
    * @Date 9:54 2019/10/17/017
    * @Param [scoreSheetTemplate]
    * @return com.avic.model.ScoreSheetTemplate
    **/
   @Override
    public ScoreSheetTemplate findTemplateByProjectNameAndNumber(ScoreSheetTemplate scoreSheetTemplate) {
        logger.info("根据项目名称和项目编号查询评分表模板的详情信息");
        return scoreSheetTemplateMapper.findTemplateByProjectNameAndNumber(scoreSheetTemplate);
    }

   /**
   * @Author xulei
   * @Description 根据id查询模板，并修改数据
   * @Date 14:01 2019/10/24/024
   * @Param [scoreSheetTemplate]
   * @return com.avic.model.ScoreSheetTemplate
   **/
   @Override
    public ScoreSheetTemplate findTemplateById(ScoreSheetTemplate scoreSheetTemplate) {
        logger.info("根据模板id查询评分表模板的详情信息");
        return scoreSheetTemplateMapper.findTemplateById(scoreSheetTemplate);
    }

    /**
    * @Author xulei
    * @Description 针对评标打分模板：生效或者失效
    * @Date 9:54 2019/10/17/017
    * @Param [scoreSheetTemplate]
    * @return java.lang.Boolean
    **/
    @Override
    public Map<String, Object>  enableEffectiveOrNot(ScoreSheetTemplate scoreSheetTemplate) {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("success", "true");
        modelMap.put("msg", "");

        // 先去数据查询数据
        ScoreSheetTemplate result = scoreSheetTemplateMapper.findTemplateById(scoreSheetTemplate);
        if (result != null && result.getRemove().equals(BidConstant.TEMPLATE_NO_REMOVE)) {
           logger.info("根据项目名称和项目编号查询评标打分模板成功,具体信息为：" + result.toString());
            //查询成功，修改数据，update数据库
            if (result.getStatus().equals(BidConstant.TEMPLATE_NO_ACTIVE)) {
                // 设置为0 生效
                result.setStatus(BidConstant.TEMPLATE_ACTIVE);
                modelMap.put("msg", "评标打分模板已生效！！");
            } else {
                // 设置为1 失效
                result.setStatus(BidConstant.TEMPLATE_NO_ACTIVE);
                modelMap.put("msg", "评标打分模板已失效！！");
            }

            result.setUpdateTime(TimeUtil.getTimeByDefautFormat());
            scoreSheetTemplateMapper.enableEffectiveOrNot(result);

            if (result.getStatus().equals(BidConstant.TEMPLATE_ACTIVE)) {
                // 设置生效之后，说明随后专家会使用该模板，因此可以预先insert到expertscoresheet中，status使用默认值1（未打分）
                // 之后在专家登录页，直接把expertscoresheet表根据projectName+projectNumbe查询，以列表形式展示
                List<ExpertScoreSheet> expertScoreSheetList = expertScoreSheetService.getExpertScoreSheetFromTemplate();
                // 批量执行插入操作
                expertScoreSheetMapper.insertExpertScoreSheetForeach(expertScoreSheetList);
            }

            logger.info("评标打分模板“生效/失效”成功,具体信息为：" + result.toString());

        } else {
            logger.info("该项目名称和项目编码对应的评分模板不存在，请确认后重试！！" + scoreSheetTemplate.toString());
            modelMap.put("success", "false");
            modelMap.put("msg", "评分模板不存在，请确认后重试！！");
            return modelMap;
        }

        return modelMap;
    }

    /**
    * @Author xulei
    * @Description 删除模板--硬删除
     * 根据评分表模板中项目名称 + 项目编码锁定唯一模板，进行删除
    * @Date 9:58 2019/10/17/017
    * @Param [scoreSheetTemplate]
    * @return void
    **/
    @Override
    public Boolean deleteScoreSheetTemplate(ScoreSheetTemplate scoreSheetTemplate) {
        logger.info("删除评分表模板：硬删除");
        // 先去数据查询数据
        ScoreSheetTemplate result = scoreSheetTemplateMapper.findTemplateById(scoreSheetTemplate);
        if (result != null && result.getRemove().equals(BidConstant.TEMPLATE_NO_REMOVE)) {
            logger.info("根据项目名称和项目编号查询将要删除的评标打分模板成功,具体信息为：" + result.toString());
            //查询成功，执行删除操作
            scoreSheetTemplateMapper.deleteScoreSheetTemplate(scoreSheetTemplate);
            logger.info("删除评标打分模板成功");

        } else {
            logger.info("根据项目名称和项目编码查询将要删除的评分模板不存在，请确认后重试！！" + scoreSheetTemplate.toString());
            return false;
        }

        return true;
    }


    /**
    * @Author xulei
    * @Description 向专家下发打分表时，组装分页数据
    * @Date 10:35 2019/10/25/025
    * @Param 
    * @return 
    **/
    /*@Override
    public List<ScoreSheetTemplate> assembleDataForSendScoreTemplateToExpert(List<ScoreSheetTemplate> resultList, Integer whichPage, Integer everyNumber) {
        int dataCount = resultList.size();
        int startNumber = (whichPage - 1) * everyNumber;
        int endNumber = startNumber + everyNumber;

        // 如果总数小于每页显示条数，可以直接返回数据
        if (dataCount <= everyNumber) {
            return resultList;
        }

        // 如果总数比每页显示数据小，则直接遍历list取startNumber和endNumber之间的数据
        List<ScoreSheetTemplate> dataList = new ArrayList<>();
        for (int index = startNumber; index < endNumber && index < dataCount; index++) {
            dataList.add(resultList.get(index));
        }
        return dataList;
    }*/



}
