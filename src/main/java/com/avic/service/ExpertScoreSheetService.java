package com.avic.service;

import com.avic.model.ExpertScoreSheet;
import com.avic.model.ScoreSheetTemplate;
import com.avic.model.httovo.ExpertScoreSheetPagination;
import com.avic.model.httovo.PaginationRequest;

import java.util.List;

/**
 * @author sconglee
 * @date 2019/10/17
 */
public interface ExpertScoreSheetService {


    int getExportScoreCount(String projectNumber);

    List<ExpertScoreSheet> getExpertScoreByProjectNumberAndPagination(String projectNumber, PaginationRequest paginationRequest);

    ExpertScoreSheet getScoreInfoByExpertNameAndCompanyName(ExpertScoreSheet expertScoreSheet);

    List<ExpertScoreSheet> getScoreInfoByProjectNumberAndCompanyName(ExpertScoreSheet expertScoreSheet);

    Integer updateExpertScoreSheet(ExpertScoreSheet expertScoreSheet);

    List<ExpertScoreSheet> getExpertScoreSheetFromTemplate();

    Integer findScoreSheetTotalCount(ExpertScoreSheetPagination expertScoreSheetPagination);

    /**
     分页查询
     **/
    List<ExpertScoreSheet> findScoreSheetPagination(ExpertScoreSheetPagination expertScoreSheetPagination);

    List<ExpertScoreSheet> getExpertScoreByProjectNumberAndProjectNumber(ExpertScoreSheet expertScoreSheet);
}
