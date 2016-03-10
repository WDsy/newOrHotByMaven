package com.xyzh.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

/**
 * @author 作者：       周卫东
 * @E-mail 邮箱：       zhouweidong@gochinatv.com
 * @version 创建时间：2016年2月24日 上午8:31:18
 * 类说明
*/
public class ParserMap {
	public static Map parserToMap(String s) {
		Map map = new HashMap();
//		System.out.println(s+"______________________________________");
		JSONObject json = JSONObject.fromObject(s);
		Iterator keys = json.keys();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String value = json.getString(key);
			if (value.startsWith("{") && value.endsWith("}")) {
				map.put(key, value);
			} else {
				map.put(key, value);
			}

		}
		return map;
	}
	
	
	public static List<Map<String, Object>> parserUrl(List<Map<String, Object>> map){
		Pattern p = Pattern.compile("\"(.*?)\"");
		Matcher match;
		for (Map<String, Object> m : map) {
			for (String string_key : m.keySet()) {
				if (string_key.equals("imgUrls")) {
					if (m.get(string_key) != null || !m.get(string_key).equals("")) {
						if (m.get(string_key).toString().startsWith("[")) {
							match = p.matcher(m.get(string_key).toString());
							if (match.find()) {
								m.put(string_key, match.group(1));
							}
						}
					}
				}
			}
		}
		return map;
	}
	public static void main(String[] args) {
		parserToMap("{\"content\":\"{ @Sha Lo Tung Village 沙螺洞村 }\"}");
	}
	
	
	
}
