package com.avic.service.impl;

import com.avic.common.constant.BidConstant;
import com.avic.common.utils.TimeUtil;
import com.avic.mapper.ScoreSheetTemplateMapper;
import com.avic.model.ScoreSheetTemplate;
import com.avic.service.ScoreSheetTemplateService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
    * @Author xulei
    * @Description 获取所有有效的评标打分模板信息
    * @Date 9:56 2019/10/17/017
    * @Param []
    * @return java.util.List<com.avic.model.ScoreSheetTemplate>
    **/
    @Override
    public List<ScoreSheetTemplate> findAllScoreSheetTemplate() {
        logger.info("获取评分表模板信息列表");
        return scoreSheetTemplateMapper.findAllScoreSheetTemplate();
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
    * @Description 针对评标打分模板：生效或者失效
    * @Date 9:54 2019/10/17/017
    * @Param [scoreSheetTemplate]
    * @return java.lang.Boolean
    **/
    @Override
    public Boolean enableEffectiveOrNot(ScoreSheetTemplate scoreSheetTemplate) {
        logger.info("使评分表模板生效或者失效");
        // 先去数据查询数据
        ScoreSheetTemplate result = scoreSheetTemplateMapper.findTemplateByProjectNameAndNumber(scoreSheetTemplate);
        if (result != null && result.getRemove().equals(BidConstant.TEMPLATE_NO_REMOVE)) {
           logger.info("根据项目名称和项目编号查询评标打分模板成功,具体信息为：" + result.toString());
            //查询成功，修改数据，update数据库
            if (result.getStatus().equals(BidConstant.TEMPLATE_NO_ACTIVE)) {
                // 设置为0 生效
                result.setStatus(BidConstant.TEMPLATE_ACTIVE);
            } else {
                // 设置为1 失效
                result.setStatus(BidConstant.TEMPLATE_NO_ACTIVE);
            }
            result.setUpdateTime(TimeUtil.getTimeByDefautFormat());
            result.setCreateTime(TimeUtil.getTimeByDefautFormat());

            scoreSheetTemplateMapper.enableEffectiveOrNot(result);
            logger.info("评标打分模板“生效/失效”成功,具体信息为：" + result.toString());

        } else {
            logger.info("该项目名称和项目编码对应的评分模板不存在，请确认后重试！！" + result.toString());
            return false;
        }

        return true;
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
        ScoreSheetTemplate result = scoreSheetTemplateMapper.findTemplateByProjectNameAndNumber(scoreSheetTemplate);
        if (result != null && result.getRemove().equals(BidConstant.TEMPLATE_NO_REMOVE)) {
            logger.info("根据项目名称和项目编号查询将要删除的评标打分模板成功,具体信息为：" + result.toString());
            //查询成功，执行删除操作
            scoreSheetTemplateMapper.deleteScoreSheetTemplate(scoreSheetTemplate);
            logger.info("删除评标打分模板成功");

        } else {
            logger.info("根据项目名称和项目编码查询将要删除的评分模板不存在，请确认后重试！！" + result.toString());
            return false;
        }

        return true;
    }





}
