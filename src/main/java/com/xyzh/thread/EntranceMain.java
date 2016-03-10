package com.xyzh.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.xyzh.entity.Columns;
import com.xyzh.util.GetColumnList;
import com.xyzh.util.GetColumnListForNew;
import com.xyzh.util.GetKeyWordList;
import com.xyzh.util.OperateDatabase;
/**
 * @author 作者： 周卫东
 * @E-mail 邮箱： zhouweidong@gochinatv.com
 * @version 创建时间：2016年3月7日 下午5:14:05 类说明
 */
public class EntranceMain {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static Logger logger  =  Logger.getLogger(EntranceMain. class );
	static OperateDatabase odb = new OperateDatabase();
	public static void hotData(List<Columns> columns_List,String url,String tableName) throws Exception {
		LinkedList<Columns> linkList = new LinkedList<Columns>(columns_List);
		for (int i = 0; i < 6; i++) {
			ToHotTable call = new ToHotTable(linkList);
			call.setUrl(url);
			call.setTableName(tableName);
			new Thread(call).start();
	}

	}
	public static void newData(List<Columns> columns_List,String url,String tableName) throws Exception {
		LinkedList<Columns> linkList = new LinkedList<Columns>(columns_List);
		for (int i = 0; i < 6; i++) {
			ToNewTable call = new ToNewTable(linkList);
			call.setUrl(url);
			call.setTableName(tableName);
			new Thread(call).start();
	}

	}
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
//		while(true){
			List<Map<String, Object>> cloumnsList = GetKeyWordList.getAllColumns("");
			List<Columns> list = GetColumnList.getColumnList(cloumnsList);
			List<Columns> list_New = GetColumnListForNew.getColumnList(cloumnsList);
			try {
				new EntranceMain().hotData(list,"http://192.168.0.13:8080/bigdata-service/_v1/sortdata/facebook","t_facebook_neworhot"); 
				new EntranceMain().hotData(list,"http://192.168.0.13:8080/bigdata-service/_v1/sortdata/youtube","t_ytb_neworhot"); 
				new EntranceMain().hotData(list,"http://192.168.0.13:8080/bigdata-service/_v1/sortdata/twitter","t_twitter_neworhot"); 
				new EntranceMain().hotData(list,"http://192.168.0.13:8080/bigdata-service/_v1/sortdata/ggplus","t_ggplus_neworhot"); 
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^最新发布^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^最新发布^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				new EntranceMain().hotData(list_New,"http://192.168.0.13:8080/bigdata-service/_v1/sortdata/facebook","t_facebook_neworhot"); 
				new EntranceMain().hotData(list_New,"http://192.168.0.13:8080/bigdata-service/_v1/sortdata/youtube","t_ytb_neworhot"); 
				new EntranceMain().hotData(list_New,"http://192.168.0.13:8080/bigdata-service/_v1/sortdata/twitter","t_twitter_neworhot"); 
				new EntranceMain().hotData(list_New,"http://192.168.0.13:8080/bigdata-service/_v1/sortdata/ggplus","t_ggplus_neworhot");
			} catch (Exception e) {
				 logger.error("在:::"+sdf.format(new Date())+";;;EntranceMain这个类出现了"+e.toString());
				e.printStackTrace();
			}
//			try {
//				Thread.sleep(1000*3600*24);
//			} catch (InterruptedException e) {
//				logger.error("在:::"+sdf.format(new Date())+";;;EntranceMain这个类出现了"+e.toString());
//				e.printStackTrace();
//			}
//		}
	}
}
