package com.csc.morsecode.data;


/**
 * @author dpk3062
 * Used to wrap Code[] so we can nicely print them out
 */
public class Encoding {
	
	public final Code[] get;
	
	public Encoding(Code[] encoding) {
		this.get = encoding;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(get.length);
		sb.append("[Encoding:");
		for(Code c: get) {
			sb.append(c.text);
		}
		sb.append("]");
		return sb.toString();
	}
	
}
