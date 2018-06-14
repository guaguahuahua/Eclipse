package com.xjtu.socket;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * �������࣬����tomcatһ������������д�����������෽��
 * �����tomcat����servlet������������Ϊһ�������ķ���������
 * @author Administrator
 *
 */
public class ServerSocketLoader implements ServletContextListener{
	//����һ����������е�socket���󣬲��ڳ�ʼ����ͬʱ��������߳�
	private ListenerClass listener;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
		if(listener!=null) {
			//�жϵ�ǰִ�е��߳�
			listener.interrupt();
			System.out.println("ServletContext Listener2 stop");
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		//�������˹��췽��������ʹ�õ���Ĭ�ϵĶ˿ں͵ȴ����еĳ���
		
		listener=new ListenerClass();
		listener.start();
	}
}
