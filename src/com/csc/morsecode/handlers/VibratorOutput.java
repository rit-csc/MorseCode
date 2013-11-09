package com.csc.morsecode.handlers;

import android.content.Context;
import android.os.Vibrator;

import com.csc.morsecode.MainActivity;
import com.csc.morsecode.Settings;
import com.csc.morsecode.data.Code;
import com.csc.morsecode.data.Encoding;

public class VibratorOutput implements Output {
	
	@Override
	public void output(Encoding encoding) {
		if ( MainActivity.globalContext == null ) {
			return;
		}
		long[] pattern = new long[ encoding.get.length + 1 ];
		pattern[0] = 0;
		int patternIndex = 1;
		for ( Code code : encoding.get ) {
			pattern[patternIndex] = (long) ( code.timeLength * Settings.getTimeScale() );
			patternIndex++;
		}
		Vibrator v = (Vibrator) MainActivity.globalContext.getSystemService( Context.VIBRATOR_SERVICE );
		v.vibrate(pattern, -1);
	}
	
	@Override
	public void output(String text) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Will not vibrate text at this time");
	}
	
}
