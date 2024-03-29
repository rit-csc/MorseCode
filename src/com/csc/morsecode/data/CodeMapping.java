package com.csc.morsecode.data;

import java.text.StringCharacterIterator;
import java.util.ArrayList;
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
	private final HashMap<String, Encoding> textToCode = new HashMap<String, Encoding>();
	private final HashMap<String, String> codeToText = new HashMap<String, String>();
	
	public CodeMapping() {
		loadDefaults();  //TODO load these from a file
	}
	
	//--------------------------------------------------------------------------
	
	private boolean contains(String key) {
		key = key.toLowerCase();
		return textToCode.get(key) != null;
	}
	
	private boolean contains(Encoding codes) {
		return codeToText.get(codes.toString()) != null;
	}
	
	private Encoding get(String key) {
		key = key.toLowerCase();
		return textToCode.get(key);
	}
	
	//TODO get the iterator working so we can turn this private again
	public String get(Encoding codes) {
		Log.e("CM", "Checking: " + codes + ", " + codeToText.get(codes.toString()));
		return codeToText.get(codes.toString());
	}
	
	public void putBoth(String key, Encoding codes) {
		key = key.toLowerCase();
		textToCode.put(key, codes);
		codeToText.put(codes.toString(), key);
	}
	
	//--------------------------------------------------------------------------
	
	public void loadDefaults() {
		//International Morse Code   //TODO what about the other versions?
		//http://en.wikipedia.org/wiki/File:International_Morse_Code.svg  //TODO the space between letters is three units
		if ( textToCode.size() > 0) {
			return;  //TODO we'll probably want to remove this or double check if we need it at some point
		}
		textToCode.clear();
		codeToText.clear();
		putBoth("a", new Encoding(new Code[] {Code.dot, Code.unit, Code.dash}));
		putBoth("b", new Encoding(new Code[] {Code.dash, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dot}));
		putBoth("c", new Encoding(new Code[] {Code.dash, Code.unit, Code.dot, Code.unit, Code.dash, Code.unit, Code.dot}));
		putBoth("d", new Encoding(new Code[] {Code.dash, Code.unit, Code.dot, Code.unit, Code.dot}));
		putBoth("e", new Encoding(new Code[] {Code.dot}));
		putBoth("f", new Encoding(new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dash, Code.unit, Code.dot}));
		putBoth("g", new Encoding(new Code[] {Code.dash, Code.unit, Code.dash, Code.unit, Code.dot}));
		putBoth("h", new Encoding(new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dot}));
		putBoth("i", new Encoding(new Code[] {Code.dot, Code.unit, Code.dot}));
		putBoth("j", new Encoding(new Code[] {Code.dot, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dash}));
		putBoth("k", new Encoding(new Code[] {Code.dash, Code.unit, Code.dot, Code.unit, Code.dash}));
		putBoth("l", new Encoding(new Code[] {Code.dot, Code.unit, Code.dash, Code.unit, Code.dot, Code.unit, Code.dot}));
		putBoth("m", new Encoding(new Code[] {Code.dash, Code.unit, Code.dash}));
		putBoth("n", new Encoding(new Code[] {Code.dash, Code.unit, Code.dot}));
		putBoth("o", new Encoding(new Code[] {Code.dash, Code.unit, Code.dash, Code.unit, Code.dash}));
		putBoth("p", new Encoding(new Code[] {Code.dot, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dot}));
		putBoth("q", new Encoding(new Code[] {Code.dash, Code.unit, Code.dash, Code.unit, Code.dot, Code.unit, Code.dash}));
		putBoth("r", new Encoding(new Code[] {Code.dot, Code.unit, Code.dash, Code.unit, Code.dot}));
		putBoth("s", new Encoding(new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dot}));
		putBoth("t", new Encoding(new Code[] {Code.dash}));
		putBoth("u", new Encoding(new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dash}));
		putBoth("v", new Encoding(new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dash}));
		putBoth("w", new Encoding(new Code[] {Code.dot, Code.unit, Code.dash, Code.unit, Code.dash}));
		putBoth("x", new Encoding(new Code[] {Code.dash, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dash}));
		putBoth("y", new Encoding(new Code[] {Code.dash, Code.unit, Code.dot, Code.unit, Code.dash, Code.unit, Code.dash}));
		putBoth("z", new Encoding(new Code[] {Code.dash, Code.unit, Code.dash, Code.unit, Code.dot, Code.unit, Code.dot}));
		
		putBoth(" ", new Encoding(new Code[] {Code.unit, Code.unit, Code.unit, Code.unit, Code.unit, Code.unit, Code.unit}));
		
		putBoth("1", new Encoding(new Code[] {Code.dot, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dash}));
		putBoth("2", new Encoding(new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dash}));
		putBoth("3", new Encoding(new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dash, Code.unit, Code.dash}));
		putBoth("4", new Encoding(new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dash}));
		putBoth("5", new Encoding(new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dot}));
		putBoth("6", new Encoding(new Code[] {Code.dash, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dot}));
		putBoth("7", new Encoding(new Code[] {Code.dash, Code.unit, Code.dash, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dot}));
		putBoth("8", new Encoding(new Code[] {Code.dash, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dot, Code.unit, Code.dot}));
		putBoth("9", new Encoding(new Code[] {Code.dash, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dot}));
		putBoth("0", new Encoding(new Code[] {Code.dash, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dash}));
		//TODO what about whitespace and other characters?
	}
	
	//--------------------------------------------------------------------------	
	
	/**
	 * Creates a new iterator that returns Code arrays indicating the next largest matching sequence (a Code array could match a string longer than one character).
	 * @param reader
	 * @return
	 * @throws IllegalArgumentException If the giver reader is null
	 */
	public Iterator<Encoding> sToEIterator(StringCharacterIterator reader) throws IllegalArgumentException {
		if(reader == null) {
			throw new IllegalArgumentException("The character iterator cannot be null");
		}
		
		return new TextToCodeIterator(reader);
	}
	
	
	/**
	 * @author dpk3062
	 * Moves over the given reader returning Code textToCode for the largest possible character sequence.
	 */
	private class TextToCodeIterator implements Iterator<Encoding> {
		
		private final StringCharacterIterator reader;
		
		public TextToCodeIterator(StringCharacterIterator reader) {
			this.reader = reader;
		}
		
		@Override
		public boolean hasNext() {
			return reader.getIndex() < reader.getEndIndex();
		}
		
		@Override
		public Encoding next() {
			String key = "";
			Code[] code = new Code[0];
			
			//TODO this loop only matches the first Char.  Change it to keep looping through until it doesn't match, then backup and return the last matching key
			while(hasNext()) {
				key += reader.current();
				reader.next();
				
				if( contains(key) ) {
					return get(key);
				}
			}
			Log.w("code-mapping", "Left over characters without a code mapping: " + key);
			
			return new Encoding(code);
		}
		
		@Override
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Remove not supported");
		}
	}
	
	//--------------------------------------------------------------------------
	
	public Iterator<String> eToSIterator(Encoding encoding) throws IllegalArgumentException {
		if(encoding == null) {
			throw new IllegalArgumentException("The encoding iterator cannot be null");
		}
		
		return new CodeToTextIterator(encoding);
	}
	
	//TODO this needs testing
	/**
	 * @author dpk3062
	 *
	 */
	private class CodeToTextIterator implements Iterator<String> {
		
		private final Encoding encoding;
		private int index = 0;
		
		public CodeToTextIterator(Encoding encoding) {
			this.encoding = encoding;
		}
		
		public boolean hasNext() {
			return index < encoding.get.length;
		}
		
		public String next() {
			String value = "";
			
			//TODO this loop only matches the first encoding.  Change it to keep looping through until it matches the longest matchable encoding.
			ArrayList<Code> codes = new ArrayList<Code>();
			Encoding e;
			while(hasNext()) {
				codes.add(encoding.get[index]);
				++index;
				
				e = new Encoding(codes.toArray(new Code[0]));
				if(contains(e)) {
					return get(e);
				}
			}
			
			return value;
		}
		
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Remove not supported");
		}
	}
	
}
