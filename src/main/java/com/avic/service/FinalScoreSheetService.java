package com.avic.service;

import com.avic.model.FinalScoreSheet;
import com.avic.model.httovo.PaginationRequest;

import java.util.List;

/**
 * @author sconglee
 * @date 2019/10/24
 */
public interface FinalScoreSheetService {

    int getProjectCount();

    List<FinalScoreSheet> getProjectByPagination(PaginationRequest paginationRequest);

    int updateFinalScoreSheet(FinalScoreSheet finalScoreSheet);

    FinalScoreSheet getFinalScoreSheetByProjectNumberAndCompanyName(FinalScoreSheet finalScoreSheet);

    int insertFinalScoreSheet(FinalScoreSheet finalScoreSheet);

}
