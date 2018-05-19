package com.xjtu.catchimg;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

public class CatchImages {

	
	public static void catchImages() {
		for(int j=2; j<=30; j++) {
		String path="E:/Contest/train/"+j+".mp4";
		//读取视频文件
		VideoCapture cap = new VideoCapture(path);
		cap.open(path);
		System.out.println("打开视频："+cap.isOpened());
		//判断视频是否打开
		if (cap.isOpened()) {
			//总帧数
			double frameCount = cap.get(Videoio.CV_CAP_PROP_FRAME_COUNT);
			System.out.println("视频总帧数:"+frameCount);
			//帧率
			double fps = cap.get(Videoio.CV_CAP_PROP_FPS);
			System.out.println("视频帧率"+fps);
			//时间长度
			double len = frameCount / fps;
			System.out.println("视频总时长:"+len);
			Double d_s = new Double(len);
			System.out.println(d_s.intValue());
			Mat frame = new Mat();
			for (int i = 0; i <=frameCount ; i++) {  //d_s.intValue()
				//设置视频的位置(单位:毫秒)
				cap.set(Videoio.CV_CAP_PROP_POS_MSEC, i * 20);
				//读取下一帧画面
				if (cap.read(frame)) {
				System.out.println("正在保存");
				//保存画面到本地目录
				Imgcodecs.imwrite("E:/Pigface/"+j+"/"+i+".jpg", frame);
				}
					//关闭视频文件
			}
		}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		catchImages();
		
	}
	
	

}
