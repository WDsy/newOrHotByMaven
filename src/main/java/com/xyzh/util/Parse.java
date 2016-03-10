package com.xyzh.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.xyzh.thread.EntranceMain;


/**
 * @author 作者：       周卫东
 * @E-mail 邮箱：       zhouweidong@gochinatv.com
 * @version 创建时间：2016年1月30日 下午12:09:13
 * 类说明
*/
public class Parse {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static Logger logger  =  Logger.getLogger(Parse. class );
	public String parse(String name,String fileName){
		String name_str = "";
		Properties p = new Properties();
		if(StringUtils.isNotEmpty(fileName)){
			try {
				File file = new File(fileName);
				InputStream in = new FileInputStream(file);
				p.load(in);
				name_str = p.getProperty(name);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				throw new Exception("未指定配置文件");
			} catch (Exception e) {
				logger.error("在:::"+sdf.format(new Date())+";;;parse这个类出现了"+e.toString());
				e.printStackTrace();
			}
		}
		return name_str;
	}
}
