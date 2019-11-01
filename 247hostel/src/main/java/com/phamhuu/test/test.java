package com.phamhuu.test;

public class test {

	public static void main(String[] args) {
		
		double x1 = -1;
		double y1 = -1;
		double x2 = 10.895361;
		double y2 = 106.764907;
		
		double distance = getDistanceTransferMeter(x1, y1, x2, y2);
		
		System.out.println(distance);
		
	}
	
	public static double getDistanceTransferMeter(double x1, double y1, double x2, double y2) {

		double distance = -1;
		
		distance = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
		
		return distance * 100000;
	}
}
