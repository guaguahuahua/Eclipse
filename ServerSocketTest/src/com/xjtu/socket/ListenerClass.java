package com.xjtu.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ListenerClass extends Thread{

	@Override
	public void run() {
		listener();
	}
	
	
	/**
	 * 不断的监听有没有客户端的连接
	 */
	public void listener() {
		int port=8018;
		int backlogs=10;
		ServerSocket serverSocket = null;
		int count=0;

		try {
			//服务端监听端口号
			serverSocket = new ServerSocket(port, backlogs);
			System.out.println("server端监听开始");
			
			
			while(true) {
				Socket socket=serverSocket.accept();
				ServerSocketTest instance=new ServerSocketTest(socket);
				instance.start();
				System.out.println("客户端数量："+count++);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try {
				if(serverSocket!=null) {
					serverSocket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
