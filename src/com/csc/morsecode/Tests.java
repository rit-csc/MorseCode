package com.csc.morsecode;

import java.text.StringCharacterIterator;
import java.util.Arrays;
import java.util.Iterator;

import com.csc.morsecode.data.Code;
import com.csc.morsecode.data.CodeMappings;


public class Tests {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting tests...");
		
		
		// ----- Test CodeMappings ----- //
		CodeMappings cm = new CodeMappings();
		cm.put("a", new Code[] {Code.dot, Code.dash});
		cm.put("b", new Code[] {Code.dot, Code.dot, Code.dash});
		
		StringCharacterIterator sci = new StringCharacterIterator("abcdes");
		Iterator<Code[]> iter = cm.iterator(sci);
		int index = 0;
		while(iter.hasNext()) {
			Code[] next = iter.next();
			System.out.println("Code match " + index + ": " + Arrays.toString(next));
			++index;
		}
		
		
		// ----- Test ... ----- //
		
	}
	
}
