package com.avic.service;

import com.avic.model.ExpertScoreSheet;

import java.util.List;

/**
 * @author sconglee
 * @date 2019/10/17
 */
public interface ExpertScoreSheetService {

    public List<ExpertScoreSheet> getAllExpertScoreByProjectNumber(String projectnumber);

    public ExpertScoreSheet getScoreInfoByExpertNameAndCompanyName(ExpertScoreSheet expertScoreSheet);



}
