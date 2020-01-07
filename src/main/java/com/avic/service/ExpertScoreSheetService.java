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

    /**
    * 获取每次用来生成pdf的数据
     * 由于pdf需要分页，所以需要使用tines标记当前是第几次生成pdf。
    **/
    ExpertScoreSheetInsert getExpertScoreSheetDataForCreatePDF(ExpertScoreSheetInsert expertScoreSheetInsert,int times);

    /**
    * 获取保存zip压缩包的路径
     * 压缩包下保存当前本次请求中的所有pdf
    **/
    String getSaveZipPath(String url, String projectNameForZip, String loginUsername);



}
