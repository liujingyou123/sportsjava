package com.sports.limitsport.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.Gravity;
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
import com.baidu.mapapi.map.Text;
import com.baidu.mapapi.model.LatLng;
import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.util.UnitUtil;

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
    private String address;


    BitmapDescriptor bdA = BitmapDescriptorFactory
            .fromResource(R.mipmap.icon_map_position);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        getIntentData();
        mBaiduMap = bmapView.getMap();
        imvFocusHouseBack.setVisibility(View.VISIBLE);
        tvFocusHouse.setText("活动地点");
        location();
        initLocationMap();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            String lonStr = intent.getStringExtra("lon");
            String latStr = intent.getStringExtra("lat");
            address = intent.getStringExtra("address");

            try {
                lon = Long.parseLong(lonStr);
                lat = Long.parseLong(latStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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


        TextView textView = new TextView(getApplicationContext());
        textView.setTextColor(Color.parseColor("#FF4795FB"));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        textView.setBackgroundResource(R.mipmap.icon_position);
        textView.setText(" 阿活动后给你送噶都洒过德国队撒个蛋糕大噶时 ");
        textView.setGravity(Gravity.CENTER);
//        textView.setPadding(0, UnitUtil.dip2px(this, 7),0, UnitUtil.dip2px(this, 7));
        LatLng pt = mMarkerA.getPosition();
        InfoWindow mInfoWindow = new InfoWindow(textView, pt, -110);
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
