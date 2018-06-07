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
			//��������˶˿ں�
			ServerSocket server=new ServerSocket(8080);
			//�����˿ںţ���������ӵ�����������
			java.net.Socket soc=server.accept();
			//��ȡ�������е�������
			bf=new BufferedReader(new InputStreamReader(soc.getInputStream()));
			//��ӡ�ͻ��˷��͵�����
			while(bf!=null) {
				System.out.println(bf.readLine());
			}
			//�������ͻ��˽������
			pw=new PrintWriter(soc.getOutputStream(), true);
			pw.print("�������Է���˵�hello");
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
