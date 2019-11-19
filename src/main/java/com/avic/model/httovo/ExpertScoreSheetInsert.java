package com.avic.model.httovo;

import java.util.List;

/**
 * @ClassName ExpertScoreSheetInsert
 * @Description 前端批量向后端传输专家打分数据
 * @Author xulei
 * @Date 2019/11/14/014 18:06
 * @Version 1.0
 **/
public class ExpertScoreSheetInsert {

    private String projectName;
    private String projectNumber;
    private String expertName;
    private String itemWeight;
    private String totalItems;
    private String itemCount;
    private String sequenceNumber;
    private String evaluIndexDesc;
    private String description ;

    private List<ExpertScoreSheetComAndPoint> expertScoreSheetComAndPointList;

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

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public String getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(String itemWeight) {
        this.itemWeight = itemWeight;
    }

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
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

    public List<ExpertScoreSheetComAndPoint> getExpertScoreSheetComAndPointList() {
        return expertScoreSheetComAndPointList;
    }

    public void setExpertScoreSheetComAndPointList(List<ExpertScoreSheetComAndPoint> expertScoreSheetComAndPointList) {
        this.expertScoreSheetComAndPointList = expertScoreSheetComAndPointList;
    }

    @Override
    public String toString() {
        return "ExpertScoreSheetInsert{" +
                "projectName='" + projectName + '\'' +
                ", projectNumber='" + projectNumber + '\'' +
                ", expertName='" + expertName + '\'' +
                ", itemWeight='" + itemWeight + '\'' +
                ", totalItems='" + totalItems + '\'' +
                ", itemCount='" + itemCount + '\'' +
                ", sequenceNumber='" + sequenceNumber + '\'' +
                ", evaluIndexDesc='" + evaluIndexDesc + '\'' +
                ", description='" + description + '\'' +
                ", expertScoreSheetComAndPointList=" + expertScoreSheetComAndPointList +
                '}';
    }

}
