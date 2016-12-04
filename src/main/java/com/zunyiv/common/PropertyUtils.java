package com.zunyiv.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyUtils {

	private static final Logger log = LoggerFactory.getLogger(PropertyUtils.class);

    /**
     * 根据配置文件名 获取配置文件对象
     * @param fileName
     * @return
     */
    private static Properties getProperties(String fileName){
        if(fileName == null || fileName.trim().isEmpty()){
            log.error("**********没有找到" + fileName + ".properties文件**********");
            return null;
        }
        if (fileName.split("\\.").length == 1){
            fileName += ".properties";
        }
        Properties props = new Properties();
        InputStream is = null;
        try {
            is = PropertyUtils.class.getClassLoader().getResourceAsStream(fileName);
            props.load(is);
        }catch (IOException e) {
            e.printStackTrace();
            log.error("**********获取" + fileName + ".properties文件失败**********");
            return null;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return props;
    }

	/**
	 * 通过properties[文件名称：fileName],[属性名称：properyName]获取属性值
	 * @param fileName
	 * @param propertyName
	 * @return propertyValue
	 */
	public static String getProperty(String fileName, String propertyName){
//		return getProperty(WebConst.ENVIRONMENT_KEY,fileName,propertyName);
		String fieldValue = "";
		Properties props = getProperties(fileName);
		if(!props.containsKey(propertyName)){
            log.error("**********从" + fileName + ".properties文件获取"+ propertyName + "失败**********");
            return fieldValue;
        }
        fieldValue = props.getProperty(propertyName);
        
        return fieldValue;
	}

//    public static String getProperty(String environmentKey ,String fileName, String propertyName){
//        if(fileName == null || fileName.trim().isEmpty()){
//            log.error("**********没有找到" + fileName + ".properties文件**********");
//            return null;
//        }
//
//        if (fileName.split("\\.").length == 1){
//            fileName += ".properties";
//        }
//
//        if(propertyName == null || propertyName.trim().isEmpty()){
//            log.error("**********没有找到" + propertyName + "属性**********");
//            return null;
//        }
//
//        Properties props = new Properties();
//        String fieldValue = "";
//        InputStream is = null;
//
//        Map<String,String> envMap = System.getenv();
//        String filePath = envMap.get(environmentKey);
//
//        try {
//            is = new FileInputStream(filePath + "\\" + fileName ) ;
//            props.load(is);
//            if(!props.containsKey(propertyName)){
//                log.error("**********从" + fileName + ".properties文件获取"+ propertyName + "失败**********");
//                return fieldValue;
//            }
//            fieldValue = props.getProperty(propertyName);
//        } catch (IOException e) {
//            e.printStackTrace();
//            log.error("**********从" + fileName + ".properties文件获取"+ propertyName + "失败**********");
//        } finally {
//            try {
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return fieldValue;
//    }

    public static void main(String[] args) {
//        System.out.println(PropertyUtils.getProperty("ESTIMATE_CONFIG_HOME","config","max_process_times"));
    	System.out.println(getProperty("config.properties", "db.read.pwd"));
    }
}
