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
		//��ȡ��Ƶ�ļ�
		VideoCapture cap = new VideoCapture(path);
		cap.open(path);
		System.out.println("����Ƶ��"+cap.isOpened());
		//�ж���Ƶ�Ƿ��
		if (cap.isOpened()) {
			//��֡��
			double frameCount = cap.get(Videoio.CV_CAP_PROP_FRAME_COUNT);
			System.out.println("��Ƶ��֡��:"+frameCount);
			//֡��
			double fps = cap.get(Videoio.CV_CAP_PROP_FPS);
			System.out.println("��Ƶ֡��"+fps);
			//ʱ�䳤��
			double len = frameCount / fps;
			System.out.println("��Ƶ��ʱ��:"+len);
			Double d_s = new Double(len);
			System.out.println(d_s.intValue());
			Mat frame = new Mat();
			for (int i = 0; i <=frameCount ; i++) {  //d_s.intValue()
				//������Ƶ��λ��(��λ:����)
				cap.set(Videoio.CV_CAP_PROP_POS_MSEC, i * 20);
				//��ȡ��һ֡����
				if (cap.read(frame)) {
				System.out.println("���ڱ���");
				//���滭�浽����Ŀ¼
				Imgcodecs.imwrite("E:/Pigface/"+j+"/"+i+".jpg", frame);
				}
					//�ر���Ƶ�ļ�
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
