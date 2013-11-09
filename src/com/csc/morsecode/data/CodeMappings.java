package com.csc.morsecode.data;

import java.text.StringCharacterIterator;
import java.util.HashMap;
import java.util.Iterator;

import android.util.Log;


/**
 * @author dpk3062
 * Abstracts out how
 */
public class CodeMappings {
	
	/**
	 * Keys are lowercased by the helper methods.
	 */
	private final HashMap<String, Code[]> mappings = new HashMap<String, Code[]>(26);
	
	public CodeMappings() {
		loadDefaults();  //TODO load these from a file
	}
	
	//--------------------------------------------------------------------------
	
	
	public boolean contains(String key) {
		return mappings.containsKey(key.toLowerCase());
	}
	
	public Code[] get(String key) {
		return mappings.get(key.toLowerCase());
	}
	
	public void put(String key, Code[] codes) {
		mappings.put(key.toLowerCase(), codes);
	}
	
	
	//--------------------------------------------------------------------------
	
	public void loadDefaults() {
		//International Morse Code   //TODO what about the other versions?
		//http://en.wikipedia.org/wiki/File:International_Morse_Code.svg  //TODO the space between letters is three units
		mappings.clear();
		mappings.put("a", new Code[] {Code.dot, Code.unit, Code.dash});
		mappings.put("b", new Code[] {Code.dash, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dot});
		mappings.put("c", new Code[] {Code.dash, Code.unit, Code.dot, Code.unit, Code.dash, Code.unit, Code.dot});
		mappings.put("d", new Code[] {Code.dash, Code.unit, Code.dot, Code.unit, Code.dot});
		mappings.put("e", new Code[] {Code.dot});
		mappings.put("f", new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dash, Code.unit, Code.dot});
		mappings.put("g", new Code[] {Code.dash, Code.unit, Code.dash, Code.unit, Code.dot});
		mappings.put("h", new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dot});
		mappings.put("i", new Code[] {Code.dot, Code.unit, Code.dot});
		mappings.put("j", new Code[] {Code.dot, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dash});
		mappings.put("k", new Code[] {Code.dash, Code.unit, Code.dot, Code.unit, Code.dash});
		mappings.put("l", new Code[] {Code.dot, Code.unit, Code.dash, Code.unit, Code.dot, Code.unit, Code.dot});
		mappings.put("m", new Code[] {Code.dash, Code.unit, Code.dash});
		mappings.put("n", new Code[] {Code.dash, Code.unit, Code.dot});
		mappings.put("o", new Code[] {Code.dash, Code.unit, Code.dash, Code.unit, Code.dash});
		mappings.put("p", new Code[] {Code.dot, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dot});
		mappings.put("q", new Code[] {Code.dash, Code.unit, Code.dash, Code.unit, Code.dot, Code.unit, Code.dash});
		mappings.put("r", new Code[] {Code.dot, Code.unit, Code.dash, Code.unit, Code.dot});
		mappings.put("s", new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dot});
		mappings.put("t", new Code[] {Code.dash});
		mappings.put("u", new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dash});
		mappings.put("v", new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dash});
		mappings.put("w", new Code[] {Code.dot, Code.unit, Code.dash, Code.unit, Code.dash});
		mappings.put("x", new Code[] {Code.dash, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dash});
		mappings.put("y", new Code[] {Code.dash, Code.unit, Code.dot, Code.unit, Code.dash, Code.unit, Code.dash});
		mappings.put("z", new Code[] {Code.dash, Code.unit, Code.dash, Code.unit, Code.dot, Code.unit, Code.dot});
		
		mappings.put(" ", new Code[] {Code.unit, Code.unit, Code.unit, Code.unit, Code.unit, Code.unit, Code.unit});
		
		mappings.put("1", new Code[] {Code.dot, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dash});
		mappings.put("2", new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dash});
		mappings.put("3", new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dash, Code.unit, Code.dash});
		mappings.put("4", new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dash});
		mappings.put("5", new Code[] {Code.dot, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dot});
		mappings.put("6", new Code[] {Code.dash, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dot});
		mappings.put("7", new Code[] {Code.dash, Code.unit, Code.dash, Code.unit, Code.dot, Code.unit, Code.dot, Code.unit, Code.dot});
		mappings.put("8", new Code[] {Code.dash, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dot, Code.unit, Code.dot});
		mappings.put("9", new Code[] {Code.dash, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dot});
		mappings.put("0", new Code[] {Code.dash, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dash, Code.unit, Code.dash});
		
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
		
		return new CodeMappingsIterator(reader);
	}
	
	
	/**
	 * @author dpk3062
	 * Moves over the given reader returning Code mappings for the largest possible character sequence.
	 */
	private class CodeMappingsIterator implements Iterator<Code[]> {
		
		private final StringCharacterIterator reader;
		
		public CodeMappingsIterator(StringCharacterIterator reader) {
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
				
				if(mappings.containsKey(key)) {
					return mappings.get(key);
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
