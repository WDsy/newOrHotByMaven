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
public class GetJson_StrForNew {
	public static String getJson_Str(String point,List<String> keyWord_list){
		JSONObject json = new JSONObject();
		JSONObject json1 = new JSONObject();
		for (String keyWord_list_str : keyWord_list) {
			json1.put(new UniqueStringGenerator().getUniqueString(), keyWord_list_str);
		}
		json.put("keyWord", json1);
		json.put("from", "0");	
		json.put("limit", "10");
		json.put("dayNum", "30");
		json.put("point", point);
		json.put("field", "createTime");
		return json.toString();
	}
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("list_str0");
		list.add("list_str1");
		System.out.println(getJson_Str("post",list));
	}
}
