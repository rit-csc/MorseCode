package com.csc.morsecode.handlers;

import com.csc.morsecode.data.Encoding;



public interface Output {
	
	public void output(Encoding encoding);
	
	public void output(String text);
	
}
