package com.avic.mapper;

import com.avic.model.ExpertScoreSheet;
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

    List<Map<String, Object>> findExpertScoreByProjectNumberAndPagination(@Param("projectNumber") String projectNumber, PaginationRequest paginationRequest);

    ExpertScoreSheet findScoreInfoByExpertNameAndCompanyName(ExpertScoreSheet expertScoreSheet);

    List<ExpertScoreSheet> findScoreInfoByProjectNumberAndCompanyName(ExpertScoreSheet expertScoreSheet);

    Integer insertExpertScoreSheet(ExpertScoreSheet expertScoreSheet);


}
