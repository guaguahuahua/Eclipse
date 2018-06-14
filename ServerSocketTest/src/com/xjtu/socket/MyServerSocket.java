package com.xjtu.socket;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ������ֱ�Ӽ̳�Thread������Runnable��ԭ������Ҫ�ڼ��������е����̵߳��жϷ���
 * ���߳���ֹ
 * @author Administrator
 *
 */
public class MyServerSocket extends Thread{
	//�����������Ķ˿ں�
	private int port;
	//����ͻ����ӵȴ�������
	private int backlogs;
	//����˵�socketʵ��
	private ServerSocket serverSocket;
	//socket ��������Ͷ�ȡ�Ļ�����
	BufferedOutputStream bo;
	BufferedReader br;
	
	//���췽��
	public MyServerSocket(int port) {
		this.port=port;
		this.bo=null;
		this.br=null;
		serverSocket=null;
	}
	//���췽��
	public MyServerSocket(int port, int backlogs) {
		this.port=port;
		this.backlogs=backlogs;
		this.bo=null;
		this.br=null;
		serverSocket=null;
	}
	
	//�ղι��췽����ʹ��Ĭ�ϵĲ���
	public MyServerSocket() {
		this.port=8018;
		this.backlogs=10;
		this.bo=null;
		this.br=null;
		serverSocket=null;
	}
	
	
	/**
	 * ԭʼ�ķ���˵�socket
	 */
	public void myserverSocket() {
		// TODO Auto-generated method stub
		System.out.println("MyServersocket start!");
		try {
			//��һ������˵Ķ˿ڽ���ͨ�ţ���ȷ��ͨ�ŵĿͻ��˵�����
			@SuppressWarnings("resource")
			ServerSocket serverSocket=new ServerSocket(8018, 10);
			//����˽��������ڼ���һ���˿ں�
			Socket fromClient=serverSocket.accept();
			//����������˿ͻ��˵�������ô��ȡ�ͻ��˵�������
			System.out.println("MyServersocket client port:"+fromClient.getPort());
			InputStreamReader ir=new InputStreamReader(fromClient.getInputStream());
			BufferedReader br=new BufferedReader(ir);
			//��ȡ�ͻ��˵���Ϣ�����ҽ��д�ӡ
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
			//��ͻ��˷���Ϣ
			BufferedOutputStream bo=new BufferedOutputStream(fromClient.getOutputStream());
			String s="This messsge comes from server, thank you!";
			byte []a=s.getBytes();
			System.out.println(a.length);
			bo.write(a);
			System.out.println("//////////");
			bo.flush();
			bo.close();
			System.out.println("server��ִ�н����������Ϣ����");
//			fromClient.shutdownOutput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
	}
	
	/**
	 * ͨ���̵߳��õķ�ʽ���з���˵ĳ���
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub		
		myserverSocket();
//		connectSocket();
	}
	
	/**
	 * ����˵�socket�������ҽ��պͷ�����Ϣ
	 */
	public void connectSocket() {
		System.out.println("myServerSocket start!");
		System.out.println(port+", "+backlogs);
		try {
			//��socket������г�ʼ��
			serverSocket=new ServerSocket(port, backlogs);
			//������Ϣ
			Socket socket=serverSocket.accept();
			//�������Ϣ��������ȡ
			br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//�ڿ���̨�Ͻ��д�ӡ
			System.out.println("infor from client:");
			System.out.println("�ͻ���ͨ�Ŷ˿ڣ�"+socket.getPort());
			System.out.println("�ͻ���ͨ�ŵ�ַ��"+socket.getRemoteSocketAddress());
			System.out.println("�ͻ��˷��͵���Ϣ��");
			String temp="";
			while((temp=br.readLine())!=null) {
				System.out.println(temp);
			}
			//�ر�����������������ݲ��ٽ���
			socket.shutdownInput();
			
			
			//�������ͻ��˷�����Ϣ
			bo=new BufferedOutputStream(socket.getOutputStream());
			String message="This messsage from server, we got your infor";
			//����˷����ֽ���
			bo.write(message.getBytes());
			bo.flush();
			//�ر����
			socket.shutdownOutput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			releaseResources();
		}
		System.out.println("�����ִ����ϣ�");
	}
	
	/**
	 * �ر����ӣ��ͷ���Դ
	 */
	public void releaseResources() {
		try {
			//�رջ���д
			if(bo!=null) {
				bo.close();
			}
			//�رջ������
			if(br!=null) {
				br.close();
			}
			//�ر�socket
//			if(serverSocket!=null) {
//				serverSocket.close();
//			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
