package com.xyzh.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 * @author jiyangnan
 * @since 2015-12-16 16:40:39
 * @version 1.0 �?��多个数据�?
 */
public class DatabaseUtil {
	private  String fileName;
	private  String username;
	private  String password;
	private  String driver;
	private  String urlForReptile;
	private  String urlForProduct;
	 static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		static Logger logger  =  Logger.getLogger(DatabaseUtil. class );
	public DatabaseUtil(String fileName,String method){
		this.fileName = fileName;
		try {
			if(method.equals("getConnForReptile")){
				getConnForReptile();
			}else if(method.equals("getConnForProduct")){
				getConnForProduct();
			}else{
				System.out.println("请检查请求！！！");
			}
			
		} catch (SQLException e) {
			logger.error("在:::"+sdf.format(new Date())+";;;"+this.getClass().getName()+"这个类出现了"+e.toString());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			logger.error("在:::"+sdf.format(new Date())+";;;"+this.getClass().getName()+"这个类出现了"+e.toString());
			e.printStackTrace();
		}
		
	}
	
	public  void setFileName(String fileName){
		this.fileName = fileName;
	}
	
	public  void parse(){
		if(StringUtils.isNotEmpty(fileName)){
			Properties p = new Properties();
			try {
				File file = new File(fileName);
				InputStream in = new FileInputStream(file);
				p.load(in);
				username = p.getProperty("username");
				password = p.getProperty("password");
				urlForReptile = p.getProperty("urlForReptile");
				urlForProduct = p.getProperty("urlForProduct");
				driver = p.getProperty("driver");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("在:::"+sdf.format(new Date())+";;;"+this.getClass().getName()+"这个类出现了"+e.toString());
				e.printStackTrace();
			}
		}else{
			try {
				throw new Exception("未指定数据库配置");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("在:::"+sdf.format(new Date())+";;;"+this.getClass().getName()+"这个类出现了"+e.toString());
				e.printStackTrace();
			}
		}
	}
	
	public Connection getConnForReptile() throws SQLException, ClassNotFoundException{
		Connection connForReptile=null;
		this.parse();
		Class.forName(driver);
		connForReptile = DriverManager.getConnection(urlForReptile, username, password);
		return connForReptile;
	}
	
	public Connection getConnForProduct() throws ClassNotFoundException, SQLException{
		Connection connForProduct = null;
		this.parse();
		Class.forName(driver);
		connForProduct = DriverManager.getConnection(urlForProduct, username, password);
		return connForProduct;
	}
	
}

