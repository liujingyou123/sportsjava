package com.sports.limitsport.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.dialog.DateSelectDialog;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.util.TextViewUtil;
import com.sports.limitsport.util.ToastUtil;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.yalantis.ucrop.UCrop;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Created by jingyouliu on 17/7/8.
 * 注册
 */

public class IdentifyActivity extends BaseActivity {
    @BindView(R.id.imv_head)
    ImageView imvHead;
    private static final int REQUEST_CODE_CHOOSE = 23;
    @BindView(R.id.rg_male)
    RadioGroup rgMale;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_city)
    TextView etCity;
    @BindView(R.id.tv_birth)
    TextView tvBirth;
    private String headPath;
    private String gender;
    private String name;
    private String city;
    private String birth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify);
        ButterKnife.bind(this);
        init();
    }

    @OnClick({R.id.tv_skip, R.id.imv_head, R.id.tv_done, R.id.tv_birth})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_skip:
                finish();
                break;
            case R.id.imv_head:
                showPicker();
                break;
            case R.id.tv_done:
                if (check()) {
                    Intent intent = new Intent(this, SelectOwnHobbyActivity.class);
                    intent.putExtra("headPath", headPath);
                    intent.putExtra("gender", gender);
                    intent.putExtra("name", name);
                    intent.putExtra("city", city);
                    intent.putExtra("birth", birth);
                    startActivity(intent);
                }
                break;
            case R.id.tv_birth:
                DateSelectDialog dialog = new DateSelectDialog(this, new DateSelectDialog.SelectResultListener() {
                    @Override
                    public void onResult(String date) {
                        birth = date;
                        tvBirth.setText(date);
                    }
                });
                dialog.show();
                break;
        }
    }

    private void init() {
        rgMale.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.rb_male) {
                    gender = "0";
                } else if (checkedId == R.id.rb_female) {
                    gender = "1";
                }
            }
        });
    }

    public void showPicker() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (aBoolean) {
                    Matisse.from(IdentifyActivity.this)
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
                    ToastUtil.show(IdentifyActivity.this, "Permission request denied");
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
        options.setActiveWidgetColor(ContextCompat.getColor(context, R.color.colorPrimary));
        options.setToolbarWidgetColor(ContextCompat.getColor(context, R.color.white));
        return uCrop.withOptions(options);
    }

    private boolean check() {
        if (TextViewUtil.isEmpty(headPath)) {
            ToastUtil.showFalseToast(this, "请选择头像");
            return false;
        }

        if (TextViewUtil.isEmpty(gender)) {
            ToastUtil.showFalseToast(this, "请选择性别");
            return false;
        }

        name = etName.getText().toString();
        if (TextViewUtil.isEmpty(name)) {
            ToastUtil.showFalseToast(this, "请输入昵称");
            return false;
        }

        city = etCity.getText().toString();
        if (TextViewUtil.isEmpty(city)) {
            ToastUtil.showFalseToast(this, "请输入城市");
            return false;
        }

        if (TextViewUtil.isEmpty(birth)) {
            ToastUtil.showFalseToast(this, "请选择出生年月");
            return false;
        }
        return true;
    }
}
