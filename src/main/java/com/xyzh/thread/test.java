package com.xyzh.thread;

import org.apache.log4j.Logger;

/**
 * @author 作者：       周卫东
 * @E-mail 邮箱：       zhouweidong@gochinatv.com
 * @version 创建时间：2016年3月9日 下午3:41:43
 * 类说明
*/
public class test {
	 public   static   void  main(String[] args)  {
		 Logger logger  =  Logger.getLogger(test. class );
//	        PropertyConfigurator.configure( "log4j.properties " );
	        logger.info("这是输出的信息内容");
	        logger.debug( " debug " );
	        logger.error( " error " );
	    } 
}
