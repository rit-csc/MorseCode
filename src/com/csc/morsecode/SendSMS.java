package com.csc.morsecode;

import android.app.Activity;
import android.app.AlertDialog;
import android.telephony.SmsManager;

public class SendSMS extends Activity {

	
	public void sendSMS(String phoneNumber, String message) {
		//String phoneNumber = "585-739-1852";
		try {
			SmsManager.getDefault().sendTextMessage(phoneNumber, null, message, null, null);
		}
		catch (Exception e) {
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
			AlertDialog dialog = alertDialogBuilder.create();


					dialog.setMessage(e.getMessage());


					dialog.show();
		}
	}
}
