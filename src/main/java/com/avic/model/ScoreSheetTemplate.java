package com.avic.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ScoreSheetTemplateMapper
 * @Description TODO
 * @Author xulei
 * @Date 2019/10/16/016 10:48
 * @Version 1.0
 **/
public class ScoreSheetTemplate implements Serializable {

    private Integer id;
    private String projectName;
    private String projectNumber;
    private String totalItems;
    private String sequenceNumber;
    private String itemWeight;
    private String scoredComName;
    private Integer status;
    private Integer remove;
    private String createTime;
    private String updateTime;

    private String evaluIndexDesc;
    private String description ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(String itemWeight) {
        this.itemWeight = itemWeight;
    }

    public String getScoredComName() {
        return scoredComName;
    }

    public void setScoredComName(String scoredComName) {
        this.scoredComName = scoredComName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRemove() {
        return remove;
    }

    public void setRemove(Integer remove) {
        this.remove = remove;
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

    public String getEvaluIndexDesc() {
        return evaluIndexDesc;
    }

    public void setEvaluIndexDesc(String evaluIndexDesc) {
        this.evaluIndexDesc = evaluIndexDesc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ScoreSheetTemplate{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", projectNumber='" + projectNumber + '\'' +
                ", totalItems='" + totalItems + '\'' +
                ", sequenceNumber='" + sequenceNumber + '\'' +
                ", itemWeight='" + itemWeight + '\'' +
                ", scoredComName='" + scoredComName + '\'' +
                ", status=" + status +
                ", remove=" + remove +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", evaluIndexDesc='" + evaluIndexDesc + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
