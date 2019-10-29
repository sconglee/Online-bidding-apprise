package com.avic.model.httovo;

/**
 * @ClassName ExpertScoreSheetPagination
 * @Description TODO
 * @Author xulei
 * @Date 2019/10/29/029 10:10
 * @Version 1.0
 **/
public class ExpertScoreSheetPagination extends PaginationRequest {

    /**
     项目名称
     **/
    private String projectName;

    /**
     项目编号
     **/
    private String projectNumber;


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
}
