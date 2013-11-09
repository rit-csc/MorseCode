package com.csc.morsecode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;


public class MessageReceiver extends BroadcastReceiver {
	
	private static final String TAG = "MessageActivity";

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
                str += "SMS from " + msgs[i].getOriginatingAddress();                     
                str += " :";
                str += msgs[i].getMessageBody().toString();
                str += "\n";        
            }
            //---display the new SMS message---
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }                         
		
		/*
		Bundle pudsBundle = intent.getExtras();
		Object[] pdus = (Object[]) pudsBundle.get("puds");
		SmsMessage[] msgs = new SmsMessage[pdus.length];
		String smsSender = "";
		String smsBody = "";
		long timestamp = 0L;
		for (int i=0; i<msgs.length; i++) {
			msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
			smsSender += msgs[i].getOriginatingAddress();
			smsBody += msgs[i].getMessageBody().toString();
			timestamp += msgs[i].getTimestampMillis();
		}
		
		PopMessage pop_msg = new PopMessage();
		pop_msg.body = smsBody;
		pop_msg.sender = smsSender;
		pop_msg.timestamp = timestamp;
		
		intent.setClass(context, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		intent.putExtra("msg", pop_msg);
		
		context.startActivity(intent);
		*/
		
	}

}
