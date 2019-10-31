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

import javax.print.MultiDoc;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    private ExpertScoreSheetService expertScoreSheetService;

    @Override
    public int getExportScoreCount(String projectNumber) {
        logger.info("获取已提交的打分列表总数");
        return expertScoreSheetMapper.findExportScoreCount(projectNumber);
    }

    @Override
    public List<Map<String, Object>> getExpertScoreByProjectNumberAndPagination(ExpertScoreSheetPagination expertScoreSheetPagination) {
        logger.info("按页查询已提交的评分");
        return expertScoreSheetMapper.findExpertScoreByProjectNumberAndPagination(expertScoreSheetPagination);
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
    public List<ExpertScoreSheet> getExpertScoreSheetFromTemplate(String expertName) {
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
                expertScoreSheet.setExpertName(expertName);
                expertScoreSheet.setCreateTime(scoreSheetTemplate.getCreateTime());
                expertScoreSheet.setUpdateTime(scoreSheetTemplate.getUpdateTime());

                resultList.add(expertScoreSheet);
            }
        }
        System.out.println("数组长度：" + resultList.size());
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
    public List<ExpertScoreSheet> getExpertScoreByProjectNameAndProjectNumber(ExpertScoreSheet expertScoreSheet) {
        logger.info("根据项目名称+项目编号获取专家打分表信息：");
        return expertScoreSheetMapper.getExpertScoreByProjectNumberAndProjectNumber(expertScoreSheet);
    }

    @Override
    public Map<String, Object> findScoreSheetPaginationInfo(ScoreSheetTemplate scoreSheetTemplate,ExpertScoreSheetPagination expertScoreSheetPagination) {
        Map<String, Object> modelMap = new HashMap<>();

        //1、根据上面scoreSheetTemplate中的name和number，查询expertScoreSheet表
        int whichPage = expertScoreSheetPagination.getPage();
        int everyNumber = expertScoreSheetPagination.getColumns();
        expertScoreSheetPagination.setStartNumber((whichPage - 1) * everyNumber);

        logger.info("专家打分列表的查询条件：projectName：" + scoreSheetTemplate.getProjectName()
                + "  ,projectNumber:" + scoreSheetTemplate.getProjectNumber()
                + "   ,expertName: " + expertScoreSheetPagination.getExpertName());
        expertScoreSheetPagination.setProjectName(scoreSheetTemplate.getProjectName());
        expertScoreSheetPagination.setProjectNumber(scoreSheetTemplate.getProjectNumber());

        List<ExpertScoreSheet> expertScoreSheetPaginationList = new ArrayList<>();
        // 只查询当前处于未打分的表
        // 2、查询数据总数
        Integer count = expertScoreSheetService.findScoreSheetTotalCount(expertScoreSheetPagination);
        logger.info("总数为：" + count);

        // 3、查询当页的数据信息
        expertScoreSheetPaginationList = expertScoreSheetService.findScoreSheetPagination(expertScoreSheetPagination);
        logger.info("数据为：" + expertScoreSheetPaginationList.size());

        if (!expertScoreSheetPaginationList.isEmpty()) {
            modelMap.put("success", "true");
            modelMap.put("page", expertScoreSheetPagination.getPage());
            modelMap.put("count", count);
            modelMap.put("total", (int)Math.ceil((double) count / everyNumber));
            modelMap.put("data", expertScoreSheetPaginationList);
        } else {
            modelMap.put("success", "false");
            modelMap.put("msg", "数据为空！！");
        }

        return modelMap;
    }
}
