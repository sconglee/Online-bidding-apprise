package com.avic.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName TimeUtil
 * @Description TODO
 * @Author xulei
 * @Date 2019/10/16/016 14:11
 * @Version 1.0
 **/
public class TimeUtil {

    public static String getTimeByDefautFormat() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(date);
        return dateNowStr;
    }


}
