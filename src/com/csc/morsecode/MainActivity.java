package com.csc.morsecode;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.csc.morsecode.data.CodeMapping;
import com.csc.morsecode.handlers.ConsoleOutput;
import com.csc.morsecode.tests.Tests;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//TODO configure these based on (stored) user preferences
		Settings.setCodeMapping(new CodeMapping());
		Settings.addOutput(new ConsoleOutput());
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
	
}
