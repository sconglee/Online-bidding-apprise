package com.avic.model;

import java.io.Serializable;

/**
 * @ClassName PreFormTemplate
 * @Description 初审（预审）表信息
 * @Author xulei
 * @Date 2020/2/19/019 10:10
 * @Version 1.0
 **/
public class PreCheckSheetTemplate implements Serializable {

    private Integer id;
    /**
    * 初审表格的名称 title
    **/
    private String sheetTitle;
    /**
     * 项目名称
     **/
    private String projectName;
    /**
     * 项目编号
     **/
    private String projectNumber;
    /**
     * 评标时间
     **/
    private String evaluateBidTime;
    /**
     * 评标地址
     **/
    private String evaluateBidAddr;
    /**
     * 审核项
     **/
    private String checkItems;
    /**
     * 投标单位
     **/
    private String bidderComName;
    /**
     * 初审结论（结果）
     **/
    private String preCheckResult;

    private String createTime;
    private String updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSheetTitle() {
        return sheetTitle;
    }

    public void setSheetTitle(String sheetTitle) {
        this.sheetTitle = sheetTitle;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getEvaluateBidTime() {
        return evaluateBidTime;
    }

    public void setEvaluateBidTime(String evaluateBidTime) {
        this.evaluateBidTime = evaluateBidTime;
    }

    public String getEvaluateBidAddr() {
        return evaluateBidAddr;
    }

    public void setEvaluateBidAddr(String evaluateBidAddr) {
        this.evaluateBidAddr = evaluateBidAddr;
    }

    public String getCheckItems() {
        return checkItems;
    }

    public void setCheckItems(String checkItems) {
        this.checkItems = checkItems;
    }

    public String getBidderComName() {
        return bidderComName;
    }

    public void setBidderComName(String bidderComName) {
        this.bidderComName = bidderComName;
    }

    public String getPreCheckResult() {
        return preCheckResult;
    }

    public void setPreCheckResult(String preCheckResult) {
        this.preCheckResult = preCheckResult;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "PreCheckSheetTemplate{" +
                "id=" + id +
                ", sheetTitle='" + sheetTitle + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectNumber='" + projectNumber + '\'' +
                ", evaluateBidTime='" + evaluateBidTime + '\'' +
                ", evaluateBidAddr='" + evaluateBidAddr + '\'' +
                ", checkItems='" + checkItems + '\'' +
                ", bidderComName='" + bidderComName + '\'' +
                ", preCheckResult='" + preCheckResult + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
