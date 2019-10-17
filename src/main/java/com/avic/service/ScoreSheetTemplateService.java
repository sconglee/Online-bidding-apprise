package com.avic.service;

import com.avic.model.ScoreSheetTemplate;
import com.avic.model.User;

import java.util.List;

/**
* @Author xulei
* @Description 评分表模板
* @Date 10:47 2019/10/16/016
* @Param 
* @return 
**/
public interface ScoreSheetTemplateService {

    public List<ScoreSheetTemplate> findAllScoreSheetTemplate();

    public Integer createScoreSheetTemplate(ScoreSheetTemplate scoreSheetTemplate);

    public void updateScoreSheetTemplate(ScoreSheetTemplate scoreSheetTemplate);

    public Boolean deleteScoreSheetTemplate(ScoreSheetTemplate scoreSheetTemplate);

    public ScoreSheetTemplate findTemplateByProjectNameAndNumber(ScoreSheetTemplate scoreSheetTemplate);

    public Boolean enableEffectiveOrNot(ScoreSheetTemplate scoreSheetTemplate);


}
