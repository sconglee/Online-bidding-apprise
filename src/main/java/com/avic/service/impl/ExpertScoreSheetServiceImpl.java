package com.avic.service.impl;

import com.avic.mapper.ExpertScoreSheetMapper;
import com.avic.mapper.ScoreSheetTemplateMapper;
import com.avic.model.ExpertScoreSheet;
import com.avic.model.ScoreSheetTemplate;
import com.avic.model.httovo.ExpertScoreSheetPagination;
import com.avic.model.httovo.PaginationRequest;
import com.avic.service.ExpertScoreSheetService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private ScoreSheetTemplateMapper scoreSheetTemplateMapper;

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
    public Integer updateExpertScoreSheet(ExpertScoreSheet expertScoreSheet) {
        logger.info("添加专家打分结果：" + expertScoreSheet.toString());
        return expertScoreSheetMapper.updateExpertScoreSheet(expertScoreSheet);
    }

    @Override
    public List<ExpertScoreSheet> getExpertScoreSheetFromTemplate() {
        List<ExpertScoreSheet> resultList = new ArrayList<>();

        ScoreSheetTemplate scoreSheetTemplate = scoreSheetTemplateMapper.sendScoreSheetTemplateToExpert();
        if (scoreSheetTemplate != null) {
            // 使用","分割出每一个投标单位
            String[] companyName = scoreSheetTemplate.getScoredComName().split(",");
            logger.info("待评标公司如下：" + companyName.toString());

            for (int i = 0; i < companyName.length; i++){
                ExpertScoreSheet expertScoreSheet = new ExpertScoreSheet();
                expertScoreSheet.setProjectName(scoreSheetTemplate.getProjectName());
                expertScoreSheet.setProjectNumber(scoreSheetTemplate.getProjectNumber());
                expertScoreSheet.setTotalItems(scoreSheetTemplate.getTotalItems());
                expertScoreSheet.setItemWeight(scoreSheetTemplate.getItemWeight());
                expertScoreSheet.setSequenceNumber(scoreSheetTemplate.getSequenceNumber());
                expertScoreSheet.setCompanyName(companyName[i]);
                expertScoreSheet.setCreateTime(scoreSheetTemplate.getCreateTime());
                expertScoreSheet.setUpdateTime(scoreSheetTemplate.getUpdateTime());

                resultList.add(expertScoreSheet);
            }
        }
        return resultList;
    }

    @Override
    public Integer findScoreSheetTotalCount(ExpertScoreSheetPagination expertScoreSheetPagination) {
        logger.info("获取专家评分表模板信息列表--总数：");
        return expertScoreSheetMapper.findScoreSheetTotalCount(expertScoreSheetPagination);
    }

    @Override
    public List<ExpertScoreSheet> findScoreSheetPagination(ExpertScoreSheetPagination expertScoreSheetPagination) {
        logger.info("获取评分表模板信息列表--分页查询：");
        return expertScoreSheetMapper.findScoreSheetPagination(expertScoreSheetPagination);
    }

    @Override
    public List<ExpertScoreSheet> getExpertScoreByProjectNumberAndProjectNumber(ExpertScoreSheet expertScoreSheet) {
        logger.info("根据项目名称+项目编号获取专家打分表信息：");
        return expertScoreSheetMapper.getExpertScoreByProjectNumberAndProjectNumber(expertScoreSheet);
    }

    /**
     * @Author xulei
     * @Description 下发评标打分模板
     * @Date 9:09 2019/10/18/018
     * @Param []
     * @return java.util.List<com.avic.model.ScoreSheetTemplate>
     **/
    /*@Override
    public List<ScoreSheetTemplate> sendScoreSheetTemplateToExpert() {
        logger.info("向评标专家推送评标打分模板：");
        List<ScoreSheetTemplate> resultList = new ArrayList<>();*/

        /*ScoreSheetTemplate scoreSheetTemplate = scoreSheetTemplateMapper.sendScoreSheetTemplateToExpert();
        if (scoreSheetTemplate != null) {
            // 使用","分割出每一个投标单位
            String[] companyName = scoreSheetTemplate.getScoredComName().split(",");
            logger.info("待评标公司如下：" + companyName.toString());

            for (int i = 0; i < companyName.length; i++){
                ScoreSheetTemplate tempTemplate = new ScoreSheetTemplate();
                tempTemplate.setProjectName(scoreSheetTemplate.getProjectName());
                tempTemplate.setProjectNumber(scoreSheetTemplate.getProjectNumber());
                tempTemplate.setTotalItems(scoreSheetTemplate.getTotalItems());
                tempTemplate.setSequenceNumber(scoreSheetTemplate.getSequenceNumber());
                tempTemplate.setItemWeight(scoreSheetTemplate.getItemWeight());
                tempTemplate.setScoredComName(companyName[i]);
                tempTemplate.setStatus(scoreSheetTemplate.getStatus());
                tempTemplate.setRemove(scoreSheetTemplate.getRemove());
                tempTemplate.setCreateTime(scoreSheetTemplate.getCreateTime());
                tempTemplate.setUpdateTime(scoreSheetTemplate.getUpdateTime());

                resultList.add(tempTemplate);
            }
        }*/

        /*return resultList;
    }*/

}
