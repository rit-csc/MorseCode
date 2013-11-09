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
	
	private final HashMap<String, Code[]> mappings = new HashMap<String, Code[]>(26);
	
	public boolean contains(String key) {
		return mappings.containsKey(key);
	}
	
	public Code[] get(String key) {
		return mappings.get(key);
	}
	
	public void put(String key, Code[] codes) {
		mappings.put(key, codes);
	}
	
	
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
			return reader.getIndex() != reader.getEndIndex();
		}
		
		@Override
		public Code[] next() {
			String key = "";
			Code[] code = new Code[0];
			
			//TODO this loop only matches the first Char.  Change it to keep looping through until it doesn't match, then backup and return the last matching key
			while(hasNext()) {
				if(mappings.containsKey(key)) {
					return mappings.get(key);
				}
				
				key += reader.next();
			}
			Log.e("code-mapping", "Left over characters without a code mapping: " + key);
			
			return code;
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException("Remove not supported");
		}
		
	}
	
}
