package com.csc.morsecode.handlers;

import com.csc.morsecode.data.Code;


public class ConsoleOutput implements Output {
	
	private int eCount = 0;
	private int tCount = 0;
	
	@Override
	public void output(Code[] encoding) {
		++eCount;
		System.out.println("Encoding " + eCount + ": " + encoding);
	}
	
	@Override
	public void output(String text) {
		++tCount;
		System.out.println("String " + tCount + ": " + text);
	}
	
}
