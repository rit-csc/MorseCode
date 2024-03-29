package com.csc.morsecode;

import java.util.HashSet;
import java.util.Set;

import android.util.Log;

import com.csc.morsecode.data.CodeMapping;
import com.csc.morsecode.handlers.Input;
import com.csc.morsecode.handlers.Output;


public class Settings {
	
	
	private static CodeMapping codeMapping = null;
	private static HashSet<Input> inputs = new HashSet<Input>();
	private static HashSet<Output> outputs = new HashSet<Output>();
	
	public static String getOutgoingPhoneNum() {
		return "585-739-1852";
	}
	
	//--------------------------------------------------------------------------
	
	public static CodeMapping getCodeMapping() {
		return codeMapping;
	}
	
	public static Set<Input> getInputs() {
		return inputs;
	}
	
	public static Set<Output> getOutputs() {
		return outputs;
	}
	
	public static double getTimeScale() {
		return 5.0;
	}
	//--------------------------------------------------------------------------
	
	public static boolean setCodeMapping(CodeMapping mapping) {
		if(mapping == null) {
			return false;
		}
		
		Log.d("Settings", "Setting code mapping: " + mapping);
		codeMapping = mapping;
		return true;
	}
	
	public static boolean addInput(Input in) {
		if(in == null) {
			return false;
		}
		
		Log.d("Settings", "Adding input: " + in);
		return inputs.add(in);
	}
	
	public static boolean addOutput(Output out) {
		if(out == null) {
			return false;
		}
		
		Log.d("Settings", "Adding output: " + out);
		return outputs.add(out);
	}
}
