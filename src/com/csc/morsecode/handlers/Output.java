package com.csc.morsecode.handlers;

import com.csc.morsecode.data.Code;


public interface Output {
	
	public void output(Code[] encoding);
	
	public void output(String text);
	
}
