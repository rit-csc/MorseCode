package com.csc.morsecode.data;


public enum Code {
	dot(".", 200),
	dash("-", 600),
	unit(" ", 200); //length of time and a pause
	
	public final int timeLength;
	public final String text;
	
	Code(String text, int timeLength) {
		this.text = text;
		this.timeLength = timeLength;
	}
	
}
