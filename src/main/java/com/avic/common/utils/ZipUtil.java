package com.avic.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @ClassName ZipUtil
 * @Description 文件夹以及子文件夹为zip包
 * @Author xulei
 * @Date 2019/11/18/018 14:42
 * @Version 1.0
 **/
public class ZipUtil {
    private static final Log logger = LogFactory.getLog(ZipUtil.class);

    /**
    * @Author xulei
    * @Description  创建ZIP文件
    * @Date 14:44 2019/11/18/018
    * @Param sourcePath 文件或文件夹路径
     * zipPath 生成的zip文件存在路径（包括文件名）
     * isDrop  是否删除原文件:true删除、false不删除
    * @return void
    **/
    public static void createZip(String sourcePath, String zipPath,Boolean isDrop) {
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(zipPath);
            zos = new ZipOutputStream(fos);
            // 此处修改字节码方式。
            //zos.setEncoding("gbk");
            //createXmlFile(sourcePath,"293.xml");
            writeZip(new File(sourcePath), "", zos,isDrop);

        } catch (FileNotFoundException e) {
            logger.error("创建ZIP文件失败",e);
        } finally {
            try {
                if (zos != null) {
                    zos.close();
                }
            } catch (IOException e) {
                logger.error("创建ZIP文件失败",e);
            }

        }
    }


    /**
     * 清空文件和文件目录
     *
     * @param f
     */
    public static void clean(File f) throws Exception {
        String cs[] = f.list();
        if (cs == null || cs.length <= 0) {
            System.out.println("delFile:[ " + f + " ]");
            boolean isDelete = f.delete();
            if (!isDelete) {
                System.out.println("delFile:[ " + f.getName() + "文件删除失败！" + " ]");
                throw new Exception(f.getName() + "文件删除失败！");
            }
        } else {
            for (int i = 0; i < cs.length; i++) {
                String cn = cs[i];
                String cp = f.getPath() + File.separator + cn;
                File f2 = new File(cp);
                if (f2.exists() && f2.isFile()) {
                    System.out.println("delFile:[ " + f2 + " ]");
                    boolean isDelete = f2.delete();
                    if (!isDelete) {
                        System.out.println("delFile:[ " + f2.getName() + "文件删除失败！" + " ]");
                        throw new Exception(f2.getName() + "文件删除失败！");
                    }
                } else if (f2.exists() && f2.isDirectory()) {
                    clean(f2);
                }
            }
            System.out.println("delFile:[ " + f + " ]");
            boolean isDelete = f.delete();
            if (!isDelete) {
                System.out.println("delFile:[ " + f.getName() + "文件删除失败！" + " ]");
                throw new Exception(f.getName() + "文件删除失败！");
            }
        }
    }


    private static void writeZip(File file, String parentPath, ZipOutputStream zos,Boolean isDrop) {
        if(file.exists()){
            // 处理文件夹
            if(file.isDirectory()){
                parentPath+=file.getName()+File.separator;
                File [] files=file.listFiles();
                if(files.length != 0)
                {
                    for(File f:files){
                        writeZip(f, parentPath, zos,isDrop);
                    }
                }
                else
                {       //空目录则创建当前目录
                    try {
                        zos.putNextEntry(new ZipEntry(parentPath));
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }else{
                FileInputStream fis=null;
                try {
                    fis=new FileInputStream(file);
                    ZipEntry ze = new ZipEntry(parentPath + file.getName());
                    zos.putNextEntry(ze);
                    byte [] content=new byte[1024];
                    int len;
                    while((len=fis.read(content))!=-1){
                        zos.write(content,0,len);
                        zos.flush();
                    }

                } catch (FileNotFoundException e) {
                    logger.error("创建ZIP文件失败",e);
                } catch (IOException e) {
                    logger.error("创建ZIP文件失败",e);
                }finally{
                    try {
                        if(fis!=null){
                            fis.close();
                        }
                        if(isDrop){
                            clean(file);
                        }
                    }catch(IOException e){
                        logger.error("创建ZIP文件失败",e);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

