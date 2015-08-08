package com.derson.pumelo.home;

import android.content.Context;
import com.baidu.location.BDLocation;
import com.derson.pumelo.mvp.BasePresenter;

/**
 * Created by chengli on 15/8/8.
 */
public class MainPresenter extends BasePresenter implements OnLocationListener{

    private MainView mainView;
    private MainInteractor mainInteractor;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
        this.mainInteractor = new MainInteractor();
    }


    public void initLoacation(Context context) {
        mainInteractor.locate(this);
    }

    public void quit() {
        mainInteractor.unlocate(this);
    }

    public void stopLocation() {
        mainInteractor.stoplocate();
    }

    @Override
    public void onLocateSuccess(BDLocation location) {
        mainView.showMessage("定位成功");
        mainView.removeLoading();
        mainView.displayAddress(location.getAddrStr());
        stopLocation();
    }

    @Override
    public void onLocateFail(int errorType) {
        mainView.removeLoading();
        mainView.showMessage("定位失败，请重试");
        stopLocation();
    }
}
