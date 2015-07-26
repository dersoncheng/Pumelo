package com.derson.pumelo.util;


import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.derson.pumelo.R;

/**
 * 弹浮层工具类
 * 
 * @author Chengli
 *
 */
public class ToastUtil {

	public static Toast showInCenter(Context context, String message) {
		if (TextUtils.isEmpty(message)) {
			message = "";
		}
		Toast toast = new Toast(context);
		View view = LayoutInflater.from(context).inflate(R.layout.common_toast, null);
		TextView tv = (TextView) view.findViewById(R.id.toast_message);
		tv.setText(message);
		toast.setView(view);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
		return toast;
	}

	public static void showInBottom(Context context, String message) {
		if (TextUtils.isEmpty(message)) {
			return;
		}
		Toast toast = new Toast(context);
		View view = LayoutInflater.from(context).inflate(R.layout.common_toast, null);
		TextView tv = (TextView) view.findViewById(R.id.toast_message);
		tv.setText(message);
		toast.setView(view);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM, 0, 120);
		toast.show();
	}

}
