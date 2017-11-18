package com.sports.limitsport.mine;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.aliyunoss.AliOss;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.dialog.CitySelectDialog;
import com.sports.limitsport.dialog.DateSelectDialog;
import com.sports.limitsport.dialog.GenderSelectDialog;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.main.IdentifyMainActivity;
import com.sports.limitsport.mine.model.EventBusUserInfo;
import com.sports.limitsport.mine.presenter.UserInfoPresenter;
import com.sports.limitsport.mine.ui.IUserInfoView;
import com.sports.limitsport.model.UserInfo;
import com.sports.limitsport.model.UserInfoResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.LoadingNetSubscriber;
import com.sports.limitsport.util.SharedPrefsUtil;
import com.sports.limitsport.util.TextViewUtil;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.util.ToolsUtil;
import com.sports.limitsport.view.mine.ItemView;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.yalantis.ucrop.UCrop;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by liuworkmac on 17/7/4.
 */

public class UserInfoActivity extends BaseActivity implements IUserInfoView {
    private static final int REQUEST_CODE_CHOOSE = 23;
    private static final int REQUEST_CODE_HOBBYS = 24;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.tv_focus_right)
    TextView tvFocusRight;
    @BindView(R.id.sv)
    ScrollView sv;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.imv_head)
    ImageView imvHead;
    @BindView(R.id.tv_nicheng)
    EditText tvNicheng;
    @BindView(R.id.it_gender)
    ItemView itGender;
    @BindView(R.id.it_birth)
    ItemView itBirth;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.it_hobby)
    ItemView itHobby;
    @BindView(R.id.et_induce)
    EditText etInduce;
    @BindView(R.id.tv_phone)
    TextView tvPhone;

    private UserInfoPresenter mPresenter;

    private List<HashMap<String, String>> listHobbys;

    private UserInfoResponse.DataBean dataBean;

    private String headPath;
    private String gender;
    private String name;
    private String city;
    private String birth;
    private String hobbysU;
    private String introduce;
    private Subscription mSubscription;
    private String province;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        ButterKnife.bind(this);
        initView();
        getData();
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new UserInfoPresenter(this);
        }
        if (SharedPrefsUtil.getUserInfo() != null && SharedPrefsUtil.getUserInfo().getData().getIsPerfect() == 0) {
            mPresenter.getUserInfo();
        }

    }

    private void initView() {
        tvFocusHouse.setText("个人资料");
        tvFocusRight.setText("保存");
        tvFocusRight.setTextColor(Color.parseColor("#ffffff"));

        sv.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        sv.setFocusable(true);
        sv.setFocusableInTouchMode(true);
        sv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.requestFocusFromTouch();
                return false;
            }
        });

        etInduce.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null) {
                    tvNum.setText(s.length() + "／100字");
                }
            }
        });

        itGender.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GenderSelectDialog dialog = new GenderSelectDialog(UserInfoActivity.this, new GenderSelectDialog.SelectResultListener() {
                    @Override
                    public void onResult(String data, int position) {
                        itGender.setLableTwo(data);
                    }
                });
                dialog.show();
            }
        });

        itBirth.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateSelectDialog dialog = new DateSelectDialog(UserInfoActivity.this, new DateSelectDialog.SelectResultListener() {
                    @Override
                    public void onResult(String date) {
                        itBirth.setLableTwo(date);
                    }
                });
                dialog.show();
            }
        });

        itHobby.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this, IdentifyMainActivity.class);
                intent.putExtra("flag", IdentifyMainActivity.FLAG_HOBBY);
                intent.putExtra("type", "1");
                startActivityForResult(intent, REQUEST_CODE_HOBBYS);
            }
        });

    }

    @OnClick({R.id.imv_focus_house_back, R.id.tv_focus_right, R.id.imv_go, R.id.imv_head, R.id.tv_city})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.tv_focus_right:
                if (check()) {
                    if (TextViewUtil.isEmpty(headPath)) {
                        saveUserInfo();
                    } else {
                        doUploadPic();
                    }
                }
                break;
            case R.id.imv_go:
            case R.id.imv_head:
                showPicker();
                break;
            case R.id.tv_city:
                CitySelectDialog dialog1 = new CitySelectDialog(this, new CitySelectDialog.SelectResultListener() {
                    @Override
                    public void onResult(String province, String city) {
                        UserInfoActivity.this.province = province;
                        UserInfoActivity.this.city = city;
                        tvCity.setText(province + " " + city);
                    }
                });
                dialog1.show();
                break;
        }
    }

    public void showPicker() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (aBoolean) {
                    Matisse.from(UserInfoActivity.this)
                            .choose(MimeType.ofImage())
                            .capture(true)
                            .captureStrategy(
                                    new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider"))
                            .showSingleMediaType(true)
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                            .thumbnailScale(0.85f)
                            .theme(R.style.Matisse_Dracula)
                            .countable(false)
                            .maxSelectable(1)
                            .imageEngine(new GlideEngine())
                            .forResult(REQUEST_CODE_CHOOSE);
                } else {
                    ToastUtil.show(UserInfoActivity.this, "Permission request denied");
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            final String type = data.getStringExtra("type");
            String path = data.getStringExtra("path");
            final String uri = data.getStringExtra("uri");


            if (!TextUtils.isEmpty(uri)) {
                Uri destinationUri = Uri.fromFile(new File(context.getCacheDir(), System.currentTimeMillis() + "_" + "head.jpg"));
                startCrop(Uri.parse(uri), destinationUri);
            }
        } else if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            Uri resultUri = UCrop.getOutput(data);
            headPath = resultUri.getPath();
            Batman.getInstance().getImageWithCircle(resultUri.getPath(), imvHead, R.mipmap.icon_gerenziliao_wutouxiang, R.mipmap.icon_gerenziliao_wutouxiang);

        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
        } else if (requestCode == REQUEST_CODE_HOBBYS && resultCode == RESULT_OK) {
            listHobbys = (List<HashMap<String, String>>) data.getSerializableExtra("hobbys");

            if (listHobbys != null) {
                StringBuilder sp = new StringBuilder();
                for (int i = 0; i < listHobbys.size(); i++) {
                    HashMap<String, String> hashMap = listHobbys.get(i);
                    sp.append("　");
                    sp.append(hashMap.get("name"));
                }
                itHobby.setLableTwo(sp.toString());
            }
        }
    }

    //开启裁剪
    private void startCrop(Uri sourceUri, Uri destinationUri) {
        UCrop uCrop = UCrop.of(sourceUri, destinationUri);
        uCrop.withAspectRatio(1, 1);

        uCrop = advancedConfig(uCrop);
        uCrop.start(this);
    }

    private UCrop advancedConfig(@NonNull UCrop uCrop) {
        UCrop.Options options = new UCrop.Options();
        options.setCompressionQuality(60);
        options.setMaxScaleMultiplier(5);
        options.setImageToCropBoundsAnimDuration(666);
        options.setShowCropFrame(true);
        options.setCropGridStrokeWidth(2);
        options.setCropGridColumnCount(2);
        options.setCropGridRowCount(1);
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        // Color palette
        options.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary));
        options.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimary));
        options.setActiveWidgetColor(ContextCompat.getColor(context, R.color.color_text_green));
        options.setToolbarWidgetColor(ContextCompat.getColor(context, R.color.white));
        return uCrop.withOptions(options);
    }

    @Override
    public void showUserInfo(UserInfoResponse response) {
        if (response != null && response.getData() != null) {
            dataBean = response.getData();
            Batman.getInstance().getImageWithCircle(dataBean.getHeadPortrait(), imvHead, R.mipmap.icon_gerenziliao_wutouxiang, R.mipmap.icon_gerenziliao_wutouxiang);

            if (!TextViewUtil.isEmpty(dataBean.getName())) {
                tvNicheng.setText(dataBean.getName());
            }

            if (!TextViewUtil.isEmpty(dataBean.getSex())) {
                if ("1".equals(dataBean.getSex())) {
                    itGender.setLableTwo("女");
                } else {
                    itGender.setLableTwo("男");
                }
            }

            if (!TextViewUtil.isEmpty(dataBean.getBrithDate())) {
                itBirth.setLableTwo(dataBean.getBrithDate());
            }

            if (!TextViewUtil.isEmpty(dataBean.getCity())) {
                tvCity.setText(dataBean.getProvince() + " " + dataBean.getCity());
                city = dataBean.getCity();
                province = dataBean.getProvince();

            }

            tvPhone.setText(dataBean.getPhone());

            if (!TextViewUtil.isEmpty(dataBean.getHobby())) {
                String[] strs = dataBean.getHobby().split(",");
                StringBuilder sp = new StringBuilder();
                for (int i = 0; i < strs.length; i++) {
                    sp.append("　");
                    sp.append(strs[i]);
                }
                itHobby.setLableTwo(sp.toString());
            }

            if (!TextViewUtil.isEmpty(dataBean.getIntroduction())) {
                etInduce.setText(dataBean.getIntroduction());
            }
        }
    }

    private boolean check() {
        if ((dataBean == null || TextViewUtil.isEmpty(dataBean.getHeadPortrait())) && TextViewUtil.isEmpty(headPath)) {
            ToastUtil.showFalseToast(this, "请选择头像");
            return false;
        }

        String nameTmp = tvNicheng.getText().toString();
        if (TextViewUtil.isEmpty(nameTmp)) {
            ToastUtil.showFalseToast(this, "请输入昵称");
            return false;
        }
        name = nameTmp;

        String genderTmp = itGender.getLableTwo().trim();
        if (!"男".equals(genderTmp) && !"女".equals(genderTmp)) {
            ToastUtil.showFalseToast(this, "请选择性别");
            return false;
        }

        if ("男".equals(genderTmp)) {
            gender = "0";
        } else if ("女".equals(genderTmp)) {
            gender = "1";
        }


        String citytmp = tvCity.getText().toString();
        if (TextViewUtil.isEmpty(citytmp) || "请选择".equals(citytmp)) {
            ToastUtil.showFalseToast(this, "请选择城市");
            return false;
        }
//        city = citytmp;

        String birthtmp = itBirth.getLableTwo();
        if (TextViewUtil.isEmpty(birthtmp) || "请选择出生年月".equals(birthtmp) || "请选择".equals(birthtmp)) {
            ToastUtil.showFalseToast(this, "请选择出生年月");
            return false;
        }
        birth = birthtmp;
        if (listHobbys != null) {
            hobbysU = null;
            for (int i = 0; i < listHobbys.size(); i++) {
                HashMap<String, String> hashMap = listHobbys.get(i);
                if (TextViewUtil.isEmpty(hobbysU)) {
                    hobbysU = hashMap.get("id") + "";
                } else {
                    hobbysU = hobbysU + "," + hashMap.get("id") + "";
                }
            }
        } else {
            if (dataBean != null) {
                hobbysU = dataBean.getHobby();
            }
        }

        if (etInduce.getText() != null) {
            introduce = etInduce.getText().toString();
        }

        return true;
    }

    private void saveUserInfo() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("headPortrait", dataBean.getHeadPortrait());
        hashMap.put("name", name);
        hashMap.put("hobby", hobbysU);
        hashMap.put("sex", gender);
        hashMap.put("brithDate", birth);
        hashMap.put("coutry", "中国");
        hashMap.put("city", city);
        hashMap.put("introduction", introduce);
        hashMap.put("province", province);


        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).updateUserInfo(hashMap), new LoadingNetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (response != null && response.isSuccess()) {
                    ToastUtil.showTrueToast(UserInfoActivity.this, "修改个人资料成功");
                    UserInfo userInfo = SharedPrefsUtil.getUserInfo();
                    userInfo.getData().setIsPerfect(0);
                    SharedPrefsUtil.saveUserInfo(userInfo);
                    EventBusUserInfo params = new EventBusUserInfo();
                    params.isResfreh = true;
                    EventBus.getDefault().post(params);
                    UserInfoActivity.this.finish();

                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                ToastUtil.showTrueToast(UserInfoActivity.this, "修改个人资料失败");
            }
        });
    }

    private void doUploadPic() {
        mSubscription = Observable.just(headPath).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return AliOss.getInstance().putObjectFromByteArray(AliOss.DIR_HEAD_PORTRAIT, s);
            }
        }).flatMap(new Func1<String, Observable<BaseResponse>>() {
            @Override
            public Observable<BaseResponse> call(String s) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("headPortrait", s);
                hashMap.put("name", name);
                hashMap.put("hobby", hobbysU);
                hashMap.put("sex", gender);
                hashMap.put("brithDate", birth);
                hashMap.put("coutry", "中国");
                hashMap.put("city", city);
                hashMap.put("province", province);
                hashMap.put("introduction", introduce);

                return ToolsUtil.createService(IpServices.class).updateUserInfo(hashMap);
            }
        }).compose(ToolsUtil.<BaseResponse>applayScheduers()).subscribe(new LoadingNetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (response != null && response.isSuccess()) {
                    ToastUtil.showTrueToast(UserInfoActivity.this, "修改个人资料成功");
                    UserInfo userInfo = SharedPrefsUtil.getUserInfo();
                    userInfo.getData().setName(name);
                    userInfo.getData().setIsPerfect(0);
                    SharedPrefsUtil.saveUserInfo(userInfo);
                    EventBusUserInfo params = new EventBusUserInfo();
                    params.isResfreh = true;
                    EventBus.getDefault().post(params);
                    UserInfoActivity.this.finish();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                ToastUtil.showTrueToast(UserInfoActivity.this, "修改个人资料失败");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.clear();
        }

        if (mSubscription != null && mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }

        mPresenter = null;

    }
}
