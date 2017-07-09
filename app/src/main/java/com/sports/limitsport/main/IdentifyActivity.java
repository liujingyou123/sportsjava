package com.sports.limitsport.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.mine.UserInfoActivity;
import com.sports.limitsport.util.ToastUtil;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.yalantis.ucrop.UCrop;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.util.List;

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_skip, R.id.imv_head, R.id.tv_done})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_skip:
                finish();
                break;
            case R.id.imv_head:
                showPicker();
                break;
            case R.id.tv_done:
                Intent intent = new Intent(this, SelectOwnHobbyActivity.class);
                startActivity(intent);
                break;
        }
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
            List<Uri> strings = Matisse.obtainResult(data);

            if (strings != null && strings.size() > 0) {
                Uri destinationUri = Uri.fromFile(new File(context.getCacheDir(), System.currentTimeMillis() + "_" + "head.jpg"));
                startCrop(strings.get(0), destinationUri);
            }
        } else if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            Uri resultUri = UCrop.getOutput(data);
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
}
