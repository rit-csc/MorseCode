package com.csc.morsecode.handlers;

import java.text.StringCharacterIterator;
import java.util.Iterator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.csc.morsecode.Settings;
import com.csc.morsecode.data.Code;
import com.csc.morsecode.data.CodeMapping;
import com.csc.morsecode.data.Encoding;


public class MessageReceiver extends BroadcastReceiver implements Input {
	
	private static final String TAG = "MessageActivity";
	
	public MessageReceiver() {
		Settings.addInput(this);  //TODO is a new object created each time we receive a message or is it only registered once?
		Log.i(TAG, "message receiver constructor");
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		//---get the SMS message passed in---
		Bundle bundle = intent.getExtras();        
		SmsMessage[] msgs = null;
		String str = "";            
		if (bundle != null)
		{
			//---retrieve the SMS message received---
			Object[] pdus = (Object[]) bundle.get("pdus");
			msgs = new SmsMessage[pdus.length];            
			for (int i=0; i<msgs.length; i++){
				msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);                
				//str += "sms from " + msgs[i].getOriginatingAddress();
				//str = str.trim();
				//str += " ";
				str += msgs[i].getMessageBody().toString();        
			}
			input(str);
		}
	}
	
	//--------------------------------------------------------------------------
	// Methods from Input
	//--------------------------------------------------------------------------
	
	public void input(String message) {
		if(message == null) {
			return;
		}
		
		Log.i(TAG, "Messag received:" + message);
		
		CodeMapping codeMapping = Settings.getCodeMapping();
		Iterator<Encoding> iter = codeMapping.iterator(new StringCharacterIterator(message));
		
		//output each sequence
		Encoding encoding;
		while(iter.hasNext()) {
			encoding = iter.next();
			
			//each sequence goes through all enabled output methods
			for(Output output: Settings.getOutputs()) {
				output.output(encoding);
			}
		}
	}
	
	public void input(Code[] encoding) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Can't receive morse code over SMS");
	}
	
}
