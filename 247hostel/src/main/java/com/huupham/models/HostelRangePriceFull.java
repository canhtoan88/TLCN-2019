package com.huupham.models;

public class HostelRangePriceFull {

	private String label;
	private int count;
	private double percent;

	public HostelRangePriceFull() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HostelRangePriceFull(String label, int count, double percent) {
		super();
		this.label = label;
		this.count = count;
		this.percent = percent;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

}
