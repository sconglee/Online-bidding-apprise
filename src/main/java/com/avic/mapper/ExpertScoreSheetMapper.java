package com.avic.mapper;

import com.avic.model.ExpertScoreSheet;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author sconglee
 * @date 2019/10/17
 */
public interface ExpertScoreSheetMapper {

    public List<ExpertScoreSheet> findAllExpertScoreByProjectNumber(@Param("projectNumber") String projectNumber);

    public ExpertScoreSheet findScoreInfoByExpertNameAndCompanyName(ExpertScoreSheet expertScoreSheet);


}
