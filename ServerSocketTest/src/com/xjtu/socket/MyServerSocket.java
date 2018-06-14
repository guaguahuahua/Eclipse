package com.xjtu.socket;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在这里直接继承Thread而不是Runnable的原因是需要在监听器类中调用线程的中断方法
 * 将线程终止
 * @author Administrator
 *
 */
public class MyServerSocket extends Thread{
	//服务器监听的端口号
	private int port;
	//允许客户连接等待的数量
	private int backlogs;
	//服务端的socket实例
	private ServerSocket serverSocket;
	//socket 用于输出和读取的缓冲区
	BufferedOutputStream bo;
	BufferedReader br;
	
	//构造方法
	public MyServerSocket(int port) {
		this.port=port;
		this.bo=null;
		this.br=null;
		serverSocket=null;
	}
	//构造方法
	public MyServerSocket(int port, int backlogs) {
		this.port=port;
		this.backlogs=backlogs;
		this.bo=null;
		this.br=null;
		serverSocket=null;
	}
	
	//空参构造方法，使用默认的参数
	public MyServerSocket() {
		this.port=8018;
		this.backlogs=10;
		this.bo=null;
		this.br=null;
		serverSocket=null;
	}
	
	
	/**
	 * 原始的服务端的socket
	 */
	public void myserverSocket() {
		// TODO Auto-generated method stub
		System.out.println("MyServersocket start!");
		try {
			//打开一个服务端的端口进行通信，并确认通信的客户端的数量
			@SuppressWarnings("resource")
			ServerSocket serverSocket=new ServerSocket(8018, 10);
			//服务端进行无限期监听一个端口号
			Socket fromClient=serverSocket.accept();
			//如果监听到了客户端的请求，那么获取客户端的输入流
			System.out.println("MyServersocket client port:"+fromClient.getPort());
			InputStreamReader ir=new InputStreamReader(fromClient.getInputStream());
			BufferedReader br=new BufferedReader(ir);
			//读取客户端的信息，并且进行打印
			String infor="";
			//Thread.sleep(1000);
			System.out.println("this is server print!");
			System.out.println(br.readLine());
			System.out.println("//////////");
			while((infor=br.readLine())!=null) {
				System.out.println(infor);
				System.out.println("//////////");
			}	
			fromClient.shutdownInput();
			//向客户端发消息
			BufferedOutputStream bo=new BufferedOutputStream(fromClient.getOutputStream());
			String s="This messsge comes from server, thank you!";
			byte []a=s.getBytes();
			System.out.println(a.length);
			bo.write(a);
			System.out.println("//////////");
			bo.flush();
			bo.close();
			System.out.println("server端执行结束，完成消息发送");
//			fromClient.shutdownOutput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
	}
	
	/**
	 * 通过线程调用的方式运行服务端的程序
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub		
		myserverSocket();
//		connectSocket();
	}
	
	/**
	 * 服务端的socket监听并且接收和发送消息
	 */
	public void connectSocket() {
		System.out.println("myServerSocket start!");
		System.out.println(port+", "+backlogs);
		try {
			//对socket对象进行初始化
			serverSocket=new ServerSocket(port, backlogs);
			//监听消息
			Socket socket=serverSocket.accept();
			//如果有消息到来，读取
			br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//在控制台上进行打印
			System.out.println("infor from client:");
			System.out.println("客户端通信端口："+socket.getPort());
			System.out.println("客户端通信地址："+socket.getRemoteSocketAddress());
			System.out.println("客户端发送的消息：");
			String temp="";
			while((temp=br.readLine())!=null) {
				System.out.println(temp);
			}
			//关闭输入流，后面的数据不再接收
			socket.shutdownInput();
			
			
			//服务端向客户端发送消息
			bo=new BufferedOutputStream(socket.getOutputStream());
			String message="This messsage from server, we got your infor";
			//服务端发送字节流
			bo.write(message.getBytes());
			bo.flush();
			//关闭输出
			socket.shutdownOutput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			releaseResources();
		}
		System.out.println("服务端执行完毕！");
	}
	
	/**
	 * 关闭连接，释放资源
	 */
	public void releaseResources() {
		try {
			//关闭缓冲写
			if(bo!=null) {
				bo.close();
			}
			//关闭缓冲读入
			if(br!=null) {
				br.close();
			}
			//关闭socket
//			if(serverSocket!=null) {
//				serverSocket.close();
//			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
