package com.avic.service.impl;

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

    @Override
    public List<ScoreSheetTemplate> findAllScoreSheetTemplate() {
        logger.info("获取评分表模板信息列表");
        return scoreSheetTemplateMapper.findAllScoreSheetTemplate();
    }

    @Override
    public Integer createScoreSheetTemplate(ScoreSheetTemplate scoreSheetTemplate) {
        logger.info("添加新的评分表模板：");
        return scoreSheetTemplateMapper.insertScoreSheetTemplate(scoreSheetTemplate);
    }

    @Override
    public void updateScoreSheetTemplate(ScoreSheetTemplate scoreSheetTemplate) {
        logger.info("修改评分表模板");
        scoreSheetTemplateMapper.updateScoreSheetTemplate(scoreSheetTemplate);
    }

    @Override
    public void deleteScoreSheetTemplate(ScoreSheetTemplate scoreSheetTemplate) {
        logger.info("删除评分表模板：硬删除");
        scoreSheetTemplateMapper.deleteScoreSheetTemplate(scoreSheetTemplate);
    }

    @Override
    public ScoreSheetTemplate findTemplateByProjectNameAndNumber(ScoreSheetTemplate scoreSheetTemplate) {
        logger.info("根据项目名称和项目编号查询评分表模板的详情信息");
        return scoreSheetTemplateMapper.findTemplateByProjectNameAndNumber(scoreSheetTemplate);
    }

    @Override
    public void enableEffectiveOrNot(ScoreSheetTemplate scoreSheetTemplate) {
        logger.info("使评分表模板生效或者失效");
        scoreSheetTemplateMapper.enableEffectiveOrNot(scoreSheetTemplate);
    }
}
