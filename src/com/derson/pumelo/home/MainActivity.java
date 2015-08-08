package com.derson.pumelo.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.derson.pumelo.BaseActivty;
import com.derson.pumelo.R;
import com.derson.pumelo.location.LocationManager;
import com.derson.pumelo.util.ToastUtil;
import com.derson.pumelo.widget.ProgressWheel;

public class MainActivity extends BaseActivty implements MainView{

    private TextView textView;
    private ProgressWheel progressWheel;
    private MainPresenter mainPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenterImpl(this);
        textView = (TextView)findViewById(R.id.textview);
        progressWheel = (ProgressWheel)findViewById(R.id.progbar);
        showLoading();
        mainPresenter.initLoacation(this);
	}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.quit();
    }

    @Override
    public void showLoading() {
        progressWheel.spin();
    }

    @Override
    public void removeLoading() {
        progressWheel.stopSpinning();
    }

    @Override
    public void showMessage(String charSequence) {
        ToastUtil.showInCenter(charSequence);
    }

    @Override
    public void displayAddress(String address) {
        textView.setText(address);
    }
}
