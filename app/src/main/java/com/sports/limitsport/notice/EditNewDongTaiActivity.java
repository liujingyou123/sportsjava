package com.sports.limitsport.notice;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.dialog.NoticeDelDialog;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.util.ToastUtil;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.internal.entity.Item;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Created by liuworkmac on 17/7/13.
 */

public class EditNewDongTaiActivity extends BaseActivity {
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.imv_cover)
    ImageView imvCover;
    @BindView(R.id.imv_video)
    ImageView imvVideo;
    @BindView(R.id.tv_type)
    TextView tvType;
    private static final int REQUEST_CODE_CHOOSE = 23;
    @BindView(R.id.rl_pic)
    RelativeLayout rlPic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editnew);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && s.length() > 0) {
                    etContent.setEnabled(true);
                } else {
                    etContent.setEnabled(false);
                }
            }
        });
    }

    @OnClick({R.id.imv_close, R.id.tv_focus_right, R.id.imv_close_pic, R.id.imv_pic, R.id.imv_at, R.id.imv_club, R.id.tv_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_close:
                NoticeDelDialog dialog = new NoticeDelDialog(this);
                dialog.setMessage("确认不发布动态了吗?");
                dialog.setOkClickListener(new NoticeDelDialog.OnPreClickListner() {
                    @Override
                    public void onClick() {
                        EditNewDongTaiActivity.this.finish();
                    }
                });
                dialog.show();
                break;
            case R.id.tv_focus_right:
                break;
            case R.id.imv_close_pic:
                break;
            case R.id.imv_pic:
                showPicker();
                break;
            case R.id.imv_at:
                break;
            case R.id.imv_club:
                break;
            case R.id.tv_type:
                break;
        }
    }

    public void showPicker() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (aBoolean) {
                    Matisse.from(EditNewDongTaiActivity.this)
                            .choose(MimeType.ofAll())
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
                    ToastUtil.show(EditNewDongTaiActivity.this, "Permission request denied");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {

            List<Item> items = Matisse.obtainItemsResult(data);

            if (items == null || items.size() == 0) {
                return;
            }
            rlPic.setVisibility(View.VISIBLE);


            if (items.get(0).isVideo()) {
                imvVideo.setVisibility(View.VISIBLE);
            } else { //图片
                imvVideo.setVisibility(View.GONE);
            }
            List<Uri> strings = Matisse.obtainResult(data);
            if (strings != null && strings.size() > 0) {
                Batman.getInstance().loadUri(strings.get(0), imvCover);
            }
        }
    }
}
