package com.avic.mapper;

import com.avic.model.ExpertScoreSheet;
import com.avic.model.ScoreSheetTemplate;
import com.avic.model.httovo.ExpertScoreSheetPagination;
import com.avic.model.httovo.PaginationRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author sconglee
 * @date 2019/10/17
 */
public interface ExpertScoreSheetMapper {

    int findExportScoreCount(@Param("projectNumber") String projectNumber);

    List<Map<String, Object>> findExpertScoreByProjectNumberAndPagination(ExpertScoreSheetPagination expertScoreSheetPagination);

    ExpertScoreSheet findScoreInfoByExpertNameAndCompanyName(ExpertScoreSheet expertScoreSheet);

    List<ExpertScoreSheet> findScoreInfoByProjectNumberAndCompanyName(ExpertScoreSheet expertScoreSheet);

    Integer updateExpertScoreSheet(ExpertScoreSheet expertScoreSheet);

    Integer insertExpertScoreSheetForeach(List<ExpertScoreSheet> expertScoreSheetList);

    Integer findScoreSheetTotalCount(ExpertScoreSheetPagination expertScoreSheetPagination);

    /**
     分页查询
     **/
    List<ExpertScoreSheet> findScoreSheetPagination(ExpertScoreSheetPagination expertScoreSheetPagination);

    List<ExpertScoreSheet> getExpertScoreByProjectNumberAndProjectNumber(ExpertScoreSheet expertScoreSheet);

    Integer deleteExpertScoreForeachById(List<ExpertScoreSheet> expertScoreSheetList);

}
