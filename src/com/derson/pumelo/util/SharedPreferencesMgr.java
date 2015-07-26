package com.derson.pumelo.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences管理类
 * */
public class SharedPreferencesMgr {

	private static SharedPreferences sPrefs;

	public static void init(Context context,String fileName) {
		if (sPrefs == null) {
			sPrefs = context.getSharedPreferences(fileName, Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
		}
	}
	
	public static int getInt(String key,int defaultValue) {
		return sPrefs.getInt(key, defaultValue);
	}

	public static void setInt(String key,int value) {
		sPrefs.edit().putInt(key, value).commit();
	}

	public static boolean getBoolean(String key,boolean defaultValue) {
		return sPrefs.getBoolean(key, defaultValue);
	}

	public static void setBoolean(String key,boolean value) {
		sPrefs.edit().putBoolean(key, value).commit();
	}
	
	public static String getString(String key,String defaultValue) {
		return sPrefs.getString(key, defaultValue);
	}

	public static void setString(String key,String value) {
		sPrefs.edit().putString(key, value).commit();
	}
	
	public static void clearAll(){
		sPrefs.edit().clear().commit();
	}
}
