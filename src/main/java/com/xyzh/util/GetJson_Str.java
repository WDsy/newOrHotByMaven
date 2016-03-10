package com.xyzh.util;

import java.util.ArrayList;
import java.util.List;


import net.sf.json.JSONObject;

/**
 * @author 作者：       周卫东
 * @E-mail 邮箱：       zhouweidong@gochinatv.com
 * @version 创建时间：2016年2月23日 下午5:52:13
 * 类说明
*/
public class GetJson_Str {
	static String fileName = "poiDB.properties";
	
	public static String getJson_Str(String point,List<String> keyWord_list){
		String from = new Parse().parse("from", fileName);
		String limit = new Parse().parse("limit", fileName);
		String dayNum  = new Parse().parse("dayNum", fileName);
		String field = new Parse().parse("fieldForCount", fileName);
		
		JSONObject json = new JSONObject();
		JSONObject json1 = new JSONObject();
		for (String keyWord_list_str : keyWord_list) {
			json1.put(new UniqueStringGenerator().getUniqueString(), keyWord_list_str);
		}
		json.put("keyWord", json1);
		json.put("from", from);	
		json.put("limit", limit);
		json.put("dayNum", dayNum);
		json.put("point", point);
		json.put("field", field);
		return json.toString();
	}
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("list_str0");
		list.add("list_str1");
		System.out.println(getJson_Str("post",list));
	}
}
