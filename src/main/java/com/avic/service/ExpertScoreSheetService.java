package com.avic.service;

import com.avic.model.ExpertScoreSheet;

import java.util.List;

/**
 * @author sconglee
 * @date 2019/10/17
 */
public interface ExpertScoreSheetService {

    List<ExpertScoreSheet> getAllExpertScoreByProjectNumber(String projectnumber);

    ExpertScoreSheet getScoreInfoByExpertNameAndCompanyName(ExpertScoreSheet expertScoreSheet);

    Integer insertExpertScoreSheet(ExpertScoreSheet expertScoreSheet);

}
