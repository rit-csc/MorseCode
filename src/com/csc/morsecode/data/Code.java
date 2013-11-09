package com.csc.morsecode.data;


public enum Code {
	EndMsg("Q", 100),
	EndChar("X", 100),
	EndWord("W", 100),
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
