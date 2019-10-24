package com.avic.mapper;

import com.avic.model.ScoreSheetTemplate;

import java.util.List;

public interface ScoreSheetTemplateMapper {

    Integer insertScoreSheetTemplate(ScoreSheetTemplate scoreSheetTemplate);

    List<ScoreSheetTemplate> findAllScoreSheetTemplate();

    void updateScoreSheetTemplate(ScoreSheetTemplate scoreSheetTemplate);

    void deleteScoreSheetTemplate(ScoreSheetTemplate scoreSheetTemplate);

    ScoreSheetTemplate findTemplateByProjectNameAndNumber(ScoreSheetTemplate scoreSheetTemplate);

    ScoreSheetTemplate findTemplateById(ScoreSheetTemplate scoreSheetTemplate);

    void enableEffectiveOrNot(ScoreSheetTemplate scoreSheetTemplate);


    /**
     * @Author xulei
     * @Description 下发评标打分模板
     * @Date 9:07 2019/10/18/018
     * @Param
     * @return
     **/
    ScoreSheetTemplate sendScoreSheetTemplateToExpert();
}
