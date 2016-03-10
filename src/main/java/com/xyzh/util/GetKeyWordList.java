package com.xyzh.util;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.log4j.Logger;

/**
 * @author 作者： 周卫东
 * @E-mail 邮箱： zhouweidong@gochinatv.com
 * @version 创建时间：2016年2月18日 下午6:15:47 类说明
 */
public class GetKeyWordList {
	static String propertiesName = "urlForProduct.properties";
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static Logger logger  =  Logger.getLogger(GetKeyWordList. class );
	public static List<String> getKeyWordByColumns(String cnName,int from,int limit) {
		DBLinkPoll.setFileName(propertiesName);
		List<String> list = new ArrayList<String>();
		List<Map<String, Object>> getKeyWordList = null;
		String sql = "";
		if (cnName == null || cnName == "" || cnName.equals("")) {
			sql = "select name from t_oper_keywords k join t_oper_columns c on k.columnId = c.Id ";
		} else {
			sql = "select name from t_oper_keywords k join t_oper_columns c on k.columnId = c.Id where c.cnName = '"+ cnName + "'";
		}
		try {
			getKeyWordList = DBLinkPoll.getQueryRunner().query(sql, new MapListHandler());
			from = 0;
			limit = 1000;
			for (Map<String, Object> entry : getKeyWordList) {
				list.add((entry.values().toString()).substring(1, (entry.values().toString()).length() - 1));
			}
		} catch (SQLException e) {
			logger.error("在:::"+sdf.format(new Date())+";;;GetKeyWordList这个类出现了"+e.toString());
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	

	public static List<Map<String, Object>> getAllColumns(String cnName) {
		List<Map<String, Object>> map = new ArrayList<Map<String,Object>>();
		DBLinkPoll.setFileName(propertiesName);
		String sql = "";
		if (cnName == null || cnName == "" || cnName.equals("")) {
			sql = "select  Id, cnName,parentId from t_oper_columns";
		} else {
			sql = "select  Id, cnName,parentId from t_oper_columns where cnName = '" + cnName + "'";
		}
		System.out.println(sql);
		try {
			map = DBLinkPoll.getQueryRunner().query(sql, new MapListHandler());
		} catch (SQLException e) {
			logger.error("在:::"+sdf.format(new Date())+";;;GetKeyWordList这个类出现了"+e.toString());
			e.printStackTrace();
		}
		return map;
	}

	public static void main(String[] args) {
		List<Map<String, Object>> list = getAllColumns("");
		for (Map<String, Object> map : list) {
			for (String string : map.keySet()) {
				if (string.equals("cnName")) {
					System.out.println(string);
					System.out.println(map.get(string));
				}
			}
			
		}
	}
}
