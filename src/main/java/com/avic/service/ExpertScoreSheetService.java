package com.avic.service;

import com.avic.model.ExpertScoreSheet;
import com.avic.model.ScoreSheetTemplate;
import com.avic.model.httovo.ExpertScoreSheetInsert;
import com.avic.model.httovo.ExpertScoreSheetPagination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sconglee
 * @date 2019/10/17
 */
public interface ExpertScoreSheetService {


    int getExportScoreCount(String projectNumber);

    List<Map<String, Object>> getExpertScoreByProjectNumberAndPagination(ExpertScoreSheetPagination expertScoreSheetPagination);

    ExpertScoreSheet getScoreInfoByExpertNameAndCompanyName(ExpertScoreSheet expertScoreSheet);

    List<ExpertScoreSheet> getScoreInfoByProjectNumberAndCompanyName(ExpertScoreSheet expertScoreSheet);

    Integer updateExpertScoreSheet(ExpertScoreSheet expertScoreSheet);

    List<ExpertScoreSheet> getExpertScoreSheetFromTemplate(String expertName);


    /**
     分页查询
     **/
    Integer findScoreSheetTotalCount(ExpertScoreSheetPagination expertScoreSheetPagination);
    List<ExpertScoreSheet> findScoreSheetPagination(ExpertScoreSheetPagination expertScoreSheetPagination);
    Map<String,Object> findScoreSheetPaginationInfo(ScoreSheetTemplate scoreSheetTemplate, ExpertScoreSheetPagination expertScoreSheetPagination);


    List<ExpertScoreSheet> getExpertScoreByProjectNameAndProjectNumber(ExpertScoreSheet expertScoreSheet);

    Integer updateExpertScoreSheetForeach(List<ExpertScoreSheet> expertScoreSheetList);

    Integer insertExpertScoreSheetForeach(List<ExpertScoreSheet> expertScoreSheetList);

    List<ExpertScoreSheet> getExpertScoreSheetList(ExpertScoreSheetPagination expertScoreSheetPagination);

    Integer deleteExpertScoreByProjectNameAndProjectNumber(ExpertScoreSheet expertScoreSheet);

    HashMap<String,List> getInsertExpertScoreSheetData(ExpertScoreSheetInsert expertScoreSheetInsert);

    ExpertScoreSheetInsert getExpertScoreSheetInsertToWeb(List<ExpertScoreSheet> expertScoreSheetList);

}
