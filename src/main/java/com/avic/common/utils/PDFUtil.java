package com.avic.common.utils;

import com.avic.common.constant.BidConstant;
import com.avic.model.httovo.ExpertScoreSheetComAndPoint;
import com.avic.model.httovo.ExpertScoreSheetInsert;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName PDFUtil
 * @Description PDF相关操作
 * @Author xulei
 * @Date 2019/11/16/016 17:13
 * @Version 1.0
 **/
public class PDFUtil {

    private static final Log logger = LogFactory.getLog(PDFUtil.class);
    /**
     * @Description 建立一个Document对象
     **/
    Document document = new Document();

    /**
     * @Description 设置表头字体大小
     **/
    private static Font headfont;
    /**
     * @Description 设置关键字字体大小
     **/
    private static Font keyfont;
    /**
     * @Description 设置文本字体大小
     **/
    private static Font textfont;

    static{
        BaseFont bfChinese;
        try {
            bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
            // 设置字体大小
            headfont = new Font(bfChinese, 16, Font.BOLD);
            // 设置字体大小
            keyfont = new Font(bfChinese, 12, Font.BOLD);
            // 设置字体大小
            textfont = new Font(bfChinese, 12, Font.NORMAL);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author xulei
     * @Description 构造函数
     * @Date 15:51 2019/10/29/029
     * @Param [file]
     * @return
     **/
    public PDFUtil(File file) {
        // 设置页面大小
        document.setPageSize(PageSize.A4);
        try {
            PdfWriter.getInstance(document,new FileOutputStream(file));
            document.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public PdfPCell createCell(String value, Font font, int align){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setPhrase(new Phrase(value,font));
        return cell;
    }

    public PdfPCell createCell(String value,Font font){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Phrase(value,font));
        return cell;
    }

    public PdfPCell createCell(String value,Font font,int align,int colspan){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.setPhrase(new Phrase(value,font));
        return cell;
    }

    /**
    * @Author xulei
    * @Description isFirst判断是否是第一个，如果是第一个，则不需要setPaddingTop
    * @Date 14:15 2019/11/17/017
    * @Param [value, font, align, colspan, boderFlag, isFirst]
    * @return com.itextpdf.text.pdf.PdfPCell
    **/
    public PdfPCell createCell(String value,Font font,int align,int colspan,boolean boderFlag){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.setPhrase(new Phrase(value,font));
        cell.setPadding(3.0f);

        if(!boderFlag){
            cell.setBorder(0);
        }

        if (boderFlag) {
            cell.setBorder(0);
            cell.setPaddingTop(50.0f);
        }

        return cell;
    }

    public PdfPCell createCell(String value,Font font,int align,int colspan,boolean boderFlag,boolean isFirst){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.setPhrase(new Phrase(value,font));
        cell.setPadding(3.0f);
        cell.setBorder(0);

        if(!isFirst){
            cell.setPaddingTop(50.0f);
            /*cell.setPaddingBottom(10.0f);*/
        }
        /*cell.setPaddingBottom(10.0f);*/

        return cell;
    }

    /**
     * @Author xulei
     * @Description 垂直方向合并单元格 -- Vertical
     * @Date 16:44 2019/11/16/016
     * @Param [value, font, align, colspan]
     * @return com.itextpdf.text.pdf.PdfPCell
     **/
    public PdfPCell createCellVerticalAligent(String value,Font font,int align,int rowspan){
        PdfPCell cell = new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(align);
        cell.setRowspan(rowspan);
        cell.setPhrase(new Phrase(value,font));
        return cell;
    }

    /**
     * @Author xulei
     * @Description 根据列数，初始化table
     * @Date 17:03 2019/11/16/016
     * @Param [colNumber]
     * @return com.itextpdf.text.pdf.PdfPTable
     **/
    public PdfPTable createTable(int colNumber){
        PdfPTable table = new PdfPTable(colNumber);
        try{
            table.setTotalWidth(BidConstant.maxWidth);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
        }catch(Exception e){
            e.printStackTrace();
        }
        return table;
    }

    /**
    * @Author xulei
    * @Description 根据每一列的宽度，初始化table
    * @Date 14:03 2019/11/17/017
    * @Param [widths]
    * @return com.itextpdf.text.pdf.PdfPTable
    **/
    public PdfPTable createTable(float[] widths){
        PdfPTable table = new PdfPTable(widths);
        try{
            // table.setHeaderRows(5);

            table.setTotalWidth(BidConstant.maxWidth);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
        }catch(Exception e){
            e.printStackTrace();
        }
        return table;
    }

    public PdfPTable createBlankTable(){
        PdfPTable table = new PdfPTable(1);
        table.getDefaultCell().setBorder(0);
        table.addCell(createCell("", keyfont));
        table.setSpacingAfter(20.0f);
        table.setSpacingBefore(20.0f);
        return table;
    }

    
    /**
    * @Author xulei
    * @Description 把evaluIndexDesc和description字段分别拆分到单独list中
    * @Date 18:43 2019/11/16/016
    * @Param [evaluIndexDesc, description]
    * @return java.util.HashMap<java.lang.String,java.lang.Object>
    **/
    public HashMap<String, String[]> getEvaluAndDescArray(String evaluIndexDesc, String description) {
        HashMap<String, String[]> resultHashMap = new HashMap<>();

        if (evaluIndexDesc.contains("],[")) {
            evaluIndexDesc = evaluIndexDesc.replace("],[", "#");
        }
        if (evaluIndexDesc.contains("[")) {
            evaluIndexDesc = evaluIndexDesc.replace("[", "");
        }
        if (evaluIndexDesc.contains("]")) {
            evaluIndexDesc = evaluIndexDesc.replace("]", "");
        }
        // split不能使用||分割，所以先把||使用#替换
        evaluIndexDesc = evaluIndexDesc.replace("||", "#");
        String[] evaluIndexDescArray = evaluIndexDesc.split("#");
        resultHashMap.put("evaluIndexDescArray", evaluIndexDescArray);


        if (description.contains("],[")) {
            description = description.replace("],[", "#");
        }
        if (description.contains("[")) {
            description = description.replace("[", "");
        }
        if (description.contains("]")) {
            description = description.replace("]", "");
        }
        description = description.replace("||", "#");
        String[] descriptionArray = description.split("#");
        resultHashMap.put("descriptionArray", descriptionArray);


        return resultHashMap;
    }

    /**
    * @Author xulei
    * @Description 根据evaluIndexDesc，计算 技术评审、商务评审、价格评审各自对应的评审项目数量
    * @Date 19:43 2019/11/16/016
    * @Param [evaluIndexDesc]
    * @return java.lang.Integer[]
    **/
    public ArrayList<Integer> getReviewNumber(String evaluIndexDesc){
        ArrayList<Integer> reviewNumberList = new ArrayList<>();

        evaluIndexDesc = evaluIndexDesc.replace("||", "#");
        String[] stringArray = evaluIndexDesc.split(",");
        for (int i = 0; i < stringArray.length; i++) {
            String[] temp = stringArray[i].split("#");
            reviewNumberList.add(temp.length);
        }

        return reviewNumberList;
    }

    /**
    * 根据公司数量决定每个单元格的width
    **/
    ArrayList<Float> getSingleColoumnWidth(Integer companyNumber){
        ArrayList<Float> singleColoumnWidth = new ArrayList<>();

        if (companyNumber == 1) {
            singleColoumnWidth.add(Float.parseFloat("30"));
            singleColoumnWidth.add(Float.parseFloat("92"));
            singleColoumnWidth.add(Float.parseFloat("30"));
            singleColoumnWidth.add(Float.parseFloat("334"));
            singleColoumnWidth.add(Float.parseFloat("34"));

        } else if (companyNumber == 2) {
            singleColoumnWidth.add(Float.parseFloat("30"));
            singleColoumnWidth.add(Float.parseFloat("178"));
            singleColoumnWidth.add(Float.parseFloat("30"));
            singleColoumnWidth.add(Float.parseFloat("214"));
            singleColoumnWidth.add(Float.parseFloat("34"));
            singleColoumnWidth.add(Float.parseFloat("34"));

        } else {
            singleColoumnWidth.add(Float.parseFloat("30"));
            singleColoumnWidth.add(Float.parseFloat("58"));
            singleColoumnWidth.add(Float.parseFloat("30"));
            singleColoumnWidth.add(Float.parseFloat("300"));
            singleColoumnWidth.add(Float.parseFloat("34"));
            singleColoumnWidth.add(Float.parseFloat("34"));
            singleColoumnWidth.add(Float.parseFloat("34"));
        }

        return singleColoumnWidth;
    }


    /**
     * @Author xulei
     * @Description 生成pdf：以3家单位为标准进行分页，3是固定值。
     * resultData：项目基本信息  + 3家单位的分值数据。
     * @Date 15:51 2019/10/29/029
     * @Param []
     * @return void
     **/
    public void generatePDF(ExpertScoreSheetInsert resultData, String loginUsername) throws Exception {

        List<ExpertScoreSheetComAndPoint> expertScoreSheetComAndPointList = resultData.getExpertScoreSheetComAndPointList();

        // 1、拆分  技术评审、商务评审、价格评审
        String[] totalItems = resultData.getTotalItems().split(",");
        for (int i = 0; i < totalItems.length; i++) {
            totalItems[i] = totalItems[i].substring(0, totalItems[i].indexOf("("));
        }
        // 2、保存 技术评审、商务评审、价格评审各自对应的评审项目数量 -- [13,6,4]
        ArrayList<Integer> reviewNumberList = getReviewNumber(resultData.getEvaluIndexDesc());

        // 3、获取公司数量
        Integer companyNumber = expertScoreSheetComAndPointList.size();
        // Integer totalColounms = companyNumber + 4;
        logger.info("在本次生成pdf时，公司数量是：" + companyNumber);

        // 4、把标准分值拆分到一个数组中
        String[] itemWeightArray = resultData.getItemWeight().split(",");

        // 5、把evaluIndexDesc和description字段分别拆分到一个数组中
        HashMap<String, String[]> resultHashMap = getEvaluAndDescArray(resultData.getEvaluIndexDesc(),resultData.getDescription());
        String[] evaluIndexDescArray = resultHashMap.get("evaluIndexDescArray");
        String[] descriptionArray = resultHashMap.get("descriptionArray");

        // 评审项目序号
        Integer reviewCount = 1;

        // 6、根据表格的列数，生成table -- 先获取每个单元格的width
        ArrayList<Float> singleColoumnWidthList = getSingleColoumnWidth(companyNumber);
        float[] singleColoumnWidth = new float[singleColoumnWidthList.size()];
        for (int i = 0; i < singleColoumnWidthList.size(); i++) {
            singleColoumnWidth[i] = singleColoumnWidthList.get(i);
        }

        // 7、生成pdfTable
        PdfPTable table = createTable(singleColoumnWidth);

        // 8、以技术评审、商务评审、价格评审为顺序进行输出pdf
        Boolean isFirst = true;
        for (int i = 0; i < totalItems.length; i++){

            // 8.1、设置pdf头信息
            table.addCell(createCell("专家编号：" + loginUsername, textfont, Element.ALIGN_LEFT, BidConstant.coloumnNumber, true, isFirst));
            if (isFirst) {
                isFirst = false;
            }
            table.addCell(createCell("评审专家签字：", textfont, Element.ALIGN_LEFT, BidConstant.coloumnNumber, false));
            table.addCell(createCell("专家签字日期：", textfont, Element.ALIGN_LEFT, BidConstant.coloumnNumber, false));
            table.addCell(createCell(totalItems[i] + "表", headfont, Element.ALIGN_CENTER, BidConstant.coloumnNumber, false));
            table.addCell(createCell("项目名称：" + resultData.getProjectName(), textfont, Element.ALIGN_LEFT, BidConstant.coloumnNumber,false));
            table.addCell(createCell("项目编号：" + resultData.getProjectNumber(), textfont, Element.ALIGN_LEFT, BidConstant.coloumnNumber,false));

            // 8.2、设置表头关键字
            table.addCell(createCellVerticalAligent("序号", keyfont, Element.ALIGN_CENTER, BidConstant.verticalAligentNumber));
            table.addCell(createCellVerticalAligent(BidConstant.reviewIndex, keyfont, Element.ALIGN_CENTER, BidConstant.verticalAligentNumber));
            table.addCell(createCellVerticalAligent(BidConstant.standardScore, keyfont, Element.ALIGN_CENTER, BidConstant.verticalAligentNumber));
            table.addCell(createCellVerticalAligent(BidConstant.reviewStandardDesc, keyfont, Element.ALIGN_CENTER, BidConstant.verticalAligentNumber));
            table.addCell(createCell(BidConstant.getScore, keyfont, Element.ALIGN_CENTER, companyNumber));

            ArrayList<String[]> allComPointList = new ArrayList<>();
            for (ExpertScoreSheetComAndPoint expertScoreSheetComAndPoint : expertScoreSheetComAndPointList) {
                // 设置表头中--公司名称
                table.addCell(createCell(expertScoreSheetComAndPoint.getCompanyName(), keyfont, Element.ALIGN_CENTER));
                allComPointList.add(expertScoreSheetComAndPoint.getPoint().split(","));
            }

            // 8.3、开始循环add单元格内容
            for (int j = 0; j < reviewNumberList.get(i); j++) {
                table.addCell(createCell(reviewCount + "", textfont));
                table.addCell(createCell(evaluIndexDescArray[reviewCount - 1] , textfont));
                table.addCell(createCell(itemWeightArray[reviewCount - 1], textfont));
                table.addCell(createCell(descriptionArray[reviewCount - 1], textfont));

                // 输出分值
                for (String[] pointArry : allComPointList) {
                    table.addCell(createCell(pointArry[reviewCount - 1], textfont));
                }
                reviewCount++;
            }
        }

        document.add(table);
        document.close();
    }



}
