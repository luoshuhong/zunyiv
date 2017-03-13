package com.zunyiv.admin;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by luoshuhong on 2017/1/14.
 */
public class PicTest {
    public static void main(String[] args) {
        //int im, int size, int angle, int x, int y, int col, string fontfile, string text
//        imagettftext($img,32,0,110,225, $color, "MSYHBD.TTF",$name.":");

//        imagettftext($img,25,0, 200,660, $color, "MSYHBD.TTF","——2016年".$name."的印象");
//        imagettftext($img,18,0,300,720, $color, "MSYHBD.TTF","主编:".$name.".");

//        createStringMark("C://z-personal//微博//4.jpg", "骆书洪",Color.black, 100,"C://z-personal//微博//5.jpg", 110,225);
    }

//    //给jpg添加文字
//    public static boolean createStringMark(String filePath,String markContent,Color markContentColor,float qualNum ,String outPath, int x, int y)
//    {
//        ImageIcon imgIcon=new ImageIcon(filePath);
//        Image theImg =imgIcon.getImage();
//        int width=theImg.getWidth(null)==-1?200:theImg.getWidth(null);
//        int height= theImg.getHeight(null)==-1?200:theImg.getHeight(null);
//        System.out.println(width);
//        System.out.println(height);
//        System.out.println(theImg);
//        BufferedImage bimage = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
//        Graphics2D g=bimage.createGraphics();
//        g.setColor(markContentColor);
//        g.setBackground(Color.red);
//        g.drawImage(theImg, 0, 0, null );
////        g.setFont(new Font(null,Font.BOLD,15)); //字体、字型、字号
////        g.drawString(markContent,11,height/2); //画文字
////        g.setFont(new Font(null, Font.BOLD,15)); //字体、字型、字号
//        g.setFont(loadFont("C://z-personal//微博//MSYHBD.TTF", 25));
//        g.drawString(markContent, x, y); //画文字
//        g.drawString("——2016年我的朋友印象",200,660); //画文字
//        g.drawString("主编:" + markContent,300,720); //画文字
//
//        g.dispose();
//        try
//        {
//            FileOutputStream out=new FileOutputStream(outPath); //先用一个特定的输出文件名
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
//            param.setQuality(qualNum, true);
//            encoder.encode(bimage, param);
//            out.close();
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//
//        }
//        return true;
//    }
//
//    private static Font getSelfDefinedFont(String filename)
//    {
//        Font font = null;
//        //字体文件在conf下面
//        String filepath = "C://z-personal//微博//MSYHBD.TTF";
//        File file = new File(filepath);
//        try
//        {
//            FileInputStream fi = new FileInputStream(file);
//            BufferedInputStream fb = new BufferedInputStream(fi);
//            font = Font.createFont(Font.TRUETYPE_FONT, fb);
//        }
//        catch (FontFormatException e)
//        {
//            return null;
//        }
//        catch (FileNotFoundException e)
//        {
//            return null;
//        }
//        catch (IOException e)
//        {
//            return null;
//        }
//        return font;
//    }
//
//    public static Font loadFont(String fontFileName, float fontSize)  //第一个参数是外部字体名，第二个是字体大小
//    {
//        try
//        {
//            File file = new File(fontFileName);
//            FileInputStream aixing = new FileInputStream(file);
//            Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, aixing);
//            Font dynamicFontPt = dynamicFont.deriveFont(fontSize);
//            aixing.close();
//            return dynamicFontPt;
//        }
//        catch(Exception e)//异常处理
//        {
//            e.printStackTrace();
//            return new java.awt.Font("宋体", Font.PLAIN, 14);
//        }
//    }
//    public static java.awt.Font Font(){
//        String root=System.getProperty("user.dir");//项目根目录路径
//        Font font = loadFont(root+"/data/PRISTINA.ttf", 18f);//调用
//        return font;//返回字体
//    }
//    public static java.awt.Font Font2(){
//        String root=System.getProperty("user.dir");//项目根目录路径
//        Font font = loadFont(root+"/data/XXXX.ttf", 18f);
//        return font;//返回字体
//    }

}
