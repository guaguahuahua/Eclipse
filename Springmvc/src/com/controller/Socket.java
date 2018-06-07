package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class Socket {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader bf=null;
		PrintWriter pw=null;
		
		try {
			//开启服务端端口号
			ServerSocket server=new ServerSocket(8080);
			//监听端口号，如果有链接到来，接受它
			java.net.Socket soc=server.accept();
			//读取该链接中的数据流
			bf=new BufferedReader(new InputStreamReader(soc.getInputStream()));
			//打印客户端发送的链接
			while(bf!=null) {
				System.out.println(bf.readLine());
			}
			//服务端向客户端进行输出
			pw=new PrintWriter(soc.getOutputStream(), true);
			pw.print("这是来自服务端的hello");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				bf.close();
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
