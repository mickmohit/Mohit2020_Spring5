package com.example.demo.entity;

public class TimeToRead {

	private int mins;
	private int seconds;
	public int getMins() {
		return mins;
	}
	public void setMins(int mins) {
		this.mins = mins;
	}
	public int getSeconds() {
		return seconds;
	}
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	@Override
	public String toString() {
		return "TimeToRead [mins=" + mins + ", seconds=" + seconds + "]";
	}
	
	
}
