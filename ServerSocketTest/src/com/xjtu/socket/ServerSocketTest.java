package com.xjtu.socket;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerSocketTest extends Thread{
	//������õĿͻ��˵�socket
	private Socket socket;
	
	public ServerSocketTest(Socket socket) {
		this.socket=socket;
	}
	public ServerSocketTest() {
		this.socket=null;
	}
	
	//�����߳�ִ�з���˵Ĵ���
	@Override
	public void run() {
		// TODO Auto-generated method stub
		server();
	}
	
	/**
	 * Server�˵�socket������ÿ��ֻ�ܽ��տͻ���һ��socket
	 * ԭ����������߳�ִ��һ��֮�����еĶ˿���Դ�ͻᱻ�ͷŵ�������
	 * ���ͻ����ٴ����ӵ�ʱ����޷����ӵ�����������Ϊ����˵�socket�Ѿ��ر�
	 */
	public void server() {
		ServerSocket serverSocket=null;
		BufferedOutputStream bo=null;
		BufferedReader br=null;
		try {
			//���տͻ��˵���Ϣ
			br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("�ͻ��˿ںţ�"+socket.getPort()+", "+"�ͻ��˵�ַ��"+socket.getRemoteSocketAddress());
			System.out.print("from client:");
			String temp="";
			while((temp=br.readLine())!=null) {
				System.out.println(temp);
			}
			socket.shutdownInput();
			
			//��ͻ��˷�����Ϣ
			bo=new BufferedOutputStream(socket.getOutputStream());
			String message="This message from server";
			Scanner sc=new Scanner(System.in);
			System.out.println("�����뷢���ͻ��˵���Ϣ��");
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
			System.out.println("��Դ�ͷ���ϣ�");
		}
	}
	
}
