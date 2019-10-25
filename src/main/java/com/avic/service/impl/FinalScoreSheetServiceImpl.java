package com.avic.service.impl;

import com.avic.mapper.FinalScoreSheetMapper;
import com.avic.model.FinalScoreSheet;
import com.avic.service.FinalScoreSheetService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sconglee
 * @date 2019/10/24
 */
@Service
public class FinalScoreSheetServiceImpl implements FinalScoreSheetService {
    private static final Log logger = LogFactory.getLog(FinalScoreSheetServiceImpl.class);

    @Autowired
    private FinalScoreSheetMapper finalScoreSheetMapper;

    @Override
    public List<FinalScoreSheet> getAllProject() {
        logger.info("查询综合得分表项目列表");
        return finalScoreSheetMapper.findAllProject();
    }

    @Override
    public int updateFinalScoreSheet(FinalScoreSheet finalScoreSheet) {
        logger.info("更新生成状态，最后得分，总得分");
        return finalScoreSheetMapper.updateFinalScore(finalScoreSheet);
    }

    @Override
    public FinalScoreSheet getFinalScoreSheetByProjectNumberAndCompanyName(FinalScoreSheet finalScoreSheet) {
        logger.info("查询得分详情");
        return finalScoreSheetMapper.findFinalScoreSheetByProjectNumberAndCompanyName(finalScoreSheet);
    }

    @Override
    public int insertFinalScoreSheet(FinalScoreSheet finalScoreSheet) {
        logger.info("插入数据：");
        return finalScoreSheetMapper.insertFinalScoreSheet(finalScoreSheet);
    }
}
