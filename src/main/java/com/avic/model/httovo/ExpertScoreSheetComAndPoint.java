package com.avic.model.httovo;

/**
 * @ClassName ExpertScoreSheetComAndPoint
 * @Description TODO
 * @Author xulei
 * @Date 2019/11/14/014 18:07
 * @Version 1.0
 **/
public class ExpertScoreSheetComAndPoint {
    private String companyName;
    private String point;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "ExpertScoreSheetComAndPoint{" +
                "companyName='" + companyName + '\'' +
                ", point='" + point + '\'' +
                '}';
    }
}
