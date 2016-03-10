package com.xyzh.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;


/**
 * @author 作者： 周卫东
 * @E-mail 邮箱： zhouweidong@gochinatv.com
 * @version 创建时间：2016年2月18日 下午4:53:41 类说明
 */
public class HttpRequestClients {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static Logger logger  =  Logger.getLogger(HttpRequestClients. class );
	public static String getResponseAsStringByPostMethod (String url, String listPair)throws NullPointerException, UnsupportedEncodingException, IllegalStateException, IOException {
		HttpClient httpclient;
		StringBuilder sb = null;
		httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("param", listPair));
		BufferedReader br;
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			HttpResponse response = httpclient.execute(httpPost);
			sb = new StringBuilder();
			br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			httpclient.getConnectionManager().shutdown();
			return sb.toString();
	}
	
	public static String testConnection(String url,String json)throws Exception{
		return json;
	} 

	public static void main(String[] args) {
		try {
//			String a = getResponseAsStringByPostMethod("http://192.168.0.13:8080/bigdata-service/_v1/sortdata/youtube","{\"dayNum\":\"30\",\"field\":\"viewCount\",\"from\":\"0\",\"keyWord\":{\"145690017417148\":\"北京故宫\",\"145690017417149\":\"储秀宫\",\"145690017417150\":\"故宫\",\"145690017417151\":\"故宫博物院\",\"145690017417152\":\"乾清宫\",\"145690017417153\":\"太和殿\",\"145690017417154\":\"养心殿\",\"145690017417155\":\"紫禁城\",\"145690017417156\":\"故宫\",\"145690017417157\":\"紫禁城\",\"145690017417158\":\"清明上河图\",\"145690017417159\":\"步辇图\",\"145690017417160\":\"“大禹治水”玉山\",\"145690017417161\":\"THE PALACE MUSEUM\",\"145690017417262\":\"The Forbidden City\",\"145690017417263\":\"Emperor Taizong Receiving the Tibetan Envoy\",\"145690017417264\":\"Along the River During the Qingming Festival\"},\"limit\":\"50\",\"point\":\"故宫博物院\"}");
			String a = testConnection("http://192.168.0.13:8080/bigdata-service/_v1/sortdata/youtube","{\"dayNum\":\"30\",\"field\":\"viewCount\",\"from\":\"0\",\"keyWord\":{\"145690017417148\":\"北京故宫\",\"145690017417149\":\"储秀宫\",\"145690017417150\":\"故宫\",\"145690017417151\":\"故宫博物院\",\"145690017417152\":\"乾清宫\",\"145690017417153\":\"太和殿\",\"145690017417154\":\"养心殿\",\"145690017417155\":\"紫禁城\",\"145690017417156\":\"故宫\",\"145690017417157\":\"紫禁城\",\"145690017417158\":\"清明上河图\",\"145690017417159\":\"步辇图\",\"145690017417160\":\"“大禹治水”玉山\",\"145690017417161\":\"THE PALACE MUSEUM\",\"145690017417262\":\"The Forbidden City\",\"145690017417263\":\"Emperor Taizong Receiving the Tibetan Envoy\",\"145690017417264\":\"Along the River During the Qingming Festival\"},\"limit\":\"50\",\"point\":\"故宫博物院\"}");
			System.out.println(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
