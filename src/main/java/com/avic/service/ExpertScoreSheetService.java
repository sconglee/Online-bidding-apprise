package com.avic.service;

import com.avic.model.ExpertScoreSheet;

import java.util.List;

/**
 * @author sconglee
 * @date 2019/10/17
 */
public interface ExpertScoreSheetService {

    List<ExpertScoreSheet> getAllExpertScoreByProjectNumber(String projectNumber);

    ExpertScoreSheet getScoreInfoByExpertNameAndCompanyName(ExpertScoreSheet expertScoreSheet);

    List<ExpertScoreSheet> getScoreInfoByProjectNumberAndCompanyName(ExpertScoreSheet expertScoreSheet);

    Integer insertExpertScoreSheet(ExpertScoreSheet expertScoreSheet);

}
