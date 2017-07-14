package com.sports.limitsport.notice;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.sports.limitsport.notice.model.SelectMedia;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.view.imagepreview.ImagePreviewActivity;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.internal.entity.Item;

import java.util.ArrayList;
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
    @BindView(R.id.fl_pic)
    FrameLayout flPic;
    @BindView(R.id.tv_focus_right)
    TextView tvFocusRight;

    private static final int REQUEST_CODE_CHOOSE = 23;
    private static final int REQUEST_CODE_AT = 200;
    private static final int REQUEST_CODE_ACTIVITY = 201;
    private SelectMedia selectMedia;


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
                    if (SelectMedia.TYPE_IMAGE.equals(selectMedia.type)) {
                        ArrayList<String> list = new ArrayList<>();
                        list.add(selectMedia.path);
                        Intent intents = new Intent(context, ImagePreviewActivity.class);
                        intents.putExtra("pics", list);
                        intents.putExtra("index", 0);
                        context.startActivity(intents);
                    } else if (SelectMedia.TYPE_VIDEO.equals(selectMedia.type)) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(selectMedia.uri, "video/*");
                        try {
                            startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            ToastUtil.show(this, com.zhihu.matisse.R.string.error_no_video_activity);
                        }
                    }
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
            selectMedia = new SelectMedia();

            List<Item> items = Matisse.obtainItemsResult(data);

            flPic.setVisibility(View.VISIBLE);


            if (items != null && items.size() > 0 && items.get(0).isVideo()) {
                selectMedia.type = SelectMedia.TYPE_VIDEO;
                imvVideo.setVisibility(View.VISIBLE);
            } else { //图片
                selectMedia.type = SelectMedia.TYPE_IMAGE;
                imvVideo.setVisibility(View.GONE);
            }
            List<Uri> strings = Matisse.obtainResult(data);
            if (strings != null && strings.size() > 0) {
                selectMedia.uri = strings.get(0);
                Batman.getInstance().loadUri(strings.get(0), imvCover);
            }

            List<String> paths = Matisse.obtainPathResult(data);

            if (paths != null && paths.size() > 0) {
                selectMedia.path = paths.get(0);
            }
        } else if (requestCode == REQUEST_CODE_AT && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            etContent.setText(etContent.getText().toString() + "@" + name);
            etContent.setSelection(etContent.getText().toString().length());
        } else if (requestCode == REQUEST_CODE_ACTIVITY && resultCode == RESULT_OK) {
            String activity = data.getStringExtra("activity");
            etContent.setText("#" + activity + "#" + etContent.getText().toString());
            etContent.setSelection(etContent.getText().toString().length());
        }
    }

    /**
     * 选择我关注的人
     */
    private void gotoSelectMyFocusPerson() {
        Intent intent = new Intent(this, SelectMyFocusPersonActivity.class);
        startActivityForResult(intent, REQUEST_CODE_AT);
    }

    /**
     * 选择我关注的人
     */
    private void gotoSelectMyJionActivitys() {
        Intent intent = new Intent(this, SelectMyJoinActivity.class);
        startActivityForResult(intent, REQUEST_CODE_ACTIVITY);
    }
}
