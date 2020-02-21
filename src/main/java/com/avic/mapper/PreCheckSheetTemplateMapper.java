package com.avic.mapper;

import com.avic.model.PreCheckSheetTemplate;
import com.avic.model.httovo.PaginationRequest;

import java.util.List;

public interface PreCheckSheetTemplateMapper {
    
    /**
    * 新增预审表
    **/
    Integer insertPreCheckSheetTemplate(PreCheckSheetTemplate preCheckSheetTemplate);

    /**
     * 修改预审表
     **/
    void updatePreCheckSheetTemplate(PreCheckSheetTemplate preCheckSheetTemplate);

    /**
     * 查看预审表--根据id
     **/
    PreCheckSheetTemplate findPreCheckSheetTemplateById(Integer id);

    /**
     * 删除预审表--根据id
     **/
    void deletePreCheckSheetTemplateById(Integer id);

    /**
     * 分页查看预审表--起始页号，每页条数
     **/
    Integer findTemplateTotalCount();
    List<PreCheckSheetTemplate> findPreCheckSheetTemplatePagination(PaginationRequest paginationRequest);



}
