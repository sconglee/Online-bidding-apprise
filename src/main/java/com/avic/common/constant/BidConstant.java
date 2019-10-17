package com.avic.common.constant;

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
    public final static Integer USER_TYPE_SCORED_EXPERT= 1;

    // 评标模板生效状态
    public static final Integer TEMPLATE_ACTIVE = 0;
    public static final Integer TEMPLATE_NO_ACTIVE = 1;
    public static Map<Integer,String> templateActiveStatus = new HashMap<>();
    static{
        templateActiveStatus.put(TEMPLATE_ACTIVE, "生效");
        templateActiveStatus.put(TEMPLATE_NO_ACTIVE, "未生效");
    }

    // 评标模板删除状态
    public static final Integer TEMPLATE_NO_REMOVE = 0;
    public static final Integer TEMPLATE_REMOVE = 1;

    public static Map<Integer,String> templateRemoveStatus = new HashMap<>();
    static{
        templateRemoveStatus.put(TEMPLATE_NO_REMOVE, "评标打分模板未被删除，处于正常状态！");
        templateRemoveStatus.put(TEMPLATE_REMOVE, "评标打分模板已被删除，处于不可用状态！");
    }







}
