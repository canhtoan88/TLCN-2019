package com.huupham.utilities;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlPercentEncodingFormat {

	public static String createUrlPercentEncodingFormat(String parameter) {

		String parameterFormat = null;

		try {

			parameterFormat = URLEncoder.encode(parameter, "UTF-8");
			parameterFormat = parameterFormat.replace("+", "%20");
			return parameterFormat;
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return "";
		}
	}
}
