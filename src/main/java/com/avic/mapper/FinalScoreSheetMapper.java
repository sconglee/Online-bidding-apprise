package com.avic.mapper;

import com.avic.model.FinalScoreSheet;
import com.avic.model.httovo.PaginationRequest;

import java.util.List;

/**
 * @author sconglee
 * @date 2019/10/25
 */
public interface FinalScoreSheetMapper {

    int findProjectCount();

    List<FinalScoreSheet> findProjectByPagination(PaginationRequest paginationRequest);

    int updateFinalScore(FinalScoreSheet finalScoreSheet);

    FinalScoreSheet findFinalScoreSheetByProjectNumberAndCompanyName(FinalScoreSheet finalScoreSheet);


}
