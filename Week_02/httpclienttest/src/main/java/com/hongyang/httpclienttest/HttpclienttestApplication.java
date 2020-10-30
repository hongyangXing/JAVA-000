package com.hongyang.httpclienttest;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
//import org.springframework.boot.SpringApplication;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

//@SpringBootApplication
public class HttpclienttestApplication {
	//HttpClient客户端
	private static CloseableHttpClient httpClient ;
	//响应
	private static CloseableHttpResponse httpResponse;

	public static void main(String[] args) {
		try {
			//创建一个httpClient对象，相当于创建了一个浏览器，用来访问URL链接
			httpClient = HttpClients.createDefault();
			//创建Get请求
			HttpGet httpGet = new HttpGet("http://localhost:8088/api/hello");
			//访问这个Get链接
			httpResponse = httpClient.execute(httpGet);
			String s = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
			System.out.println(s);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
