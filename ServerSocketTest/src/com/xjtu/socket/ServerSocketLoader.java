package com.xjtu.socket;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 监听器类，随着tomcat一起启动，并重写两个监听器类方法
 * 在这里，tomcat不是servlet容器，而是作为一个独立的服务器进行
 * @author Administrator
 *
 */
public class ServerSocketLoader implements ServletContextListener{
	//创建一个服务端运行的socket对象，并在初始化的同时启动这个线程
	private ListenerClass listener;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
		if(listener!=null) {
			//中断当前执行的线程
			listener.interrupt();
			System.out.println("ServletContext Listener2 stop");
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		//这块调用了构造方法，所以使用的是默认的端口和等待队列的长度
		
		listener=new ListenerClass();
		listener.start();
	}
}
