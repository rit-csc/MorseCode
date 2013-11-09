package com.csc.morsecode.handlers;

import java.text.StringCharacterIterator;
import java.util.Arrays;
import java.util.Iterator;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;

import com.csc.morsecode.MainActivity;
import com.csc.morsecode.Settings;
import com.csc.morsecode.data.Code;

public class VibratorOutput implements Output {

	@Override
	public void output(Code[] encoding) {
		if ( MainActivity.globalContext == null ) {
			return;
		}
		long[] pattern = new long[ encoding.length + 1 ];
		pattern[0] = 0;
		int patternIndex = 1;
		for ( Code code : encoding ) {
			pattern[patternIndex] = (long) ( code.timeLength * Settings.getTimeScale() );
			patternIndex++;
		}
		Vibrator v = (Vibrator) MainActivity.globalContext.getSystemService( Context.VIBRATOR_SERVICE );
		v.vibrate(pattern, -1);
	}

	@Override
	public void output(String text) {
		// TODO Auto-generated method stub
		
	}

}
