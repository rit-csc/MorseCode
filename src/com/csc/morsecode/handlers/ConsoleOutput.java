package com.csc.morsecode.handlers;

import android.util.Log;

import com.csc.morsecode.data.Encoding;


public class ConsoleOutput implements Output {
	
	private int eCount = 0;
	private int tCount = 0;
	
	@Override
	public void output(Encoding encoding) {
		++eCount;
		Log.i("ConsoleOutput:", "Encoding " + eCount + ": " + encoding);
	}
	
	@Override
	public void output(String text) {
		++tCount;
		Log.i("ConsoleOutput:", "String " + tCount + ": " + text);
	}
	
}
