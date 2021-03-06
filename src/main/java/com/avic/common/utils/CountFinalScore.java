package com.avic.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author sconglee
 * @date 2019/10/24
 */
public class CountFinalScore {

    private final static Log logger = LogFactory.getLog(CountFinalScore.class);

    private CountFinalScore() {
    }

    public static Map<String, Object> getExpertAverageAndTotalScore(List<String> pointList) {

        logger.info(pointList);
        Map map = new HashMap();
        List<List<String>> pointList1 = new ArrayList<>();
        int expertNumber = pointList.size();

        if (expertNumber > 0) {
            for (String point : pointList) {
                pointList1.add(Arrays.asList(point.split(",")));
            }

            //求每个专家打分的和
            double sumScore = 0;
            List<Double> doubleList = new ArrayList<>();
            List<String> finalScoreList = new ArrayList<>();
            for (List<String> pointList2 : pointList1) {
                double sum = 0;
                for (String point : pointList2) {
                    sum = new BigDecimal(Double.toString(Double.valueOf(point))).add(new BigDecimal(Double.toString(sum))).doubleValue();
                }
                sumScore = new BigDecimal(Double.toString(sum)).add(new BigDecimal(Double.toString(sumScore))).doubleValue();
                sum = (double) Math.round(sum * 100) / 100;
                doubleList.add(sum);
                finalScoreList.add(String.valueOf(sum));
            }
            logger.info(finalScoreList);

            //直接求取平均分
            double totalScore = new BigDecimal(Double.toString(sumScore)).doubleValue() / expertNumber;
            totalScore = (double) Math.round(totalScore * 100) / 100;

            logger.info(sumScore);
            logger.info(Collections.max(doubleList));
            logger.info(Collections.min(doubleList));
            logger.info(totalScore);

            map.put("flag", true);
            map.put("finalScore", finalScoreList);
            map.put("totalScore", totalScore);
        } else {
            map.put("flag", false);
            map.put("msg", "没有评标专家提交打分！");
        }
        return map;
    }

    public static Map<String, Object> getExpertAverageAndTotalScoreExceptTwoExpert(List<String> pointList) {

        logger.info(pointList);
        Map map = new HashMap();
        List<List<String>> pointList1 = new ArrayList<>();
        int expertNumber = pointList.size();

        if (expertNumber > 2) {
            for (String point : pointList) {
                pointList1.add(Arrays.asList(point.split(",")));
            }

            //求每个专家打分的和
            double sumScore = 0;
            List<Double> doubleList = new ArrayList<>();
            List<String> finalScoreList = new ArrayList<>();
            //DecimalFormat df = new DecimalFormat("0.##");
            for (List<String> pointList2 : pointList1) {
                double sum = 0;
                for (String point : pointList2) {
                    //sum = Double.valueOf(point) + sum;
                    sum = new BigDecimal(Double.toString(Double.valueOf(point))).add(new BigDecimal(Double.toString(sum))).doubleValue();
                }
                sumScore = new BigDecimal(Double.toString(sum)).add(new BigDecimal(Double.toString(sumScore))).doubleValue();
                //finalScoreList.add(df.format(sum));
                sum = (double) Math.round(sum * 100) / 100;
                doubleList.add(sum);
                finalScoreList.add(String.valueOf(sum));
            }
            logger.info(finalScoreList);

            //去掉专家的一个最高总分一个最低总分求取平均分
            /*double sumScore = 0;
            List<Double> doubleList = new ArrayList<>();
            for (String point : finalScoreList) {
                double doublePoint = Double.valueOf(point);
                sumScore = doublePoint + sumScore;
                doubleList.add(doublePoint);
            }*/
            //double totalScore = (sumScore - Collections.max(doubleList) - Collections.min(doubleList)) / (expertNumber - 2);
            double totalScore = new BigDecimal(Double.toString(sumScore))
                    .subtract(new BigDecimal(Double.toString(Collections.max(doubleList))))
                    .subtract(new BigDecimal(Double.toString(Collections.min(doubleList)))).doubleValue() / (expertNumber - 2);
            totalScore = (double) Math.round(totalScore * 100) / 100;

            logger.info(sumScore);
            logger.info(Collections.max(doubleList));
            logger.info(Collections.min(doubleList));
            logger.info(totalScore);

            map.put("flag", true);
            map.put("finalScore", finalScoreList);
            map.put("totalScore", totalScore);
        } else {
            map.put("flag", false);
            map.put("msg", "已提交打分的评标专家数必须多于2人！");
        }
        return map;
    }

    public static Map<String, Object> getItemAverageAndTotalScoreExceptFourExpert(List<String> pointList) {

        logger.info(pointList);
        logger.info(";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
        Map map = new HashMap();
        List<List<String>> pointList1 = new ArrayList<>();
        int expertNumber = pointList.size();

        if (expertNumber > 4) {

            for (String point : pointList) {
                pointList1.add(Arrays.asList(point.split(",")));
            }
            int itemNumber = pointList1.get(0).size();

            List<List<String>> pointList2 = new ArrayList<>();
            for (int i = 0; i < itemNumber; i++) {
                pointList2.add(ConvertList(pointList1, i));
            }
            logger.info(pointList2);
            logger.info(expertNumber);
            logger.info("-----------------------------");

            //去掉分项的两个最高分和两个最低分然后求取平均分
            List<Double> averageScoreList = new ArrayList<>();
            for (List<String> pointList3 : pointList2) {
                double sum = 0;
                double averageScore = 0;
                logger.info("!!!!!!!!!!!!!!!!!!!!");
                logger.info(pointList3);
                for (int z = 0; z < pointList3.size(); z++) {
                    sum = new BigDecimal(Double.toString(Double.valueOf(pointList3.get(z)))).add(new BigDecimal(Double.toString(sum))).doubleValue();
                }
                List<Double> doubleList = new ArrayList<>();
                for (String x : pointList3) {
                    doubleList.add(Double.valueOf(x));
                }

                double max_1 = Collections.max(doubleList);
                logger.info(max_1);
                doubleList.remove(Double.valueOf(max_1));

                double max_2 = Collections.max(doubleList);
                logger.info(max_2);
                doubleList.remove(Double.valueOf(max_2));

                double min_1 = Collections.min(doubleList);
                logger.info(min_1);
                doubleList.remove(Double.valueOf(min_1));

                double min_2 = Collections.min(doubleList);
                logger.info(min_2);
                doubleList.remove(Double.valueOf(min_2));

                averageScore = new BigDecimal(Double.toString(sum)).subtract(new BigDecimal(Double.toString(max_1)))
                        .subtract(new BigDecimal(Double.toString(max_2)))
                        .subtract(new BigDecimal(Double.toString(min_1)))
                        .subtract(new BigDecimal(Double.toString(min_2))).doubleValue() / (expertNumber - 4);
                logger.info(sum);
                logger.info(averageScore);
                logger.info("'''''''''''''''");
                averageScoreList.add(averageScore);
            }
            logger.info(averageScoreList);

            logger.info("________________________");
            List<String> finalScoreList = new ArrayList<>();
            DecimalFormat df = new DecimalFormat("0.##");
            for (int k = 0; k < itemNumber; k++) {
                finalScoreList.add(String.valueOf((double) Math.round(averageScoreList.get(k) * 100) / 100));
            }

            //最后总得分
            double totalScore = 0;
            for (int m = 0; m < finalScoreList.size(); m++) {
                totalScore = Double.valueOf(finalScoreList.get(m)) + totalScore;
            }
            totalScore = (double) Math.round(totalScore * 100) / 100;
            logger.info("________________________");
            logger.info(finalScoreList);
            logger.info(totalScore);

            map.put("flag", true);
            map.put("finalScore", finalScoreList);
            map.put("totalScore", totalScore);
        } else {
            map.put("flag", false);
            map.put("msg", "已提交打分的评标专家数必须多于4人！");
        }
        return map;
    }

    public static Map<String, Object> getItemAverageAndTotalScoreExpertTwoExpert(List<String> pointList, String itemWeight) {


        logger.info(pointList);
        logger.info(itemWeight);
        logger.info(";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
        Map map = new HashMap();
        List<List<String>> pointList1 = new ArrayList<>();
        List<String> itemWeightList = Arrays.asList(itemWeight.split(","));
        int expertNumber = pointList.size();

        if (expertNumber > 2) {
            for (String point : pointList) {
                pointList1.add(Arrays.asList(point.split(",")));
            }
            int itemNumber = pointList1.get(0).size();

            List<List<String>> pointList2 = new ArrayList<>();
            for (int i = 0; i < itemNumber; i++) {
                pointList2.add(ConvertList(pointList1, i));
            }
            logger.info(pointList2);
            logger.info(expertNumber);
            logger.info("-----------------------------");

            //去掉分项的一个最高分和一个最低分然后求取平均分
            List<Double> averageScoreList = new ArrayList<>();
            for (List<String> pointList3 : pointList2) {
                double sum = 0;
                double averageScore = 0;
                logger.info("!!!!!!!!!!!!!!!!!!!!");
                logger.info(pointList3);
                for (int z = 0; z < pointList3.size(); z++) {
                    //sum = Float.parseFloat(pointList3.get(z)) + sum;
                    sum = new BigDecimal(Double.toString(Double.valueOf(pointList3.get(z)))).add(new BigDecimal(Double.toString(sum))).doubleValue();
                }
                List<Double> doubleList = new ArrayList<>();
                for (String x : pointList3) {
                    doubleList.add(Double.valueOf(x));
                }
                //averageScore = (sum - Collections.max(doubleList) - Collections.min(doubleList)) / (expertNumber - 2);
                averageScore = new BigDecimal(Double.toString(sum))
                        .subtract(new BigDecimal(Double.toString(Collections.max(doubleList))))
                        .subtract(new BigDecimal(Double.toString(Collections.min(doubleList)))).doubleValue() / (expertNumber - 2);
                logger.info("'''''''''''''''");
                logger.info(Collections.max(doubleList));
                logger.info(Collections.min(doubleList));
                logger.info("''''''''''''''''");
                averageScoreList.add(averageScore);
            }
            logger.info(averageScoreList);
            logger.info(itemWeightList);
            logger.info("________________________");
            List<String> finalScoreList = new ArrayList<>();
            //乘以权重,并求取总分
            //DecimalFormat df = new DecimalFormat("0.##");
            double totalScore = 0;
            for (int k = 0; k < itemNumber; k++) {
                double finalScore = 0;
                //finalScoreList.add(df.format(averageScoreList.get(k) * Double.valueOf(itemWeightList.get(k))));
                finalScore = new BigDecimal(Double.toString(averageScoreList.get(k))).multiply(new BigDecimal(Double.toString(Double.valueOf(itemWeightList.get(k))))).doubleValue();
                finalScoreList.add(String.valueOf((double) Math.round(finalScore * 100) / 100));
                totalScore = new BigDecimal(Double.toString(finalScore)).add(new BigDecimal(Double.toString(totalScore))).doubleValue();
            }
            totalScore = (double) Math.round(totalScore * 100) / 100;

            //最后总得分
            /*double totalScore = 0;
            for (int m = 0; m < finalScoreList.size(); m++) {
                totalScore = Double.valueOf(finalScoreList.get(m)) + totalScore;
            }*/
            logger.info(finalScoreList);
            logger.info(totalScore);

            map.put("flag", true);
            map.put("finalScore", finalScoreList);
            map.put("totalScore", totalScore);
        } else {
            map.put("flag", false);
            map.put("msg", "已提交打分的评标专家数必须多于2人！");
        }
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
