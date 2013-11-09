package com.csc.morsecode.handlers;

import java.util.ArrayList;

import android.util.Log;

import com.csc.morsecode.Settings;
import com.csc.morsecode.data.Code;
import com.csc.morsecode.data.CodeMapping;
import com.csc.morsecode.data.Encoding;


public class TapInput implements Input {
	
	//--------------------------------------------------------------------------
	// Methods from Input
	//--------------------------------------------------------------------------
	
	public void input(String message) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("No Text Input");
	}
	
	public void input(Code[] encoding) throws UnsupportedOperationException {
		if(encoding == null) {
			return;
		}
		
		CodeMapping codeMapping = Settings.getCodeMapping();
		ArrayList<Code> codes = new ArrayList<Code>();
		String message = "";		
		
		for(Code c: encoding) {
			
			//end of a character, so get which character
			if(c == Code.EndChar) {
				Encoding e = new Encoding(codes.toArray(new Code[0]));
				String ch = codeMapping.get(e);
				if(ch != null) {
					message += ch;
				} else {
					Log.e("INPUT", "Losing codes: " + e);
				}
				
				codes.clear();
			} else if(c == Code.EndMsg) {
				Log.d("INPUT", "Decoded message: " + message);
				//TODO send message
				//TODO send message
				//TODO send message
				return;
			} else {
				Log.d("INPUT", "Decoded code: " + c.text);
				codes.add(c);
			}
		}
	}
	
}
