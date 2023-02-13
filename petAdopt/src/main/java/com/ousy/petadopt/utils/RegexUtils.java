package com.ousy.petadopt.utils;

/**
 * @title: StrUtils
 * @Author miller.ouyang
 * @Date: 2023/1/6 10:49
 * @Version 1.0
 */
public class RegexUtils {
	public static boolean isLetterDigitOrChinese(String str) {
		String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
		return str.matches(regex);
	}

	public static boolean isNumber(String str){
		String regex = "^[0-9]*$";
		return str.matches(regex);
	}
}
