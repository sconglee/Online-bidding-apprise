package com.avic.mapper;

import com.avic.model.FinalScoreSheet;

import java.util.List;

/**
 * @author sconglee
 * @date 2019/10/25
 */
public interface FinalScoreSheetMapper {

    List<FinalScoreSheet> findAllProject();

    int updateFinalScore(FinalScoreSheet finalScoreSheet);

    FinalScoreSheet findFinalScoreSheetByProjectNumberAndCompanyName(FinalScoreSheet finalScoreSheet);


}
