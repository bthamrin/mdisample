package com.appspot.mdisample.client.place;


/**
 * @author jp
 */

public class NameTokens {

	public static final String home = "!home";
	public static String getHome() {
		return home;
	}

	public static final String list = "!list";
	public static String getList() {
		return list;
	}
	
	public static final String detail = "!detail";
	public static String getDetail() {
		return detail;
	}
	
	
	public static String parseQuerystring(String query) {
		String result = null;
		
		if(query.length() >= list.length() + 1)
			if(query.substring(0, list.length() + 1).equalsIgnoreCase(list + "=")) result = list + "=";
		
		if(query.length() >= detail.length() + 1)
			if(query.substring(0, detail.length() + 1).equalsIgnoreCase(detail + "=")) result = detail + "=";
		
		return result;
	}

}