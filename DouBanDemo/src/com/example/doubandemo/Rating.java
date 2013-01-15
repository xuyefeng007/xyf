package com.example.doubandemo;

public class Rating{
	private String max;
	private String numRaters;
	private String average;
	private String min;

	public Rating(String max, String numRaters, String average, String min) {
		this.max = max;
		this.numRaters = numRaters;
		this.average = average;
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getNumRaters() {
		return numRaters;
	}

	public void setNumRaters(String numRaters) {
		this.numRaters = numRaters;
	}

	public String getAverage() {
		return average;
	}

	public void setAverage(String average) {
		this.average = average;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}
}