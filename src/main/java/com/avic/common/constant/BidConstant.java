package com.avic.common.constant;

import jdk.nashorn.internal.ir.IfNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName BidConstant
 * @Description 该系统中常量
 * @Author xulei
 * @Date 2019/10/16/016 10:17
 * @Version 1.0
 **/
public class BidConstant {

    // 用户的状态：正常
    public final static Integer USER_STATUS_NORMAL = 0;
    // 用户的状态：停用/注销
    public final static Integer USER_STATUS_CANCEL = 1;

    // 用户类型：项目经理
    public final static Integer USER_TYPE_PROJECT_MANAGER = 0;
    // 用户类型：评标专家
    public final static Integer USER_TYPE_SCORED_EXPERT = 1;

    // 评标模板生效状态
    public static final Integer TEMPLATE_ACTIVE = 0;
    public static final Integer TEMPLATE_NO_ACTIVE = 1;
    public static Map<Integer, String> templateActiveStatus = new HashMap<>();

    static {
        templateActiveStatus.put(TEMPLATE_ACTIVE, "生效");
        templateActiveStatus.put(TEMPLATE_NO_ACTIVE, "未生效");
    }

    // 评标模板删除状态
    public static final Integer TEMPLATE_NO_REMOVE = 0;
    public static final Integer TEMPLATE_REMOVE = 1;

    public static Map<Integer, String> templateRemoveStatus = new HashMap<>();

    static {
        templateRemoveStatus.put(TEMPLATE_NO_REMOVE, "评标打分模板未被删除，处于正常状态！");
        templateRemoveStatus.put(TEMPLATE_REMOVE, "评标打分模板已被删除，处于不可用状态！");
    }

    /**
     * pdf 相关
     **/
    public static String constantPrePathForWin = "C:\\Users\\xulei\\Desktop\\pdfFile\\";
    public static String constantPrePathForLinux = "/var/pdfFile/";
    public static String constantSufPath = ".pdf";
    // 表格的列数
    public final static Integer coloumnNumber = 7;
    /**
     * 表头元素在垂直方向合并单元格数量(行数)
     **/
    public final static Integer verticalAligentNumber = 2;
    // 每页pdf中公司数量
    public final static Integer companyNumberSinglePDF = 3;
    /**
     * table的最大宽度
     **/
    public final static int maxWidth = 520;
    /**
     * table各列的宽度
     **/
    public final static float[] singleColumnWidth = {30, 58, 30, 300, 34, 34, 34};

    /**
     * 设置表头关键字
     **/
    public final static String reviewIndex = "评审项目";
    public final static String standardScore = "标准分";
    public final static String reviewStandardDesc = "评分标准";
    public final static String getScore = "得分";


}
