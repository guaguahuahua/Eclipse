package com.xjtu.socket;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerSocketTest extends Thread{
	//监听获得的客户端的socket
	private Socket socket;
	
	public ServerSocketTest(Socket socket) {
		this.socket=socket;
	}
	public ServerSocketTest() {
		this.socket=null;
	}
	
	//开启线程执行服务端的处理
	@Override
	public void run() {
		// TODO Auto-generated method stub
		server();
	}
	
	/**
	 * Server端的socket，但是每次只能接收客户端一个socket
	 * 原因在于这个线程执行一次之后所有的端口资源就会被释放掉，所以
	 * 当客户端再次连接的时候就无法连接到服务器，因为服务端的socket已经关闭
	 */
	public void server() {
		ServerSocket serverSocket=null;
		BufferedOutputStream bo=null;
		BufferedReader br=null;
		try {
			//接收客户端的消息
			br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("客户端口号："+socket.getPort()+", "+"客户端地址："+socket.getRemoteSocketAddress());
			System.out.print("from client:");
			String temp="";
			while((temp=br.readLine())!=null) {
				System.out.println(temp);
			}
			socket.shutdownInput();
			
			//向客户端发送消息
			bo=new BufferedOutputStream(socket.getOutputStream());
			String message="This message from server";
			Scanner sc=new Scanner(System.in);
			System.out.println("请输入发给客户端的消息！");
			message=sc.nextLine();
			bo.write(message.getBytes());
			bo.flush();
			socket.shutdownOutput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try {
				if(bo!=null) {
					bo.close();
				}
				if(br!=null) {
					br.close();
				}
				if(serverSocket!=null) {
					serverSocket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("资源释放完毕！");
		}
	}
	
}
