package com.avic.common.constant;

import java.math.BigDecimal;
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


    public static final String SMS_TYPE_OPEN = "1";
    public static final String SMS_TYPE_ACTIVE = "2";
    public static final String SMS_TYPE_PWD = "3";

    public static Map<String,String> actionType = new HashMap<String,String>();

    static{
        actionType.put(SMS_TYPE_OPEN, "11");
        actionType.put(SMS_TYPE_ACTIVE, "22222");
        actionType.put(SMS_TYPE_PWD, "3333");
    }





}
