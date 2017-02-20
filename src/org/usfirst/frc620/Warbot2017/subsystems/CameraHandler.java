package org.usfirst.frc620.Warbot2017.subsystems;

import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CameraHandler {
	public final int NUMBER_OF_CAMERAS;
	private UsbCamera[] cameras;
	private CvSink[] cvs;
	private CvSource outputStream;
	private Mat frame;
	private int camera = 0;
	
	public CameraHandler(int numberOfCameras) {
		NUMBER_OF_CAMERAS = numberOfCameras;
		if (NUMBER_OF_CAMERAS > 0) {
			cameras = new UsbCamera[NUMBER_OF_CAMERAS];
			cvs = new CvSink[NUMBER_OF_CAMERAS];
			int backTake = 0;
			for (int i = 0; i < NUMBER_OF_CAMERAS; i++) {
				cameras[i - backTake] = CameraServer.getInstance().startAutomaticCapture();
				// if(cameras[i - backTake].isConnected())
				// {
				// backTake ++;
				// NUMBER_OF_CAMERAS --;
				// }
				CameraServer.getInstance().removeServer("USB Camera " + i);
				cameras[i - backTake].setResolution(320, 240);
				cvs[i - backTake] = CameraServer.getInstance().getVideo(cameras[i]);
				cvs[i - backTake].setEnabled(false);
			}
			outputStream = CameraServer.getInstance().putVideo("Camera Stream", 320, 240);
			frame = new Mat();

			new Thread(() -> {
				while (!Thread.interrupted()) {
					cvs[camera].grabFrame(frame);
					outputStream.putFrame(frame);
				}
			}).start();
		}
	}
	
	public int getCurrentCam() {
		return camera;
	}

	public void nextCamera() {
		switchToCamera((camera + 1) % NUMBER_OF_CAMERAS);
	}

	public void lastCamera() {
		switchToCamera((camera + NUMBER_OF_CAMERAS - 1) % NUMBER_OF_CAMERAS);
	}

	public void switchToCamera(int cam) {
		camera = cam;
	}

	public void darkenCamera(int cam) {
		UsbCamera camera = cameras[cam];
		camera.setWhiteBalanceManual(0);
		camera.setExposureManual(0);
	}

	public void brightenCamera(int cam) {
		UsbCamera camera = cameras[cam];
		camera.setWhiteBalanceAuto();
		camera.setExposureAuto();
	}
}