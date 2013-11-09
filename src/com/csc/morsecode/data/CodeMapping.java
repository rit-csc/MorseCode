package com.csc.morsecode.data;

import java.text.StringCharacterIterator;
import java.util.HashMap;
import java.util.Iterator;

import android.util.Log;


/**
 * @author dpk3062
 * Abstracts out how we transcribe text to morse code and morse code to text.
 */
public class CodeMapping {
	
	/**
	 * Keys are lowercased by the helper methods.
	 */
	private final HashMap<String, Code[]> textToCode = new HashMap<String, Code[]>(37);
	private final HashMap<Code[], String> codeToText = new HashMap<Code[], String>(37);
	
	public CodeMapping() {
		loadDefaults();  //TODO load these from a file
	}
	
	//--------------------------------------------------------------------------
	
	private boolean contains(String key) {
		key = key.toLowerCase();
		return textToCode.containsKey(key);
	}
	
	private boolean contains(Code[] codes) {
		return codeToText.containsKey(codes);
	}
	
	private Code[] get(String key) {
		key = key.toLowerCase();
		return textToCode.get(key);
	}
	
	private String get(Code[] codes) {
		return codeToText.get(codes);
	}
	
	public void putBoth(String key, Code[] codes) {
		key = key.toLowerCase();
		textToCode.put(key, codes);
		codeToText.put(codes, key);
	}
	
	//--------------------------------------------------------------------------
	
	public void loadDefaults() {
		//International Morse Code   //TODO what about the other versions?
		//http://en.wikipedia.org/wiki/File:International_Morse_Code.svg  //TODO the space between letters is three units
		textToCode.clear();
		codeToText.clear();
		putBoth("a", new Code[] {Code.dot, Code.unit, Code.dash});
		putBoth("b", new Code[] {Code.dash, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dot});
		putBoth("c", new Code[] {Code.dash, Code.unit, Code.dot, Code.unit, Code.dash, Code.unit, Code.dot});
		putBoth("d", new Code[] {Code.dash, Code.unit, Code.dot, Code.unit, Code.dot});
		putBoth("e", new Code[] {Code.dot});
		putBoth("f", new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dash, Code.unit, Code.dot});
		putBoth("g", new Code[] {Code.dash, Code.unit, Code.dash, Code.unit, Code.dot});
		putBoth("h", new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dot});
		putBoth("i", new Code[] {Code.dot, Code.unit, Code.dot});
		putBoth("j", new Code[] {Code.dot, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dash});
		putBoth("k", new Code[] {Code.dash, Code.unit, Code.dot, Code.unit, Code.dash});
		putBoth("l", new Code[] {Code.dot, Code.unit, Code.dash, Code.unit, Code.dot, Code.unit, Code.dot});
		putBoth("m", new Code[] {Code.dash, Code.unit, Code.dash});
		putBoth("n", new Code[] {Code.dash, Code.unit, Code.dot});
		putBoth("o", new Code[] {Code.dash, Code.unit, Code.dash, Code.unit, Code.dash});
		putBoth("p", new Code[] {Code.dot, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dot});
		putBoth("q", new Code[] {Code.dash, Code.unit, Code.dash, Code.unit, Code.dot, Code.unit, Code.dash});
		putBoth("r", new Code[] {Code.dot, Code.unit, Code.dash, Code.unit, Code.dot});
		putBoth("s", new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dot});
		putBoth("t", new Code[] {Code.dash});
		putBoth("u", new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dash});
		putBoth("v", new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dash});
		putBoth("w", new Code[] {Code.dot, Code.unit, Code.dash, Code.unit, Code.dash});
		putBoth("x", new Code[] {Code.dash, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dash});
		putBoth("y", new Code[] {Code.dash, Code.unit, Code.dot, Code.unit, Code.dash, Code.unit, Code.dash});
		putBoth("z", new Code[] {Code.dash, Code.unit, Code.dash, Code.unit, Code.dot, Code.unit, Code.dot});
		
		putBoth(" ", new Code[] {Code.unit, Code.unit, Code.unit, Code.unit, Code.unit, Code.unit, Code.unit});
		
		putBoth("1", new Code[] {Code.dot, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dash});
		putBoth("2", new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dash});
		putBoth("3", new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dash, Code.unit, Code.dash});
		putBoth("4", new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dash});
		putBoth("5", new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dot});
		putBoth("6", new Code[] {Code.dash, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dot});
		putBoth("7", new Code[] {Code.dash, Code.unit, Code.dash, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dot});
		putBoth("8", new Code[] {Code.dash, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dot, Code.unit, Code.dot});
		putBoth("9", new Code[] {Code.dash, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dot});
		putBoth("0", new Code[] {Code.dash, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dash});
		
		//TODO what about whitespace and other characters?
	}
	
	//--------------------------------------------------------------------------
	
	
	/**
	 * Creates a new iterator that returns Code arrays indicating the next largest matching sequence (a Code array could match a string longer than one character).
	 * @param reader
	 * @return
	 * @throws IllegalArgumentException If the giver reader is null
	 */
	public Iterator<Code[]> iterator(StringCharacterIterator reader) throws IllegalArgumentException {
		if(reader == null) {
			throw new IllegalArgumentException("The character iterator cannot be null");
		}
		
		return new TextToCodeIterator(reader);
	}
	
	
	/**
	 * @author dpk3062
	 * Moves over the given reader returning Code textToCode for the largest possible character sequence.
	 */
	private class TextToCodeIterator implements Iterator<Code[]> {
		
		private final StringCharacterIterator reader;
		
		public TextToCodeIterator(StringCharacterIterator reader) {
			this.reader = reader;
		}
		
		@Override
		public boolean hasNext() {
			return reader.getIndex() < reader.getEndIndex() - 1;
		}
		
		@Override
		public Code[] next() {
			String key = "";
			Code[] code = new Code[0];
			
			//TODO this loop only matches the first Char.  Change it to keep looping through until it doesn't match, then backup and return the last matching key
			while(hasNext()) {
				key += reader.current();
				reader.next();
				
				if(textToCode.containsKey(key)) {
					return textToCode.get(key);
				}
			}
			Log.w("code-mapping", "Left over characters without a code mapping: " + key);
			
			return code;
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException("Remove not supported");
		}
		
	}
	
}
