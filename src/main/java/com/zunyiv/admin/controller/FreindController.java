package com.zunyiv.admin.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.util.Random;


import java.awt.image.BufferedImage;

import javax.swing.*;

/**
 * Created by luoshuhong on 2017/1/14.
 */
@Controller
@RequestMapping("/friend")
public class FreindController {
    private static String qrCodePath = "friend/qr/";
    private static String qrCodeOutPath = "friend/img/";

    @RequestMapping("/create")
    @ResponseBody
    public String create(HttpServletRequest request, HttpServletResponse response, Model model){
        int num = new Random().nextInt(18);
        String bathPath = request.getRealPath("/");
        String qrCodePathStr = bathPath + qrCodePath + num + ".png";

        String name = request.getParameter("name");
        String street = request.getParameter("street");

        if (StringUtils.isEmpty(street)) {
            street = "遵义";
        }
        String qrCodeOutPathStr = qrCodeOutPath + name + street + ".png";

        createStringMark(qrCodePathStr, name, street, 100, bathPath + qrCodeOutPathStr);
        System.out.println("qrCodeOutPathStr:" + qrCodeOutPathStr);
        return qrCodeOutPathStr;
    }

    //给jpg添加文字
    public static boolean createStringMark(String filePath,String markContent,String street,float qualNum ,String outPath) {
        File outPutFile = new File(outPath);
        if (outPutFile.exists()) {
            System.out.println("成功1:" + outPath);
            return true;
        }
        ImageIcon imgIcon=new ImageIcon(filePath);
        Image theImg =imgIcon.getImage();
        int width=theImg.getWidth(null)==-1?200:theImg.getWidth(null);
        int height= theImg.getHeight(null)==-1?200:theImg.getHeight(null);
        BufferedImage bimage = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g=bimage.createGraphics();
        g.setColor(Color.black);
        g.setBackground(Color.red);
        g.drawImage(theImg, 0, 0, null );
//        g.setFont(new Font(null, Font.BOLD,15)); //字体、字型、字号
        g.setFont(loadFont("MSYHBD.TTF", 25));
        g.drawString(street + "的" + markContent, 110, 225); //画文字
        g.drawString("—— 主编：" + street + "的" + markContent,200,660); //画文字
//        g.drawString("遵义同城印象",250,720); //画文字
        g.dispose();

//        Graphics2D g1 = bimage.createGraphics();
//        g1.drawImage(theImg, 0, 0, null );
//        g1.setFont(loadFont("MSYHBD.TTF", 30));
//        g1.setColor(Color.RED);
//        g1.drawString("遵义同城印象",250,720); //画文字
//        g1.dispose();

        try {
//            FileOutputStream out=new FileOutputStream(outPath); //先用一个特定的输出文件名
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
//            param.setQuality(qualNum, true);
//            encoder.encode(bimage, param);

            ImageIO.write(bimage, "png", new File(outPath));
//            out.close();
        }
        catch(Exception e) {
            e.printStackTrace();
            return  false;
        }
        System.out.println("成功:" + outPath);
        return true;
    }

    ////第一个参数是外部字体名，第二个是字体大小
    public static Font loadFont(String fontFileName, float fontSize)  {
        try {
            File file = new File(fontFileName);
            InputStream aixing = Thread.currentThread().getContextClassLoader().getResourceAsStream(fontFileName);
            Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, aixing);
            Font dynamicFontPt = dynamicFont.deriveFont(fontSize);
            aixing.close();
            return dynamicFontPt;
        } catch(Exception e) {
            e.printStackTrace();
            return new java.awt.Font("宋体", Font.PLAIN, 14);
        }
    }

}
