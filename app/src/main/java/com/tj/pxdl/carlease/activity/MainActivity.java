package com.tj.pxdl.carlease.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.tj.pxdl.carlease.R;
import com.tj.pxdl.carlease.base.BaseActivity;
import com.tj.pxdl.carlease.utils.PermissionUtil;
import com.tj.pxdl.carlease.widget.scrollmenu.ScrollLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 主页地图
 * @author Chaersi
 */
public class MainActivity extends BaseActivity {
    private String[] PERMISSIONS_CONTACT = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE
    };
    private int REQUEST_CONTACTS = 1;

    @BindView(R.id.menuBtn) ImageView menuBtn;
    @BindView(R.id.topLayout) LinearLayout topLayout;
    @BindView(R.id.useCarBtn) Button useCarBtn;
    @BindView(R.id.addrView) TextView addrView;
    @BindView(R.id.localBtn) ImageView localBtn;

    @BindView(R.id.mMapView) MapView mMapView;
    private BaiduMap mBaiduMap;

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= 23) {
            showContacts(mMapView);
        } else {
            initMap();
        }
        initDragView();
    }

    @OnClick({R.id.menuBtn, R.id.topLayout, R.id.useCarBtn, R.id.localBtn})
    public void onClickListener(View view) {
        switch (view.getId()) {
            case R.id.menuBtn:
//                Intent intent=new Intent(this,MenuActivity.class);
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                break;
            case R.id.topLayout:
                break;
            case R.id.useCarBtn:
                mScrollLayout.setToOpen();
                break;
            case R.id.localBtn:
                if(center!=null){
                    MapStatusUpdate mapStatus = MapStatusUpdateFactory.newLatLngZoom(center, 14);
                    mBaiduMap.setMapStatus(mapStatus);
                    addrView.setText(addrDetail);
                }else{
                    showTips("暂无GPS坐标信息");
                }
                break;
        }
    }

    private GeoCoder mSearch;

    /**
     * 初始化地图
     */
    private void initMap() {
        mMapView.setVisibility(View.VISIBLE);
        mBaiduMap = mMapView.getMap();

        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

        //实时获取地图坐标
        mBaiduMap.setOnMapStatusChangeListener(changeListener);

        //获取地图转码
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(listener);
        initLocation();
    }

    public LocationClient mLocationClient;
    public BDLocationListener myListener;
    private void initLocation() {
        myListener = new MyLocationListener();
        mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
        mLocationClient.registerLocationListener(myListener); // 注册监听函数
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度，默认值gcj02
        option.disableCache(false);
        option.setIsNeedAddress(true);
        option.setIsNeedLocationDescribe(true);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    private LatLng center;
    private String addrDetail;
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null){
                addrDetail="暂无GPS信息";
                showTips("暂无GPS信息");
            } else{
                Double lat=location.getLatitude();
                Double lng=location.getLongitude();
                center = new LatLng(lat,lng);
                //设置中心位置
                MapStatusUpdate mapStatus = MapStatusUpdateFactory.newLatLngZoom(center, 14);
                mBaiduMap.setMapStatus(mapStatus);

                //添加屏幕中心点marker
                BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.mark_location);
                OverlayOptions option = new MarkerOptions()
                        .position(center)
                        .icon(bitmap);
                mBaiduMap.addOverlay(option);
                addrDetail=location.getProvince()+location.getCity()
                        +location.getDistrict()+location.getStreet();
                addrView.setText(addrDetail);
            }
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    public void showContacts(View view) {
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission( Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission( Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            requestContactsPermissions(view);
        } else {
            initMap();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void requestContactsPermissions(View v) {
        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)
                || shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)
                || shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                || shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE)) {
            Snackbar.make(v, "请允许地图所需要的相关权限", Snackbar.LENGTH_INDEFINITE)
                    .setAction("ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            requestPermissions(PERMISSIONS_CONTACT,REQUEST_CONTACTS);
                        }
                    }).show();
        } else {
            requestPermissions(PERMISSIONS_CONTACT, REQUEST_CONTACTS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        if (requestCode==REQUEST_CONTACTS){
            if (PermissionUtil.verifyPermissions(grantResults)) {
                initMap();
            } else {
                Snackbar.make(mMapView, "权限被拒绝，地图部分功能无法使用", Snackbar.LENGTH_INDEFINITE).show();
            }
        }else{
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private BaiduMap.OnMapStatusChangeListener changeListener=new BaiduMap.OnMapStatusChangeListener() {
        @Override
        public void onMapStatusChangeStart(MapStatus arg0) {
        }

        @Override
        public void onMapStatusChange(MapStatus arg0) {
        }

        @Override
        public void onMapStatusChangeFinish(MapStatus arg0) {
            int[] location = new int[2];
            mMapView.getLocationOnScreen(location);
            Point p=new Point(location[0]+mMapView.getWidth()/2, location[1]+mMapView.getHeight()/2);
            LatLng latLng=mBaiduMap.getProjection().fromScreenLocation(p);
            mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
        }
    };

    OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
        public void onGetGeoCodeResult(GeoCodeResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有检索到结果
            }
            //获取地理编码结果
        }

        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有找到检索结果
                addrView.setText("未找到");
            }else{
                String addr=result.getAddressDetail().province+result.getAddressDetail().city
                        +result.getAddressDetail().district+result.getAddressDetail().street;
                addrView.setText(addr);
            }
        }
    };

    /************以上为地图*****以下为其它************/

    private ScrollLayout mScrollLayout;
    private RelativeLayout includeLayout;
    private void initDragView(){
        mScrollLayout = (ScrollLayout) findViewById(R.id.scroll_down_layout);
        mScrollLayout.setOnScrollChangedListener(mOnScrollChangedListener);
        mScrollLayout.getBackground().setAlpha(0);

        includeLayout= (RelativeLayout) findViewById(R.id.includeLayout);
        includeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mScrollLayout.getCurrentStatus() == ScrollLayout.Status.OPENED) {
                    mScrollLayout.scrollToClose();
                }
            }
        });

    }

    private ScrollLayout.OnScrollChangedListener mOnScrollChangedListener = new ScrollLayout.OnScrollChangedListener() {
        @Override
        public void onScrollProgressChanged(float currentProgress) {
            if(currentProgress >= 0) {
                float precent = 255 * currentProgress;
                if (precent > 255) {
                    precent = 255;
                } else if (precent < 0) {
                    precent = 0;
                }
                mScrollLayout.getBackground().setAlpha(255 - (int) precent);
            }
        }

        @Override
        public void onScrollFinished(ScrollLayout.Status currentStatus) {
            if (currentStatus.equals(ScrollLayout.Status.EXIT)) {}
        }

        @Override
        public void onChildScroll(int top) {
        }
    };

}
