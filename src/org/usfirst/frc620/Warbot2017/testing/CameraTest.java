package org.usfirst.frc620.Warbot2017.testing;

import org.opencv.videoio.VideoCapture;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;

public class CameraTest {
	private VideoCapture[] cams;

	public CameraTest() {
		// Mat backGround = new Mat(482,642, CvType.CV_8UC3, new Scalar(0));
		// //Create A black Image with 8 Color bits with 3 channels
		cams = new VideoCapture[2];
		// for (int i = 0; i < cams.length; i++) {
		// cams[i] = new VideoCapture(i);
		// // cams[i].set(Videoio.CAP_PROP_FRAME_WIDTH, 640);
		// // cams[i].set(Videoio.CAP_PROP_FRAME_HEIGHT, 480);
		// }

//		cams[0] = new VideoCapture(0);
//		cams[0].set(Videoio.CAP_PROP_FRAME_WIDTH, 160);
//		cams[0].set(Videoio.CAP_PROP_FRAME_HEIGHT, 120);
		MjpegServer tme = new MjpegServer("MjpegServer", 1181);
		UsbCamera temp = new UsbCamera("test", 0);
		tme.setSource(temp);
		

//		new Thread(() -> {
//			CvSource outputStream = CameraServer.getInstance().putVideo("Cam", 160, 120);
//
//			Mat source = new Mat();
//			Mat output = new Mat();
//			XboxController xbox = Robot.oi.getXbox();
//			while (!Thread.interrupted()) {
//				if (!xbox.getAButton()) {
//					cams[0].read(source);
//				} else {
//					// cams[1].read(source);
//				}
//				//// backGround.copyTo(output);
//				//// Imgproc.resize(source, source, new Size(620, 480));
//				//// source.copyTo(output.rowRange(1,481).colRange(1,621));
//				// System.out.println("Source Mat: Channels: " +
//				//// source.channels());
//				// System.out.println("Source Mat: Depth: " + source.depth());
//				// System.out.println("Source Mat: height: " + source.height());
//				// System.out.println("Source Mat: width: " + source.width());
//				// System.out.println("-----------------------------------");
//
//				outputStream.putFrame(source);
//				// try {
//				// Thread.sleep(200);
//				// } catch (InterruptedException e) {
//				// // TODO Auto-generated catch block
//				// e.printStackTrace();
//				// }
//			}
//		}).start();
	}

}
