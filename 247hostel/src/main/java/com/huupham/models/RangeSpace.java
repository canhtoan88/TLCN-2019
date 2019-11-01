package com.huupham.models;

public class RangeSpace {

	private int minSpace;
	private int maxSpace;

	public RangeSpace() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RangeSpace(int minSpace, int maxSpace) {
		super();
		this.minSpace = minSpace;
		this.maxSpace = maxSpace;
	}

	public int getMinSpace() {
		return minSpace;
	}

	public void setMinSpace(int minSpace) {
		this.minSpace = minSpace;
	}

	public int getMaxSpace() {
		return maxSpace;
	}

	public void setMaxSpace(int maxSpace) {
		this.maxSpace = maxSpace;
	}

}
