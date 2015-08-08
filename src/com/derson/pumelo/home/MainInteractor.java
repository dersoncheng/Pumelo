package com.derson.pumelo.home;

/**
 * Created by chengli on 15/8/8.
 */
public interface MainInteractor {

    /**
     * 定位当前位置
     */
    public void locate(OnLocationListener listener);

    public void unlocate(OnLocationListener listener);

    public void stoplocate();
}
