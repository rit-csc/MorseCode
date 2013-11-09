package com.csc.morsecode.handlers;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.telephony.SmsManager;
import android.util.Log;

import com.csc.morsecode.Settings;
import com.csc.morsecode.data.Code;
import com.csc.morsecode.data.CodeMapping;
import com.csc.morsecode.data.Encoding;


public class TapInput extends Activity implements Input {
	
	
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
				Encoding e = new Encoding(codes.toArray(new Code[0]));
				String ch = codeMapping.get(e);
				if(ch != null) {
					message += ch;
				} else {
					Log.e("INPUT", "Losing codes: " + e);
				}
				
				codes.clear();
				Log.d("INPUT", "Decoded message: [" + message + "]");
				
				sendSMS(Settings.getOutgoingPhoneNum(), message);
				
				return;
			} else if(c == Code.EndWord) {
				Encoding e = new Encoding(codes.toArray(new Code[0]));
				String ch = codeMapping.get(e);
				if(ch != null) {
					message += ch;
				} else {
					Log.e("INPUT", "Losing codes: " + e);
				}
				
				codes.clear();				
				message += " ";
			} else {
				Log.d("INPUT", "Decoded code: " + c.text);
				codes.add(c);
			}
		}
	}
	
	//--------------------------------------------------------------------------
	
	public void sendSMS(String phoneNumber, String message) {
		try {
			SmsManager.getDefault().sendTextMessage(phoneNumber, null, message, null, null);
		}
		catch (Exception e) {
			try {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
				AlertDialog dialog = alertDialogBuilder.create();
				
				dialog.setMessage(e.getMessage());
				dialog.show();
			} catch(Exception e1) {
				Log.e("SENDING_SMS", "Failed while failing", e1);
			}
		}
	}
}
