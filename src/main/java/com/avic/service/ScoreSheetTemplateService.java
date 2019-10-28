package com.avic.service;

import com.avic.model.ScoreSheetTemplate;
import com.avic.model.User;
import com.avic.model.httovo.PaginationRequest;

import java.lang.reflect.Array;
import java.util.ArrayList;
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

    Integer findTemplateTotalCount();
    
    /**
    分页查询
    **/
    List<ScoreSheetTemplate> findTemplatePagination(PaginationRequest paginationRequest);

    Integer createScoreSheetTemplate(ScoreSheetTemplate scoreSheetTemplate);

    void updateScoreSheetTemplate(ScoreSheetTemplate scoreSheetTemplate);

    Boolean deleteScoreSheetTemplate(ScoreSheetTemplate scoreSheetTemplate);

    ScoreSheetTemplate findTemplateByProjectNameAndNumber(ScoreSheetTemplate scoreSheetTemplate);

    ScoreSheetTemplate findTemplateById(ScoreSheetTemplate scoreSheetTemplate);

    Map<String, Object> enableEffectiveOrNot(ScoreSheetTemplate scoreSheetTemplate);


    /*List<ScoreSheetTemplate> assembleDataForSendScoreTemplateToExpert(List<ScoreSheetTemplate> resultList, Integer whichPage, Integer everyNumber);*/

}
