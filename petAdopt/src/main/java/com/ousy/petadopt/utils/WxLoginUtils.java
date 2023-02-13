package com.ousy.petadopt.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @title: wxLoginUtils
 * @Author miller.ouyang
 * @Date: 2023/1/13 17:50
 * @Version 1.0
 */
public class WxLoginUtils {
	private static final CloseableHttpClient httpclient = HttpClients.createDefault();
	/**
	 * 发送HttpGet请求
	 * @param url   请求地址 *
	 * @return      返回字符串
	 */
	public static String sendGet(String url, Map<String, String> headerList) throws Exception{
		String result = null;
		CloseableHttpResponse response = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			if(headerList != null){
				Set<String> keySet = headerList.keySet();
				for (String key :keySet) {
					httpGet.setHeader(key, headerList.get(key));
				}
			}
			response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity);
			}
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public void getAuth(Map<String,Object> params){
		String url = "";  //此处是你的api params中会携带参数入jsCode,iv,rawData,userInfo等参数
		try {
			String sendGet = sendGet(url, null);
			JSONObject jsonObject = JSONObject.parseObject(sendGet);
			Object openId = jsonObject.get("openId");
			Object session_key = jsonObject.get("session_key");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
