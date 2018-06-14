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
	 * ���ϵļ�����û�пͻ��˵�����
	 */
	public void listener() {
		int port=8018;
		int backlogs=10;
		ServerSocket serverSocket = null;
		int count=0;

		try {
			//����˼����˿ں�
			serverSocket = new ServerSocket(port, backlogs);
			System.out.println("server�˼�����ʼ");
			
			
			while(true) {
				Socket socket=serverSocket.accept();
				ServerSocketTest instance=new ServerSocketTest(socket);
				instance.start();
				System.out.println("�ͻ���������"+count++);
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
