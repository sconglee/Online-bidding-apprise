package com.avic.mapper;

import com.avic.model.FinalScoreSheet;
import com.avic.model.httovo.PaginationRequest;

import java.util.List;
import java.util.Map;

/**
 * @author sconglee
 * @date 2019/10/25
 */
public interface FinalScoreSheetMapper {

    int findProjectCount();

    List<Map<String, Object>> findProjectByPagination(PaginationRequest paginationRequest);

    int updateFinalScore(FinalScoreSheet finalScoreSheet);

    FinalScoreSheet findFinalScoreSheetByProjectNumberAndCompanyName(FinalScoreSheet finalScoreSheet);

    FinalScoreSheet findFinalScoreSheetByCondtion(FinalScoreSheet finalScoreSheet);

    int insertFinalScoreSheet(FinalScoreSheet finalScoreSheet);

}
