package com.xyzh.thread;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.xyzh.entity.Columns;
import com.xyzh.util.DatabaseUtil;
import com.xyzh.util.HttpRequestClients;
import com.xyzh.util.OperateDatabase;
import com.xyzh.util.ParserMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author 作者： 周卫东
 * @E-mail 邮箱： zhouweidong@gochinatv.com
 * @version 创建时间：2016年3月7日 下午5:40:10 类说明
 */
public class ToNewTable implements Runnable {
	static Logger logger  =  Logger.getLogger(ToNewTable. class );
	 static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Pattern p = Pattern.compile("\"(.*?)\"");
	Matcher match;
	static OperateDatabase odb = new OperateDatabase();
	LinkedList<Columns> list;
	String urls;
	String tableNames;
	private List<Map<String, Object>> map_List;
	public void setUrl(String url) {
		urls = url;
	}
	public void setTableName(String tableName){
		tableNames = tableName;
	}
	public ToNewTable(LinkedList<Columns> list) {
		this.list = list;
	}// 构造函数

	public List<Map<String, Object>> getMap_List() {
		return map_List;
	}
	public void setMap_List(List<Map<String, Object>> map_List) {
		this.map_List = map_List;
	}
	@Override
	public void run() {
		Columns columns = new Columns();
		while (true) {
			synchronized (list) {
				if (!list.isEmpty()) {
					columns = list.removeFirst();
				} else {
					break;
				}
			}
			try {
				List<Map<String, Object>> list_map = new ArrayList<Map<String, Object>>();
				String result = HttpRequestClients.getResponseAsStringByPostMethod(urls, columns.getJson()).toString();
				JSONObject json_1 = JSONObject.fromObject(result);
				json_1 = json_1.getJSONObject("elasticsearchResponse");
				JSONArray jsonArray = json_1.getJSONArray("data");
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject json_3 = jsonArray.getJSONObject(i);
					Map<String, Object> map_over = ParserMap.parserToMap(json_3.toString());
					map_over.put("language", "CN");
					map_over.put("columnId", columns.getCnId());
					map_over.put("keyWord", columns.getCnName());
					map_over.put("parentId", columns.getParentId());
					map_over.put("newOrHot", "new");
					list_map.add(map_over);
				}
				System.out.println(columns.getJson());
				System.out.println(urls);
				// 开始插入表中数据
				list_map = ParserMap.parserUrl(list_map);
				map_List = list_map;
				odb.addToProductBase(list_map, tableNames);
				Thread.sleep(1000);
				System.out.println(
						map_List.size() + "---------------------------------------------------------------------");
			} catch (NullPointerException e) {
				logger.error("在:::"+sdf.format(new Date())+";;;"+this.getClass().getName()+"这个类出现了"+e.toString());
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				logger.error("在:::"+sdf.format(new Date())+";;;"+this.getClass().getName()+"这个类出现了"+e.toString());
				e.printStackTrace();
			} catch (IllegalStateException e) {
				logger.error("在:::"+sdf.format(new Date())+";;;"+this.getClass().getName()+"这个类出现了"+e.toString());
				e.printStackTrace();
			} catch (IOException e) {
				logger.error("在:::"+sdf.format(new Date())+";;;"+this.getClass().getName()+"这个类出现了"+e.toString());
				e.printStackTrace();
			} catch (InterruptedException e) {
				logger.error("在:::"+sdf.format(new Date())+";;;"+this.getClass().getName()+"这个类出现了"+e.toString());
				System.out.println(columns.getJson()+"去"+urls+"这个地址的时候出现以下错误");
				e.printStackTrace();
			}
		}
	}
}
