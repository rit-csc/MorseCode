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
	
	public boolean equals(Encoding e) {
		if(e == null) {
			return false;
		}
		
		return this.toString().equals(e.toString());
	}
	
	@Override
	public int hashCode() {
		return this.toString().hashCode();
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
