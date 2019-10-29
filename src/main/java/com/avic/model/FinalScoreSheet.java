package com.avic.model;

/**
 * @author sconglee
 * @date 2019/10/24
 */
public class FinalScoreSheet {

    private String projectNumber;
    private String projectName;
    private String companyName;
    private int isGenerate;
    private String averageScore;
    private int totalScore;
    private String createTime;
    private String updateTime;

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getIsGenerate() {
        return isGenerate;
    }

    public void setIsGenerate(int isGenerate) {
        this.isGenerate = isGenerate;
    }

    public String getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(String averageScore) {
        this.averageScore = averageScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
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
        return "FinalScoreSheet{" +
                "projectNumber='" + projectNumber + '\'' +
                ", projectName='" + projectName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", isGenerate=" + isGenerate +
                ", averageScore='" + averageScore + '\'' +
                ", totalScore=" + totalScore +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
