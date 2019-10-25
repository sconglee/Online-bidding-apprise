package com.avic.common.utils;

import java.util.*;

/**
 * @author sconglee
 * @date 2019/10/24
 */
public class CountFinalScore {

    private CountFinalScore() {
    }

    public static Map<String, Object> getFinalScore(List<String> pointList, String itemWeight) {
        Map map = new HashMap();
        List<List<String>> pointList1 = new ArrayList<>();
        List<String> itemWeightList = Arrays.asList(itemWeight.split(","));
        int expertNumber = pointList.size();
        int Number = pointList1.get(0).size();

        for (String point : pointList) {
            pointList1.add(Arrays.asList(point.split(",")));
        }

        List<List<String>> pointList2 = new ArrayList<>();
        for (int i = 0; i < Number; i++) {
            pointList2.add(ConvertList(pointList1, i));
        }

        //去掉最高分和最低分然后求取平均分
        List<Integer> averageScoreList = new ArrayList<>();
        for (List<String> pointList3 : pointList2) {
            int sum = 0;
            int averageScore = 0;
            for (int z = 0; z < pointList3.size(); z++) {
                sum = Integer.parseInt(pointList3.get(z)) + sum;
            }
            averageScore = (sum - Integer.parseInt(Collections.max(pointList3)) - Integer.parseInt(Collections.min(pointList3))) / (expertNumber - 2);
            averageScoreList.add(averageScore);
        }

        List<String> finalScoreList = new ArrayList<>();
        for (int k = 0; k < Number; k++) {
            finalScoreList.add(String.valueOf(averageScoreList.get(k) * Integer.parseInt(itemWeightList.get(k))));
        }

        //最后总得分
        int totalScore = 0;
        for (int m = 0; m < finalScoreList.size(); m++) {
            totalScore = Integer.parseInt(finalScoreList.get(m)) + totalScore;
        }

        map.put("finalScore", finalScoreList);
        map.put("totalScore", totalScore);

        return map;
    }

    public static List<String> ConvertList(List<List<String>> list, int j) {
        List<String> list1 = new ArrayList<>();
        for (List<String> list2 : list) {
            list1.add(list2.get(j));
        }
        return list1;
    }
}
