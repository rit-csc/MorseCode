package com.csc.morsecode.tests;

import java.text.StringCharacterIterator;
import java.util.Iterator;

import android.util.Log;

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
		Iterator<Encoding> iter = cm.sToEIterator(sci);
		int index = 0;
		while(iter.hasNext()) {
			Encoding next = iter.next();
			Log.d("TEST", "Code match " + index + ": " + next);
			++index;
		}
		
		//TODO this isn't going to work because the shortest code will just be a dot or a dash and longer codes could contain multiple other characters
		Encoding encoding = new Encoding(new Code[] {
				Code.dot, Code.unit, Code.dash,                                             //a
				Code.dash, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dot,   //b
				Code.dash, Code.unit, Code.dot, Code.unit, Code.dash, Code.unit, Code.dot,  //c
		});
		Iterator<String> sIter = cm.eToSIterator(encoding);
		index = 0;
		while(sIter.hasNext()) {
			String next = sIter.next();
			Log.d("TEST", "String match " + index + ": " + next);
			++index;
		}
		
		// ----- Test ... ----- //
		
	}
	
}
