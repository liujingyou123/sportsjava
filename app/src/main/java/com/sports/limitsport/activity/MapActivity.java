package com.sports.limitsport.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/6/28.
 */

public class MapActivity extends BaseActivity {
    @BindView(R.id.bmapView)
    MapView bmapView;
    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    private BaiduMap mBaiduMap;
    private Marker mMarkerA;
    private double lon = 31.2449306174; // 经度
    private double lat = 121.4972996021; // 纬度


    BitmapDescriptor bdA = BitmapDescriptorFactory
            .fromResource(R.mipmap.icon_map_position);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        mBaiduMap = bmapView.getMap();
        imvFocusHouseBack.setVisibility(View.VISIBLE);
        tvFocusHouse.setText("活动地点");
        location();
        initLocationMap();
    }

    private void location() {
        LatLng cenpt = new LatLng(lon, lat);
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(12)
                .build();

        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        mBaiduMap.setMapStatus(mMapStatusUpdate);
    }

    private void initLocationMap() {
        LatLng llA = new LatLng(lon, lat);
        MarkerOptions ooA = new MarkerOptions().position(llA).icon(bdA)
                .zIndex(9).draggable(true);
        mMarkerA = (Marker) (mBaiduMap.addOverlay(ooA));


        Button button = new Button(getApplicationContext());
        button.setTextColor(Color.parseColor("#4899ff"));
        button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        button.setBackgroundResource(R.drawable.popup);
        button.setText("阿活动后给你送噶都洒过德国队撒个蛋糕大噶时");
        LatLng pt = mMarkerA.getPosition();
        InfoWindow mInfoWindow = new InfoWindow(button, pt, -100);
        mBaiduMap.showInfoWindow(mInfoWindow);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        bmapView.onDestroy();
        bdA.recycle();

    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        bmapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        bmapView.onPause();
    }

    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        finish();
    }
}
