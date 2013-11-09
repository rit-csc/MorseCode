package com.csc.morsecode;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.csc.morsecode.data.Code;
import com.csc.morsecode.data.CodeMapping;
import com.csc.morsecode.data.Encoding;
import com.csc.morsecode.handlers.ConsoleOutput;
import com.csc.morsecode.handlers.TapInput;
import com.csc.morsecode.handlers.VibratorOutput;
import com.csc.morsecode.tests.Tests;

public class MainActivity extends Activity {
	
	public static Context globalContext = null;
	
	private ArrayList<Code> inputtingCodes = new ArrayList<Code>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		if ( globalContext == null ) {
			globalContext = getApplication().getApplicationContext();
		}
		
		//TODO configure these based on (stored) user preferences
		Settings.setCodeMapping(new CodeMapping());
		Settings.addOutput(new ConsoleOutput());
		Settings.addOutput(new VibratorOutput());
		Settings.addInput(new TapInput());
		//Settings.addInput(new MessageReceiver());
		
		//run some unit tests
		Tests.main(null);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//--------------------------------------------------------------------------
	
	public void dotOnClick(View view) {
		dot();
	}
	
	public void dashOnClick(View view) {
		dash();
	}
	
	public void charEndClicked(View view) {
		endChar();
	}
	
	public void wordEndClicked(View view) {
		wordEnded();
	}
	
	public void sendClicked(View view) {
		endMsg();
	}
	
	//--------------------------------------------------------------------------
	
	boolean skip = false;
	
	public void dot() {
		if(inputtingCodes.size() > 0 && !skip) {
			inputtingCodes.add(Code.unit);
		}
		inputtingCodes.add(Code.dot);
		skip = false;
	}
	
	public void dash() {
		if(inputtingCodes.size() > 0 && !skip) {
			inputtingCodes.add(Code.unit);
		}
		inputtingCodes.add(Code.dash);
		skip = false;
	}
	
	public void endChar() {
		inputtingCodes.add(Code.EndChar);
		skip = true;
	}
	
	public void wordEnded() {
		inputtingCodes.add(Code.EndWord);
		skip = true;
	}
	
	public void endMsg() {
		inputtingCodes.add(Code.EndMsg);
		
		//send message to all input handlers
		Code[] codes = inputtingCodes.toArray(new Code[0]);
		this.input(codes);
		//for(Input in: Settings.getInputs()) {
		//	in.input(codes);
		//}
		
		//clear for next input message
		inputtingCodes.clear();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
