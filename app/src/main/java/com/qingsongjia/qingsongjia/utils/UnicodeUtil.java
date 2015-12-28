package com.qingsongjia.qingsongjia.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnicodeUtil {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub

		// 转换汉字为Unicode码
		String s2 = "文1就是转换的标准";
		System.out.println("s2: " + s2);
		s2 = UnicodeUtil.stringToUnicode(s2);
		System.out.println("s2_unicode_Hex: " + s2);
		// 转换Unicode码为汉字
		String aaa = "\\u4ec0\\u4e48\\u662f\\u5b89\\u5168\\u63a7\u4ef6"
				+ "\uff1f###\u5b89\u5168\u63a7\u4ef6\u53ef\u4ee5\u4fdd\u8bc1"
				+ "\u7528\u6237\u7684\u5bc6\u7801\u4e0d\u88ab\u7a83\u53d6\uff0c"
				+ "\u4ece\u800c\u4fdd\u8bc1\u8d44\u91d1\u5b89\u5168";
		System.out.println("aaa: " + aaa);
		String s3 = UnicodeUtil.unicodeToString(aaa);
		System.out.println("s3: " + s3);

		s2 = "文1就是转换的标准";
		System.out.println("s2_src: " + s2);
		s2 = UnicodeUtil.stringToGBK(s2);
		System.out.println("s2_gbk_hex: " + s2);
	}

	/*  * 把中文字符串转换为十六进制Unicode编码字符串 */
	public static String stringToUnicode(String str) {
		str = (str == null ? "" : str); 
		String tmp; 
		StringBuffer sb = new StringBuffer(1000); 
		char c; 
		int i, j; 
		sb.setLength(0); 
		for (i = 0; i < str.length(); i++) 
		{ 
		c = str.charAt(i); 
		sb.append("\\u"); 
		j = (c >>>8); //取出高8位 
		tmp = Integer.toHexString(j); 
		if (tmp.length() == 1) 
		sb.append("0"); 
		sb.append(tmp); 
		j = (c & 0xFF); //取出低8位 
		tmp = Integer.toHexString(j); 
		if (tmp.length() == 1) 
		sb.append("0"); 
		sb.append(tmp); 

		} 
		return (new String(sb)); 
	}

	/*  * 把十六进制Unicode编码字符串转换为中文字符串 */
	public static String unicodeToString(String str) {

		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(str);
		char ch;
		while (matcher.find()) {
			ch = (char) Integer.parseInt(matcher.group(2), 16);
			str = str.replace(matcher.group(1), ch + "");
		}
		return str;
	}

	/*  * 把中文字符串转换为十六进制GBK编码字符串 */
	public static String stringToGBK(String s)
			throws UnsupportedEncodingException {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			String ch = String.valueOf(s.charAt(i));
			// 取字符串编码示例 默认字符为GBK
			byte[] sgbk = ch.getBytes("gbk");
			String shex = "";
			for (int j = 0; j < sgbk.length; j++) {
				// System.out.println(sb[i]);
				String hex = Integer.toHexString(sgbk[j] & 0xFF);
				if (hex.length() == 1) {
					hex = "0" + hex;
				}
				shex = shex + hex;
			}
			str = str + " 0x" + shex.toUpperCase();
		}

		return str;
	}

}
