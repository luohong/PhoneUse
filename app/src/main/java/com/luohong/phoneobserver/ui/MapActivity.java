package com.luohong.phoneobserver.ui;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;

/**
 * Created by luohong on 15/9/22.
 */
public class MapActivity extends Activity implements AMapLocationListener {

    private LocationManagerProxy mLocationManagerProxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 初始化定位
     */
    private void init() {
        mLocationManagerProxy = LocationManagerProxy.getInstance(this);
        //此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        //注意设置合适的定位时间的间隔，并且在合适时间调用removeUpdates()方法来取消定位请求
        //在定位结束后，在合适的生命周期调用destroy()方法     
        //其中如果间隔时间为-1，则定位只定一次
        mLocationManagerProxy.requestLocationData(
                LocationProviderProxy.AMapNetwork, 60*1000, 15, this);
        mLocationManagerProxy.setGpsEnable(false);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if(aMapLocation != null && aMapLocation.getAMapException().getErrorCode() == 0){
            //获取位置信息
            Double geoLat = aMapLocation.getLatitude();
            Double geoLng = aMapLocation.getLongitude();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLocation();
    }

    private void stopLocation() {
        if (mLocationManagerProxy != null) {
            mLocationManagerProxy.removeUpdates(this);
            mLocationManagerProxy.destory();
        }
        mLocationManagerProxy = null;
    }
}
