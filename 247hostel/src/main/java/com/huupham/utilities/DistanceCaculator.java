package com.huupham.utilities;

public class DistanceCaculator {
	
	public static double getDistanceTransferMeter(double x1, double y1, double x2, double y2) {

		double distance = -1;
		
		distance = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)) * 100000;
		
		return distance;
	}

}
