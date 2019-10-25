package com.avic.service;

import com.avic.model.FinalScoreSheet;

import java.util.List;

/**
 * @author sconglee
 * @date 2019/10/24
 */
public interface FinalScoreSheetService {

    List<FinalScoreSheet> getAllProject();

    int updateFinalScoreSheet(FinalScoreSheet finalScoreSheet);

    FinalScoreSheet getFinalScoreSheetByProjectNumberAndCompanyName(FinalScoreSheet finalScoreSheet);

}
