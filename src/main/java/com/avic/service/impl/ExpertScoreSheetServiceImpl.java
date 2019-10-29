package com.avic.service.impl;

import com.avic.mapper.ExpertScoreSheetMapper;
import com.avic.model.ExpertScoreSheet;
import com.avic.model.httovo.PaginationRequest;
import com.avic.service.ExpertScoreSheetService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author sconglee
 * @date 2019/10/17
 */
@Service
public class ExpertScoreSheetServiceImpl implements ExpertScoreSheetService {

    private static final Log logger = LogFactory.getLog(ExpertScoreSheetServiceImpl.class);

    @Autowired
    private ExpertScoreSheetMapper expertScoreSheetMapper;

    @Override
    public int getExportScoreCount(String projectNumber) {
        logger.info("获取已提交的打分列表总数");
        return expertScoreSheetMapper.findExportScoreCount(projectNumber);
    }

    @Override
    public List<Map<String, Object>> getExpertScoreByProjectNumberAndPagination(String projectNumber, PaginationRequest paginationRequest) {
        logger.info("按页查询已提交的评分");
        return expertScoreSheetMapper.findExpertScoreByProjectNumberAndPagination(projectNumber, paginationRequest);
    }

    @Override
    public ExpertScoreSheet getScoreInfoByExpertNameAndCompanyName(ExpertScoreSheet expertScoreSheet) {
        logger.info("查询指定专家、投标单位的得分信息");
        return expertScoreSheetMapper.findScoreInfoByExpertNameAndCompanyName(expertScoreSheet);
    }

    @Override
    public List<ExpertScoreSheet> getScoreInfoByProjectNumberAndCompanyName(ExpertScoreSheet expertScoreSheet) {
        logger.info("查询指定项目编号、投标单位的得分信息");
        return expertScoreSheetMapper.findScoreInfoByProjectNumberAndCompanyName(expertScoreSheet);
    }

    @Override
    public Integer insertExpertScoreSheet(ExpertScoreSheet expertScoreSheet) {
        logger.info("添加专家打分结果：" + expertScoreSheet.toString());
        return expertScoreSheetMapper.insertExpertScoreSheet(expertScoreSheet);
    }

}
