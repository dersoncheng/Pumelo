package com.derson.pumelo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.derson.pumelo.location.LocationManager;
import com.derson.pumelo.util.ToastUtil;
import com.derson.pumelo.widget.ProgressWheel;

import org.w3c.dom.Text;

import java.io.Serializable;

public class MainActivity extends BaseActivty {

    private TextView textView;
    private ProgressWheel progressWheel;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(null != intent) {
                if(intent.getAction().equals(LocationManager.LOCATION_SUCCESS)) {
                    BDLocation location = (BDLocation)intent.getParcelableExtra("location");
                    ToastUtil.showInCenter("定位成功");
                    progressWheel.stopSpinning();
                    textView.setText(location.getAddrStr());
                } else {
                    ToastUtil.showInCenter("定位失败，请重试");
                }
            }
        }
    };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        LocationManager.getInstance().locate();
        textView = (TextView)findViewById(R.id.textview);
        progressWheel = (ProgressWheel)findViewById(R.id.progbar);
        progressWheel.spin();
        IntentFilter locationFilter = new IntentFilter();
        locationFilter.addAction(LocationManager.LOCATION_SUCCESS);
        locationFilter.addAction(LocationManager.LOCATION_FAIL);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, locationFilter);
	}
}
