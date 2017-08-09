package com.sports.limitsport.notice;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.dialog.NoticeDelDialog;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.Act;
import com.sports.limitsport.model.FansList;
import com.sports.limitsport.model.ReObject;
import com.sports.limitsport.notice.model.SelectMedia;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.view.REEditText;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.yalantis.ucrop.UCrop;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.ui.PreviewActivity;

import java.io.File;
import java.io.Serializable;
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
    REEditText etContent;
    @BindView(R.id.imv_cover)
    ImageView imvCover;
    @BindView(R.id.imv_video)
    ImageView imvVideo;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.fl_pic)
    FrameLayout flPic;
    @BindView(R.id.tv_focus_right)
    TextView tvFocusRight;

    private static final int REQUEST_CODE_CHOOSE = 23;
    private static final int REQUEST_CODE_AT = 200;
    private static final int REQUEST_CODE_ACTIVITY = 201;
    private SelectMedia selectMedia;
    private Act selectAct;
    private List<Integer> mSelectPosition;


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
                    tvFocusRight.setEnabled(true);
                } else {
                    tvFocusRight.setEnabled(false);
                }
            }
        });
    }

    @OnClick({R.id.imv_close, R.id.tv_focus_right, R.id.imv_close_pic, R.id.imv_pic, R.id.imv_at, R.id.imv_club, R.id.tv_type, R.id.fl_pic})
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
                selectMedia = null;
                flPic.setVisibility(View.GONE);
                imvCover.setImageResource(0);
                break;
            case R.id.imv_pic:
                showPicker();
                break;
            case R.id.imv_at:
                gotoSelectMyFocusPerson();
                break;
            case R.id.imv_club:
                gotoSelectMyJionActivitys();
                break;
            case R.id.tv_type:
                if (tvType.isSelected()) {
                    tvType.setSelected(false);
                    tvType.setText("公开");
                } else {
                    tvType.setSelected(true);
                    tvType.setText("私密");
                }
                break;
            case R.id.fl_pic:
                if (selectMedia != null && !TextUtils.isEmpty(selectMedia.type)) {
                    Intent intents = new Intent(context, PreviewActivity.class);
                    intents.putExtra("type", selectMedia.type);
                    intents.putExtra("path", selectMedia.path);
                    intents.putExtra("uri", selectMedia.uri.toString());
                    intents.putExtra("isDel", true);
                    startActivityForResult(intents, REQUEST_CODE_CHOOSE);
                }
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
                            .capture(false)
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
            selectMedia = new SelectMedia();

            final String type = data.getStringExtra("type");
            String path = data.getStringExtra("path");
            final String uri = data.getStringExtra("uri");

            if (!TextUtils.isEmpty(path)) {

                if ("photo".equals(type)) {
                    if (!TextUtils.isEmpty(uri)) {
                        Uri destinationUri = Uri.fromFile(new File(context.getCacheDir(), System.currentTimeMillis() + "_" + "dongtai.jpg"));
                        startCrop(Uri.parse(uri), destinationUri);
                    }
                } else {
                    selectMedia.type = type;
                    selectMedia.path = path;
                    if (!TextUtils.isEmpty(uri)) {
                        selectMedia.uri = Uri.parse(uri);
                    } else {
                        selectMedia.uri = null;
                    }
                    flPic.setVisibility(View.VISIBLE);
                    imvCover.setImageResource(0);
                    imvVideo.setVisibility(View.GONE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imvVideo.setVisibility(View.VISIBLE);
                            Batman.getInstance().loadUri(Uri.parse(uri), imvCover);
                        }
                    }, 500);
                }

            } else {
                flPic.setVisibility(View.GONE);
            }


        } else if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {

            Uri resultUri = UCrop.getOutput(data);
            String path = resultUri.getPath();

            flPic.setVisibility(View.VISIBLE);
            imvVideo.setVisibility(View.GONE);

            selectMedia.type = "photo";
            selectMedia.path = path;
            selectMedia.uri = resultUri;
            Batman.getInstance().fromNet(path, imvCover, 0, 0);

        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
        } else if (requestCode == REQUEST_CODE_AT && resultCode == RESULT_OK) {
            List<FansList> mSelect = (List<FansList>) data.getSerializableExtra("name");
            mSelectPosition = (List<Integer>) data.getSerializableExtra("select");
            if (mSelect != null && mSelect.size() > 0) {
                etContent.clearAt();
                for (int i = 0; i < mSelect.size(); i++) {
                    ReObject reObject = new ReObject();
                    reObject.setText(mSelect.get(i).getName());
                    etContent.append(reObject);
                }
            }
        } else if (requestCode == REQUEST_CODE_ACTIVITY && resultCode == RESULT_OK) {
            selectAct = (Act) data.getSerializableExtra("activity");
            etContent.setText("#" + selectAct.getName() + "#" + etContent.getText().toString());
            etContent.setSelection(etContent.getText().toString().length());
        }
    }

    /**
     * 选择我关注的人
     */
    private void gotoSelectMyFocusPerson() {
        Intent intent = new Intent(this, SelectMyFocusPersonActivity.class);
        intent.putExtra("select", (Serializable) mSelectPosition);
        startActivityForResult(intent, REQUEST_CODE_AT);
    }

    /**
     * 选择我参加的活动
     */
    private void gotoSelectMyJionActivitys() {
        Intent intent = new Intent(this, SelectMyJoinActivity.class);
        startActivityForResult(intent, REQUEST_CODE_ACTIVITY);
    }

    //开启裁剪
    private void startCrop(Uri sourceUri, Uri destinationUri) {
        UCrop uCrop = UCrop.of(sourceUri, destinationUri);
        uCrop.withAspectRatio(16, 9);

        uCrop = advancedConfig(uCrop);
        uCrop.start(this);
    }

    private UCrop advancedConfig(@NonNull UCrop uCrop) {
        UCrop.Options options = new UCrop.Options();
        options.setCompressionQuality(100);
        options.setMaxScaleMultiplier(5);
        options.setImageToCropBoundsAnimDuration(666);
        options.setShowCropFrame(true);
        options.setCropGridColumnCount(0);
        options.setCropGridRowCount(0);
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        // Color palette
        options.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary));
        options.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimary));
        options.setActiveWidgetColor(ContextCompat.getColor(context, R.color.color_text_green));
        options.setToolbarWidgetColor(ContextCompat.getColor(context, R.color.white));
        return uCrop.withOptions(options);
    }
}
