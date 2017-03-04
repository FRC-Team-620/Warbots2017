package org.usfirst.frc620.Warbot2017.testing;

import java.util.concurrent.atomic.AtomicInteger;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.usfirst.frc620.Warbot2017.Robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.XboxController;

public class CameraTest {
	private VideoCapture[] cams;
//	private AtomicInteger id =
	public CameraTest() {
//		MjpegServer test = new MjpegServer("Cam", 0);
		cams = new VideoCapture[2];
		for(int i = 0 ; i < cams.length; i ++){
			cams[i] = new VideoCapture(i);
		}
		
//		VideoCapture cam = new VideoCapture(0);
//		Mat in = new Mat();
//		cam.read(in);
//		test.getSource().
//		cam.
//		test.setSource(cam);
//		test.setSource(source);
		new Thread(() -> {
//			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
//			camera.setResolution(640, 480);

//			CvSink cvSink = CameraServer.getInstance().getVideo();
			CvSource outputStream = CameraServer.getInstance().putVideo("Cam", 640, 480);

			Mat source = new Mat();
			Mat output = new Mat();
			XboxController xbox = Robot.oi.getXbox();
			while (!Thread.interrupted()) {
//				cvSink.grabFrame(source);
				
				System.out.println(!xbox.getAButton());
				if(!xbox.getAButton()){
					cams[0].read(source);
				}else{
					cams[1].read(source);
				}
//				Imgproc.
//				Imgproc.
//				source.
				Imgproc.cvtColor( source, output, Imgproc.COLOR_BGRA2BGR);
				Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
				outputStream.putFrame(output);
			}
		}).start();
	}

}
