package com.xjtu.catchimg;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class EdgeDetect {

	public static void edgeDetect(Mat mat) {
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.load("C:\\opencv-3.3.0\\build\\java\\x64\\opencv_java330.dll");
		String file = "C:\\Users\\Administrator\\Desktop\\face";	//实验图是针对第二张
		Mat original=Imgcodecs.imread(file);
		
	}

}
