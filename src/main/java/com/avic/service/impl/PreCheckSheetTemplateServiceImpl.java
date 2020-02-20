package com.avic.service.impl;

import com.avic.common.constant.BidConstant;
import com.avic.mapper.PreCheckSheetTemplateMapper;
import com.avic.model.PreCheckSheetTemplate;
import com.avic.model.ScoreSheetTemplate;
import com.avic.model.httovo.PaginationRequest;
import com.avic.service.PreCheckSheetTemplateService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PreCheckSheetTemplateServiceImpl
 * @Description TODO
 * @Author xulei
 * @Date 2020/2/19/019 13:44
 * @Version 1.0
 **/
@Service
public class PreCheckSheetTemplateServiceImpl implements PreCheckSheetTemplateService {
    private static final Log logger = LogFactory.getLog(PreCheckSheetTemplateServiceImpl.class);

    @Autowired
    private PreCheckSheetTemplateMapper preCheckSheetTemplateMapper;

    /**
    * @Author xulei
    * @Description 新增预审表
    * @Date 13:53 2020/2/19/019
    * @Param [preCheckSheetTemplate]
    * @return java.lang.Integer
    **/
    @Override
    public Integer insertPreCheckSheetTemplate(PreCheckSheetTemplate preCheckSheetTemplate) {
        logger.info("添加新的预审表信息：");
        return preCheckSheetTemplateMapper.insertPreCheckSheetTemplate(preCheckSheetTemplate);
    }

    /**
    * @Author xulei
    * @Description 修改预审表
    * @Date 13:53 2020/2/19/019
    * @Param [preCheckSheetTemplate]
    * @return void
    **/
    @Override
    public void updatePreCheckSheetTemplate(PreCheckSheetTemplate preCheckSheetTemplate) {
        logger.info("修改预审表信息：");
        preCheckSheetTemplateMapper.updatePreCheckSheetTemplate(preCheckSheetTemplate);
    }

    /**
    * @Author xulei
    * @Description 查看预审表详情--根据id
    * @Date 13:53 2020/2/19/019
    * @Param [id]
    * @return com.avic.model.PreCheckSheetTemplate
    **/
    @Override
    public PreCheckSheetTemplate findPreCheckSheetTemplateById(Integer id) {
        logger.info("根据预审表id查询预审表详情，查询条件id值是：" + id);
        return preCheckSheetTemplateMapper.findPreCheckSheetTemplateById(id);
    }

    /**
    * @Author xulei
    * @Description 删除预审表--根据id
    * @Date 13:54 2020/2/19/019
    * @Param [id]
    * @return void
    **/
    @Override
    public Boolean deletePreCheckSheetTemplateById(Integer id) {
        logger.info("根据预审表中id信息删除某条数据，删除条件id值是：" + id);
        // 先去数据查询数据
        PreCheckSheetTemplate result = preCheckSheetTemplateMapper.findPreCheckSheetTemplateById(id);
        if (result != null) {
            logger.info("根据id号查询将要删除的评标打分模板成功,具体信息为：" + result.toString());
            //查询成功，执行删除操作
            preCheckSheetTemplateMapper.deletePreCheckSheetTemplateById(id);
            logger.info("删除评标打分模板成功");

        } else {
            logger.info("根据项目名称和项目编码查询将要删除的评分模板不存在，请确认id号后重试！id信息为：" + id);
            return false;
        }
        return true;
    }

    /**
    * @Author xulei
    * @Description 分页查看预审表--总条数
    * @Date 13:54 2020/2/19/019
    * @Param []
    * @return java.lang.Integer
    **/
    @Override
    public Integer findTemplateTotalCount() {
        logger.info("获取预审表信息列表--总数：");
        return preCheckSheetTemplateMapper.findTemplateTotalCount();
    }

    /**
     * @Author xulei
     * @Description 分页查看预审表--当前页数据
     * @Date 13:54 2020/2/19/019
     * @Param []
     * @return java.lang.Integer
     **/
    @Override
    public List<PreCheckSheetTemplate> findPreCheckSheetTemplatePagination(PaginationRequest paginationRequest) {
        logger.info("获取预审表信息列表--分页查询：");
        return preCheckSheetTemplateMapper.findPreCheckSheetTemplatePagination(paginationRequest);
    }


}
