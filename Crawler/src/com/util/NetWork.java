package com.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NetWork {

	private static String location="http://211.67.107.38/jwweb/ZNPK/TeacherKBFB.aspx";
	private static String cookieKey;
	private static String cookieValue;
	private static String filePath="E:\\Eclipse\\Crawler\\WebContent";
	private static String validImg="http://211.67.107.38/jwweb/sys/ValidateCode.aspx";
	private static String courseTable="http://211.67.107.38/jwweb/ZNPK/TeacherKBFB_rpt.aspx";
	
	//当前浏览器的cookie
	String key="ASP.NET_SessionId";
	String value="z1wyodrbeffltc55wcfj4za2";
	/**
	 * 根据url获取响应的页面
	 */
	public void getHtml() {
		Connection conn=Jsoup.connect(location);
		Document doc=null;
		try {
			//获取html页面
			doc=conn.get();
			//获取服务端的发送的cookie
			Map<String, String> map=conn.response().cookies();
			for(Entry<String, String> en:map.entrySet()) {
				cookieKey=en.getKey();
				cookieValue=en.getValue();
				System.out.println("通过http header获取cookie："+cookieKey+","+cookieValue);
			}
			FileWriter file=new FileWriter(filePath+"\\html.txt", false);
			file.write(doc.html());
			file.flush();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过jsoup，获取验证码
	 */
	public void getValidImgByJsoup() {
		
		//当前浏览器的cookie
		String key="ASP.NET_SessionId";
		String value="z1wyodrbeffltc55wcfj4za2";
		
		//获取验证码
		//首先以get方式连接到验证码的url上面
		Connection conn=Jsoup.connect(validImg);
		conn.method(Connection.Method.GET);
		//说明身份信息
		conn.cookie(key, value);
		conn.header("Referer", location);
		Document doc=null;
		try {
			//使用的是response得到响应的所有内容，不可以使用get方式，那样会导致整个内容被解析
			Response response=conn.execute();
			byte[] r=response.bodyAsBytes();
			OutputStream out=new FileOutputStream(filePath+"\\\\jsoupValidImg.txt");
			out.write(r);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过urlconnecti方式获取验证码
	 */
	public void getValidImgByUrl() {

		
		ByteArrayOutputStream outs = null;
		InputStream ins=null;
		HttpURLConnection httpURL=null;
		OutputStream out=null;
		try {
			//设置请求属性
			URL imgPath=new URL(validImg);
			httpURL=(HttpURLConnection) imgPath.openConnection();
			httpURL.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpURL.addRequestProperty("Upgrade-Insecure-Requests", "1");
			httpURL.addRequestProperty("Connection", "keep-alive");
			httpURL.addRequestProperty("Cache-Control", "max-age=0");
			httpURL.addRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0");
			httpURL.setRequestMethod("GET");
			httpURL.setUseCaches(false);
			httpURL.setDoOutput(true);
			httpURL.setDoInput(true);
			httpURL.setConnectTimeout(20000);
			httpURL.setReadTimeout(30000);
			//设置参考的上一个路径，上个路径对应的cookie
			httpURL.setRequestProperty("cookie", key+"="+value);
			httpURL.setRequestProperty("Referer", location);			
			ins=httpURL.getInputStream();			
			//获得的二进制字节流
			byte[] buffer = null;
			outs = new ByteArrayOutputStream();
			buffer = new byte[1024];
			int len = 0;
			while ((len = ins.read(buffer)) != -1) {
				outs.write(buffer, 0, len);
			}
			buffer = outs.toByteArray();
			out=new FileOutputStream("E:\\Eclipse\\Crawler\\validImg.txt");
			out.write(buffer);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {			
			try {
				if (outs != null) {
					outs.close();
				}
				if (ins != null) {
					ins.close();
				}
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		httpURL.disconnect();
	}
	
	
	/**
	 * 向文档写入响应得到的html
	 * @param filePath
	 * @param doc
	 */
	public void writeTxt(String filePath, Document doc) {		
		try {
			FileWriter file=new FileWriter(filePath, false);
			file.write(doc.html()); 
			file.flush();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 根据获取得到的信息得到课程表
	 */
	public void getCourseTable() {
		//这块是一个用户的请求形成参数串
		String s="Sel_XNXQ=20171&Sel_JS=0000247&type=1&txt_yzm=wrjm";
		//打开一个网络连接
		ByteArrayOutputStream outs = null;
		InputStream ins=null;
		HttpURLConnection httpURL=null;
		DataOutputStream out=null;
		try {
			//设置请求属性
			URL imgPath=new URL(courseTable);
			httpURL=(HttpURLConnection) imgPath.openConnection();
			httpURL.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpURL.addRequestProperty("Upgrade-Insecure-Requests", "1");
			httpURL.addRequestProperty("Connection", "keep-alive");
			httpURL.addRequestProperty("Cache-Control", "max-age=0");
			httpURL.addRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0");
			httpURL.setRequestMethod("GET");
			httpURL.setUseCaches(false);
			httpURL.setDoOutput(true);
			httpURL.setDoInput(true);
			httpURL.setConnectTimeout(20000);
			httpURL.setReadTimeout(30000);
			//设置参考的上一个路径，上个路径对应的cookie
			httpURL.setRequestProperty("cookie", key+"="+value);
			httpURL.setRequestProperty("Referer", location);
			//将用户参数写入到连接中
			out = new DataOutputStream(httpURL.getOutputStream());
			out.writeBytes(s);
			out.flush();
			//获取连接的返回视图
			ins=httpURL.getInputStream();
			StringBuilder sb=new StringBuilder();
			BufferedReader br=new BufferedReader(new InputStreamReader(ins,"gbk"));
			while(br.readLine()!=null) {
				String t=br.readLine();
				sb.append(t);
			}
			System.out.println(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	/**
	 * 使用jsoup的方式获取课程表
	 * 
	 */
	public void getCourseTableByJsoup() {
		//获取连接
		Connection conn=Jsoup.connect(courseTable);
		//设置cookie和参考的路径提交方法
		conn.cookie(key, value);
		conn.referrer(location);
		conn.method(Connection.Method.POST);
		//将使用post提交的键值对放在map当中；
		Map<String, String> map=new HashMap<String, String>();
		map.put("Sel_XNXQ", "20171");
		map.put("Sel_JS", "0000323");
		map.put("type", "1");
		map.put("txt_yzm", "ctsg");
		//向request中添加请求属性
		conn.data(map);
		try {
			//执行request
			Response response=conn.ignoreContentType(true).execute();			
			System.out.println(response.body());
			writeToTxt(response.body());
			//
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 将抓取到的课程数据写入到文本文档中
	 * @param s
	 */
	public void writeToTxt(String s) {
		try {
			FileWriter writer=new FileWriter("E:\\Eclipse\\Crawler\\WebContent\\courseInfor.txt");
			writer.write(s);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 解析老师的信息
	 */
	public void parseTeacherInfor() {
		String teacherInfor="E:\\Eclipse\\Crawler\\WebContent\\teacherInfor.txt";
		try {
			//解析老师信息
			Document doc=Jsoup.parse(new File(teacherInfor), "utf-8");
			//使用map映射存放信息
			Map<String, String> map=new HashMap<String, String>();
			for(Element e:doc.getElementsByTag("option")) {
				//这块对应的是老师信息的id号和名字
				map.put(e.val(), e.text());				
//				System.out.println(e.val()+", "+e.text());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * 解析获得的课程数据
	 */
	public void parseCourseInfor() {
		String courseInfor="E:\\Eclipse\\Crawler\\WebContent\\courseInfor.txt";
		List<String> courseDetails=new ArrayList<String>();
		try {
			Document doc=Jsoup.parse(new File(courseInfor), "GBK");
			Elements eles=doc.getElementsByTag("tr");

			//第一条数据就是一个对整体数据输出，所以跳过			
			for(int i=1; i<eles.size(); i++) {
				Element e=eles.get(i);
				//搜索得到行里面的所有列数据
				Elements trEle=e.select("td");
				for(int j=0; j<trEle.size(); j++) {
					Element tdEle=trEle.get(j);
					courseDetails.add(tdEle.text());
					//打印列中数据
					//System.out.println(tdEle.text());
				}
				//打印该行的所有的数据
				//System.out.println(e.text());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NetWork net=new NetWork();
//		net.getHtml();
//		net.getValidImgByUrl();
//		net.getValidImgByJsoup();
//		net.getCourseTableByJsoup();
//		net.parseTeacherInfor();
		net.parseCourseInfor();
	}

}
