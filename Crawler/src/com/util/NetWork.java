package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetWork {
	
	private HttpURLConnection urlConn;
	private URL url;
//	String location="http://211.67.107.38/jwweb/ZNPK/TeacherKBFB.aspx";
	String location="http://www.flvcd.com";
	
	/**
	 * ���URL����
	 */
	public void connect() {
		try {
			url=new URL(location);
			urlConn=(HttpURLConnection) url.openConnection();
			urlConn.setConnectTimeout(50000);
			urlConn.setDoInput(true);
			urlConn.setDoOutput(true);
			urlConn.setRequestMethod("GET");
			urlConn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			urlConn.addRequestProperty("Upgrade-Insecure-Requests", "1");
			urlConn.addRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 10.0; WOW64;"
					+ " Trident/7.0; .NET4.0C; .NET4.0E; .NET CLR 2.0.50727; .NET CLR 3.0.30729;"
					+ " .NET CLR 3.5.30729; InfoPath.3)");
			urlConn.setUseCaches(false);
			System.out.println("ִ�е���!");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ����url���ӻ��HTMLҳ��
	 */
	public void getHtml() {
		BufferedReader br=null;
		StringBuilder sb=new StringBuilder();
		try {
			br=new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
			String line="";
			while(br.readLine() != null) {
				line=br.readLine();
				sb.append(line);
			}			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			if(br!=null) {
				try {
					br.close();				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
			urlConn.disconnect();
		}
		System.out.println(sb.toString());
	}
	
	public static void main(String []args) {
		NetWork net=new NetWork();
		net.connect();
		net.getHtml();
	}
}