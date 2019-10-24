package com.avic.service;

import com.avic.model.ScoreSheetTemplate;
import com.avic.model.User;

import java.util.List;
import java.util.Map;

/**
* @Author xulei
* @Description 评分表模板
* @Date 10:47 2019/10/16/016
* @Param 
* @return 
**/
public interface ScoreSheetTemplateService {

    List<ScoreSheetTemplate> findAllScoreSheetTemplate();

    Integer createScoreSheetTemplate(ScoreSheetTemplate scoreSheetTemplate);

    void updateScoreSheetTemplate(ScoreSheetTemplate scoreSheetTemplate);

    Boolean deleteScoreSheetTemplate(ScoreSheetTemplate scoreSheetTemplate);

    ScoreSheetTemplate findTemplateByProjectNameAndNumber(ScoreSheetTemplate scoreSheetTemplate);

    ScoreSheetTemplate findTemplateById(ScoreSheetTemplate scoreSheetTemplate);

    Map<String, Object> enableEffectiveOrNot(ScoreSheetTemplate scoreSheetTemplate);

    /**
     * @Author xulei
     * @Description 下发评标打分模板
     * @Date 9:07 2019/10/18/018
     * @Param
     * @return
     **/
    List<ScoreSheetTemplate> sendScoreSheetTemplateToExpert();
}
