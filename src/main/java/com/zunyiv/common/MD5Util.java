package com.zunyiv.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util
{
	/**
	 * 对目标字符串进行MD5加密
	 * 
	 * @param source
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws Exception
	 */
	public static String md5(String source) throws UnsupportedEncodingException, NoSuchAlgorithmException
	{
		String resultHash = null;
		try
		{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] result = new byte[md5.getDigestLength()];
			md5.reset();
			md5.update(source.getBytes("UTF-8"));
			result = md5.digest();

			StringBuffer buf = new StringBuffer(result.length * 2);

			for (int i = 0; i < result.length; i++)
			{
				int intVal = result[i] & 0xff;
				if (intVal < 0x10)
				{
					buf.append("0");
				}
				buf.append(Integer.toHexString(intVal));
			}
			resultHash = buf.toString();
		}
		catch (Exception e)
		{

		}
		return resultHash;
	}
	/**
     * MD5方法
     * 
     * @param text 明文
     * @param charset 密钥
     * @return 密文
     * @throws Exception
     */
	public static String md5(String text, String charset) throws Exception {
        if(charset == null || charset.length()==0)
            charset = "UTF-8";

		byte[] bytes = text.getBytes(charset);
		
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(bytes);
		bytes = messageDigest.digest();
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < bytes.length; i ++)
		{
			if((bytes[i] & 0xff) < 0x10)
			{
				sb.append("0");
			}

			sb.append(Long.toString(bytes[i] & 0xff, 16));
		}
		
		return sb.toString().toLowerCase();
	}
	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException
	{
		String md5 = md5("18511551331"+md5("123456"));
		System.out.println(md5);
		System.out.println(md5("123456"));
		System.out.println(md5("18511551331"+"e10adc3949ba59abbe56e057f20f883e"+"JJ00007"));
		System.out.println(md5("admin"+"111111"));
	}
}
