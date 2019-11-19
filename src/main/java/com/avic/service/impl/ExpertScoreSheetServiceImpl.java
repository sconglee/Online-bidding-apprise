package com.avic.service.impl;

import com.avic.common.constant.BidConstant;
import com.avic.common.utils.TimeUtil;
import com.avic.mapper.ExpertScoreSheetMapper;
import com.avic.mapper.ScoreSheetTemplateMapper;
import com.avic.model.ExpertScoreSheet;
import com.avic.model.FinalScoreSheet;
import com.avic.model.ScoreSheetTemplate;
import com.avic.model.httovo.ExpertScoreSheetComAndPoint;
import com.avic.model.httovo.ExpertScoreSheetInsert;
import com.avic.model.httovo.ExpertScoreSheetPagination;
import com.avic.model.httovo.PaginationRequest;
import com.avic.service.ExpertScoreSheetService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.MultiDoc;
import java.io.File;
import java.sql.Time;
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
                expertScoreSheet.setEvaluIndexDesc(scoreSheetTemplate.getEvaluIndexDesc());
                expertScoreSheet.setDescription(scoreSheetTemplate.getDescription());

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

    @Override
    public Integer updateExpertScoreSheetForeach(List<ExpertScoreSheet> expertScoreSheetList) {
        logger.info("修改模板之后，根据项目名称 + 项目编号 批量修改expertScoreSheet：" );
        return expertScoreSheetMapper.updateExpertScoreSheetForeach(expertScoreSheetList);
    }

    @Override
    public Integer insertExpertScoreSheetForeach(List<ExpertScoreSheet> expertScoreSheetList) {
        logger.info("专家在一个表单中，同时对所有单位评价，然后批量插入expertScoreSheet表:" );

        for (ExpertScoreSheet expertScoreSheet : expertScoreSheetList) {
            expertScoreSheet.setCreateTime(TimeUtil.getTimeByDefautFormat());
            expertScoreSheet.setUpdateTime(TimeUtil.getTimeByDefautFormat());

            System.out.println("打分状态数据：status = " + expertScoreSheet.getStatus());
        }

        return expertScoreSheetMapper.insertExpertScoreSheetForeach(expertScoreSheetList);
    }

    @Override
    public List<ExpertScoreSheet> getExpertScoreSheetList(ExpertScoreSheetPagination expertScoreSheetPagination) {
        logger.info("根据projectName  projectNumber expertName批量获取专家打分结果: projectName = " + expertScoreSheetPagination.getProjectName()
                + ",   projectNumber = " + expertScoreSheetPagination.getProjectNumber()
                + ",    expertName = " + expertScoreSheetPagination.getExpertName());

        return expertScoreSheetMapper.getExpertScoreSheetList(expertScoreSheetPagination);
    }

    @Override
    public Integer deleteExpertScoreByProjectNameAndProjectNumber(ExpertScoreSheet expertScoreSheet) {
        logger.info("修改评分模板后，根据projectName  projectNumber 批量删除老的打分结果: projectName = " + expertScoreSheet.getProjectName()
                + ",   projectNumber = " + expertScoreSheet.getProjectNumber());
        return expertScoreSheetMapper.deleteExpertScoreByProjectNameAndProjectNumber(expertScoreSheet);
    }

    @Override
    public HashMap<String, List> getInsertExpertScoreSheetData(ExpertScoreSheetInsert expertScoreSheetInsert){
        HashMap<String,List> hashMap = new HashMap<>();

        List<ExpertScoreSheetComAndPoint> expertScoreSheetComAndPointList = expertScoreSheetInsert.getExpertScoreSheetComAndPointList();
        List<ExpertScoreSheet> expertScoreSheetList = new ArrayList<>();
        List<FinalScoreSheet> finalScoreSheetList = new ArrayList<>();

        for (ExpertScoreSheetComAndPoint expertScoreSheetComAndPoint : expertScoreSheetComAndPointList) {
            ExpertScoreSheet expertScoreSheet = new ExpertScoreSheet();
            FinalScoreSheet finalScoreSheet = new FinalScoreSheet();

            expertScoreSheet.setProjectName(expertScoreSheetInsert.getProjectName());
            expertScoreSheet.setProjectNumber(expertScoreSheetInsert.getProjectNumber());
            expertScoreSheet.setExpertName(expertScoreSheetInsert.getExpertName());
            expertScoreSheet.setItemWeight(expertScoreSheetInsert.getItemWeight());
            expertScoreSheet.setTotalItems(expertScoreSheetInsert.getTotalItems());
            expertScoreSheet.setTotalItems(expertScoreSheetInsert.getItemCount());
            expertScoreSheet.setSequenceNumber(expertScoreSheetInsert.getSequenceNumber());
            expertScoreSheet.setEvaluIndexDesc(expertScoreSheetInsert.getEvaluIndexDesc());
            expertScoreSheet.setDescription(expertScoreSheetInsert.getDescription());
            expertScoreSheet.setCompanyName(expertScoreSheetComAndPoint.getCompanyName());
            expertScoreSheet.setPoint(expertScoreSheetComAndPoint.getPoint());
            expertScoreSheet.setCreateTime(TimeUtil.getTimeByDefautFormat());
            expertScoreSheet.setUpdateTime(TimeUtil.getTimeByDefautFormat());


            finalScoreSheet.setProjectName(expertScoreSheetInsert.getProjectName());
            finalScoreSheet.setProjectNumber(expertScoreSheetInsert.getProjectNumber());
            finalScoreSheet.setCompanyName(expertScoreSheetComAndPoint.getCompanyName());
            finalScoreSheet.setIsGenerate(0);
            finalScoreSheet.setCreateTime(TimeUtil.getTimeByDefautFormat());
            finalScoreSheet.setUpdateTime(TimeUtil.getTimeByDefautFormat());

            expertScoreSheetList.add(expertScoreSheet);
            finalScoreSheetList.add(finalScoreSheet);
        }

        hashMap.put("expertScoreSheet", expertScoreSheetList);
        hashMap.put("finalScoreSheet", finalScoreSheetList);
        return hashMap;
    }

    @Override
    public ExpertScoreSheetInsert getExpertScoreSheetInsertToWeb(List<ExpertScoreSheet> expertScoreSheetList) {
        ExpertScoreSheetInsert expertScoreSheetInsert = new ExpertScoreSheetInsert();

        ExpertScoreSheet expertScoreSheet = expertScoreSheetList.get(0);
        expertScoreSheetInsert.setProjectName(expertScoreSheet.getProjectName());
        expertScoreSheetInsert.setProjectNumber(expertScoreSheet.getProjectNumber());
        expertScoreSheetInsert.setExpertName(expertScoreSheet.getExpertName());
        expertScoreSheetInsert.setItemWeight(expertScoreSheet.getItemWeight());
        expertScoreSheetInsert.setTotalItems(expertScoreSheet.getTotalItems());
        expertScoreSheetInsert.setItemCount(expertScoreSheet.getItemCount());
        expertScoreSheetInsert.setSequenceNumber(expertScoreSheet.getSequenceNumber());
        expertScoreSheetInsert.setEvaluIndexDesc(expertScoreSheet.getEvaluIndexDesc());
        expertScoreSheetInsert.setDescription(expertScoreSheet.getDescription());

        List<ExpertScoreSheetComAndPoint> expertScoreSheetComAndPointList = new ArrayList<>();
        for (ExpertScoreSheet temp : expertScoreSheetList) {
            ExpertScoreSheetComAndPoint expertScoreSheetComAndPoint = new ExpertScoreSheetComAndPoint();
            expertScoreSheetComAndPoint.setCompanyName(temp.getCompanyName());
            expertScoreSheetComAndPoint.setPoint(temp.getPoint());
            expertScoreSheetComAndPointList.add(expertScoreSheetComAndPoint);
        }
        expertScoreSheetInsert.setExpertScoreSheetComAndPointList(expertScoreSheetComAndPointList);

        return expertScoreSheetInsert;
    }


    /**
    * @Author xulei
    * @Description 获取每次生成pdf的数据：3家单位的评分
    * @Date 15:36 2019/11/17/017
    * @Param [expertScoreSheetInsert, times]
    * @return com.avic.model.httovo.ExpertScoreSheetInsert
    **/
    @Override
    public ExpertScoreSheetInsert getExpertScoreSheetDataForCreatePDF(ExpertScoreSheetInsert expertScoreSheetInsert, int times) {
        ExpertScoreSheetInsert resultData = new ExpertScoreSheetInsert();

        resultData.setProjectName(expertScoreSheetInsert.getProjectName());
        resultData.setProjectNumber(expertScoreSheetInsert.getProjectNumber());
        resultData.setExpertName(expertScoreSheetInsert.getExpertName());
        resultData.setItemWeight(expertScoreSheetInsert.getItemWeight());
        resultData.setTotalItems(expertScoreSheetInsert.getTotalItems());
        resultData.setItemCount(expertScoreSheetInsert.getItemCount());
        resultData.setSequenceNumber(expertScoreSheetInsert.getSequenceNumber());
        resultData.setEvaluIndexDesc(expertScoreSheetInsert.getEvaluIndexDesc());
        resultData.setDescription(expertScoreSheetInsert.getDescription());

        List<ExpertScoreSheetComAndPoint> expertScoreSheetComAndPointList = new ArrayList<>();

        List<ExpertScoreSheetComAndPoint> comAndPointListResultData = expertScoreSheetInsert.getExpertScoreSheetComAndPointList();
        int currentLength = (1 + times) * BidConstant.companyNumberSinglePDF;
        int endLength = expertScoreSheetInsert.getExpertScoreSheetComAndPointList().size();
        int count = currentLength < endLength ?  currentLength : endLength;

        for (int i = times * BidConstant.companyNumberSinglePDF; i < count; i++) {
            ExpertScoreSheetComAndPoint expertScoreSheetComAndPoint = new ExpertScoreSheetComAndPoint();
            expertScoreSheetComAndPoint.setCompanyName(comAndPointListResultData.get(i).getCompanyName());
            expertScoreSheetComAndPoint.setPoint(comAndPointListResultData.get(i).getPoint());
            expertScoreSheetComAndPointList.add(expertScoreSheetComAndPoint);
        }

        resultData.setExpertScoreSheetComAndPointList(expertScoreSheetComAndPointList);
        return resultData;
    }

    @Override
    public String getSaveZipPath(String url) {
        File file = new File(url);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                // 判断是否有已经存在zip文件，如果有就删除。
                if (f.getName().endsWith(".zip")) {
                    f.delete();
                   logger.info("删除zip！！");
                }
            }
        }
        // 新建zip文件
        String newUrl = url + "pdfFile.zip";
        File fileNew = new File(newUrl);
        logger.info("zip包文件的绝对路径：" + fileNew.getAbsolutePath());

        return newUrl;
    }


}
