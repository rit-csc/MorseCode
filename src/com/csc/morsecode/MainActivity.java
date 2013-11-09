package com.csc.morsecode;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.csc.morsecode.data.CodeMapping;
import com.csc.morsecode.handlers.ConsoleOutput;
import com.csc.morsecode.handlers.VibratorOutput;

public class MainActivity extends Activity {
	
	public static Context globalContext = null;
	
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
		Settings.addOutput( new VibratorOutput() );
		//Settings.addInput(new MessageReceiver());
		
		//run some unit tests
		//Tests.main(null);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void dotOnClick(View view) {
		//
	}
	
	public void dashOnClick(View view) {
		//
	}
}
