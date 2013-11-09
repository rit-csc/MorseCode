package com.csc.morsecode.tests;

import java.text.StringCharacterIterator;
import java.util.Iterator;

import com.csc.morsecode.data.Code;
import com.csc.morsecode.data.CodeMapping;
import com.csc.morsecode.data.Encoding;


public class Tests {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting tests...");
		
		
		// ----- Test CodeMapping ----- //
		CodeMapping cm = new CodeMapping();
		cm.putBoth("a", new Encoding(new Code[] {Code.dot, Code.dash}));
		cm.putBoth("b", new Encoding(new Code[] {Code.dot, Code.dot, Code.dash}));
		
		StringCharacterIterator sci = new StringCharacterIterator("abcdes");
		Iterator<Encoding> iter = cm.iterator(sci);
		int index = 0;
		while(iter.hasNext()) {
			Encoding next = iter.next();
			System.out.println("Code match " + index + ": " + next);
			++index;
		}
		
		
		// ----- Test ... ----- //
		
	}
	
}
