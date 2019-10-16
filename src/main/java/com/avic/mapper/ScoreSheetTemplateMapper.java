package com.avic.mapper;

import com.avic.model.ScoreSheetTemplate;

import java.util.List;

public interface ScoreSheetTemplateMapper {

    Integer insertScoreSheetTemplate(ScoreSheetTemplate scoreSheetTemplate);

    List<ScoreSheetTemplate> findAllScoreSheetTemplate();

    void updateScoreSheetTemplate(ScoreSheetTemplate scoreSheetTemplate);

    void deleteScoreSheetTemplate(ScoreSheetTemplate scoreSheetTemplate);

    ScoreSheetTemplate findTemplateByProjectNameAndNumber(ScoreSheetTemplate scoreSheetTemplate);

    void enableEffectiveOrNot(ScoreSheetTemplate scoreSheetTemplate);
}
