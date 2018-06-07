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
	
	//��ǰ�������cookie
	String key="ASP.NET_SessionId";
	String value="z1wyodrbeffltc55wcfj4za2";
	/**
	 * ����url��ȡ��Ӧ��ҳ��
	 */
	public void getHtml() {
		Connection conn=Jsoup.connect(location);
		Document doc=null;
		try {
			//��ȡhtmlҳ��
			doc=conn.get();
			//��ȡ����˵ķ��͵�cookie
			Map<String, String> map=conn.response().cookies();
			for(Entry<String, String> en:map.entrySet()) {
				cookieKey=en.getKey();
				cookieValue=en.getValue();
				System.out.println("ͨ��http header��ȡcookie��"+cookieKey+","+cookieValue);
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
	 * ͨ��jsoup����ȡ��֤��
	 */
	public void getValidImgByJsoup() {
		
		//��ǰ�������cookie
		String key="ASP.NET_SessionId";
		String value="z1wyodrbeffltc55wcfj4za2";
		
		//��ȡ��֤��
		//������get��ʽ���ӵ���֤���url����
		Connection conn=Jsoup.connect(validImg);
		conn.method(Connection.Method.GET);
		//˵�������Ϣ
		conn.cookie(key, value);
		conn.header("Referer", location);
		Document doc=null;
		try {
			//ʹ�õ���response�õ���Ӧ���������ݣ�������ʹ��get��ʽ�������ᵼ���������ݱ�����
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
	 * ͨ��urlconnecti��ʽ��ȡ��֤��
	 */
	public void getValidImgByUrl() {

		
		ByteArrayOutputStream outs = null;
		InputStream ins=null;
		HttpURLConnection httpURL=null;
		OutputStream out=null;
		try {
			//������������
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
			//���òο�����һ��·�����ϸ�·����Ӧ��cookie
			httpURL.setRequestProperty("cookie", key+"="+value);
			httpURL.setRequestProperty("Referer", location);			
			ins=httpURL.getInputStream();			
			//��õĶ������ֽ���
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
	 * ���ĵ�д����Ӧ�õ���html
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
	 * ���ݻ�ȡ�õ�����Ϣ�õ��γ̱�
	 */
	public void getCourseTable() {
		//�����һ���û��������γɲ�����
		String s="Sel_XNXQ=20171&Sel_JS=0000247&type=1&txt_yzm=wrjm";
		//��һ����������
		ByteArrayOutputStream outs = null;
		InputStream ins=null;
		HttpURLConnection httpURL=null;
		DataOutputStream out=null;
		try {
			//������������
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
			//���òο�����һ��·�����ϸ�·����Ӧ��cookie
			httpURL.setRequestProperty("cookie", key+"="+value);
			httpURL.setRequestProperty("Referer", location);
			//���û�����д�뵽������
			out = new DataOutputStream(httpURL.getOutputStream());
			out.writeBytes(s);
			out.flush();
			//��ȡ���ӵķ�����ͼ
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
	 * ʹ��jsoup�ķ�ʽ��ȡ�γ̱�
	 * 
	 */
	public void getCourseTableByJsoup() {
		//��ȡ����
		Connection conn=Jsoup.connect(courseTable);
		//����cookie�Ͳο���·���ύ����
		conn.cookie(key, value);
		conn.referrer(location);
		conn.method(Connection.Method.POST);
		//��ʹ��post�ύ�ļ�ֵ�Է���map���У�
		Map<String, String> map=new HashMap<String, String>();
		map.put("Sel_XNXQ", "20171");
		map.put("Sel_JS", "0000323");
		map.put("type", "1");
		map.put("txt_yzm", "ctsg");
		//��request�������������
		conn.data(map);
		try {
			//ִ��request
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
	 * ��ץȡ���Ŀγ�����д�뵽�ı��ĵ���
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
	 * ������ʦ����Ϣ
	 */
	public void parseTeacherInfor() {
		String teacherInfor="E:\\Eclipse\\Crawler\\WebContent\\teacherInfor.txt";
		try {
			//������ʦ��Ϣ
			Document doc=Jsoup.parse(new File(teacherInfor), "utf-8");
			//ʹ��mapӳ������Ϣ
			Map<String, String> map=new HashMap<String, String>();
			for(Element e:doc.getElementsByTag("option")) {
				//����Ӧ������ʦ��Ϣ��id�ź�����
				map.put(e.val(), e.text());				
//				System.out.println(e.val()+", "+e.text());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * ������õĿγ�����
	 */
	public void parseCourseInfor() {
		String courseInfor="E:\\Eclipse\\Crawler\\WebContent\\courseInfor.txt";
		List<String> courseDetails=new ArrayList<String>();
		try {
			Document doc=Jsoup.parse(new File(courseInfor), "GBK");
			Elements eles=doc.getElementsByTag("tr");

			//��һ�����ݾ���һ�������������������������			
			for(int i=1; i<eles.size(); i++) {
				Element e=eles.get(i);
				//�����õ������������������
				Elements trEle=e.select("td");
				for(int j=0; j<trEle.size(); j++) {
					Element tdEle=trEle.get(j);
					courseDetails.add(tdEle.text());
					//��ӡ��������
					//System.out.println(tdEle.text());
				}
				//��ӡ���е����е�����
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
