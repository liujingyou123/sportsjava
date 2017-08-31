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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.sports.limitsport.R;
import com.sports.limitsport.aliyunoss.AliOss;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.dialog.NoticeDelDialog;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.model.Act;
import com.sports.limitsport.model.FansList;
import com.sports.limitsport.model.ReObject;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.notice.model.SelectMedia;
import com.sports.limitsport.util.TextViewUtil;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.util.ToolsUtil;
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
import java.util.ArrayList;
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
    @BindView(R.id.imv_pic)
    ImageView imvPic;
    @BindView(R.id.tv_uploading)
    TextView tvUploading;
    @BindView(R.id.uploading_progress)
    ProgressBar uploadingProgress;
    @BindView(R.id.fl_uploading_tip)
    FrameLayout flUploadingTip;
    private SelectMedia selectMedia;
    private Act selectAct;
    //    private List<Integer> mSelectPosition;
//    private int activityPosition = -1;
    private List<FansList> mSelect = new ArrayList<>();
    private Subscription mUploadSb;
    private OSSAsyncTask mOssAsyncTask;


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
                checkPublish();
//                if (s != null && s.length() > 0) {
//                    tvFocusRight.setEnabled(true);
//                } else {
//                    tvFocusRight.setEnabled(false);
//                }
            }
        });

        etContent.setOnDelObjectListener(new REEditText.OnDelObjectListener() {
            @Override
            public void onDeleteListener(ReObject object) {
                if (object != null) {
                    if ("1".equals(object.getType())) {
                        if (mSelect != null && mSelect.size() > 0) {
                            for (int i = 0; i < mSelect.size(); i++) {
                                FansList fansList = mSelect.get(i);
                                if (fansList.getId().equals(object.getId())) {
                                    mSelect.remove(i);
                                    break;
                                }
                            }
                        }
                    } else {
                        selectAct = null;
                    }
                }
            }
        });
    }

    @OnClick({R.id.imv_close, R.id.tv_focus_right, R.id.imv_close_pic, R.id.imv_pic, R.id.imv_at, R.id.imv_club, R.id.tv_type, R.id.fl_pic, R.id.tv_uploading})
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
            case R.id.tv_uploading:
                tvFocusRight.setEnabled(false);
                tvUploading.setEnabled(false);
                publish();
                break;
            case R.id.imv_close_pic:
                cancelUploadPic();
                selectMedia = null;
                flPic.setVisibility(View.GONE);
                imvCover.setImageResource(0);
                imvPic.setVisibility(View.VISIBLE);
                flUploadingTip.setVisibility(View.GONE);
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

    private void checkPublish() {
        if ((etContent != null && !TextViewUtil.isEmpty(etContent.getContent())) && selectMedia != null) {
            tvFocusRight.setEnabled(true);
        } else {
            tvFocusRight.setEnabled(false);
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
                    imvPic.setVisibility(View.GONE);
                    checkPublish();
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
            imvPic.setVisibility(View.GONE);
            checkPublish();
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
            List<FansList> tmpmSelect = (List<FansList>) data.getSerializableExtra("name");
//            for (int i = 0; i < tmpmSelect.size(); i++) {
//                FansList fansList = tmpmSelect.get(i);
//                if (mSelect != null && !mSelect.contains(fansList)) {
//                    mSelect.add(fansList);
//                }
//            }
//            mSelect = (List<FansList>) data.getSerializableExtra("name");
//            mSelectPosition = (List<Integer>) data.getSerializableExtra("select");
            if (tmpmSelect != null && tmpmSelect.size() > 0) {
//                etContent.clear("1");
                for (int i = 0; i < tmpmSelect.size(); i++) {
                    ReObject reObject = new ReObject();
                    reObject.setType("1");
                    reObject.setIndex(-1);
                    reObject.setId(tmpmSelect.get(i).getId());
                    reObject.setText(tmpmSelect.get(i).getName());
                    etContent.appendInsert(reObject);
                }
            }
        } else if (requestCode == REQUEST_CODE_ACTIVITY && resultCode == RESULT_OK) {
            selectAct = (Act) data.getSerializableExtra("activity");
//            activityPosition = data.getIntExtra("positionSelect", -1);
            etContent.clearActivity();
            if (selectAct != null) {
//                etContent.clear("2");
                ReObject reObject = new ReObject();
                reObject.setIndex(-1);
                reObject.setType("2");
                reObject.setStartRule("#");
                reObject.setEndRule("#");
                reObject.setText(selectAct.getName());
                etContent.append(reObject);
            }

        }
    }

    /**
     * 选择我关注的人
     */
    private void gotoSelectMyFocusPerson() {
        Intent intent = new Intent(this, SelectMyFocusPersonActivity.class);
        intent.putExtra("select", (Serializable) mSelect);
        startActivityForResult(intent, REQUEST_CODE_AT);
    }

    /**
     * 选择我参加的活动
     */
    private void gotoSelectMyJionActivitys() {
        Intent intent = new Intent(this, SelectMyJoinActivity.class);
        if (selectAct != null) {
            intent.putExtra("activity", selectAct);
        }
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

    private void publish() {
        if (check()) {
            if (selectMedia != null) {
                doUploadSource();
            } else {
                publishDongTai();
            }
        }
    }

    OSSProgressCallback<PutObjectRequest> putObjectRequest = new OSSProgressCallback<PutObjectRequest>() {
        @Override
        public void onProgress(PutObjectRequest putObjectRequest, long currentSize, long totalSize) {
//            int progress = (int) (currentSize % totalSize) * 100;

            double shang = (double) currentSize / totalSize;
            int progress = (int) (shang * 100);
//            long yushu = (currentSize / totalSize);
            XLog.e("currentSize: " + currentSize + " totalSize: " + totalSize);
//            XLog.e("yushu = " + yushu);
            XLog.e("progress = " + progress);
            uploadingProgress.setProgress(progress);
        }
    };

    OSSCompletedCallback<PutObjectRequest, PutObjectResult> ossCompletedCallback = new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
        @Override
        public void onSuccess(PutObjectRequest putObjectRequest, PutObjectResult putObjectResult) {
            selectMedia.url = AliOss.getInstance().getUploadString(putObjectRequest.getObjectKey());
            publishDongTai();
        }

        @Override
        public void onFailure(PutObjectRequest putObjectRequest, ClientException e, ServiceException e1) {
            ToastUtil.showFalseToast(EditNewDongTaiActivity.this, "上传资源失败");
            uploadError();
        }
    };

    /**
     * 取消正在进行的上传
     */
    private void cancelUploadPic() {
        if (mOssAsyncTask != null && (!mOssAsyncTask.isCompleted() || !mOssAsyncTask.isCanceled())) {
            mOssAsyncTask.cancel();
        }

        if (mUploadSb != null && !mUploadSb.isUnsubscribed()) {
            mUploadSb.unsubscribe();
        }
        mOssAsyncTask = null;
        mUploadSb = null;
        tvFocusRight.setEnabled(true);
        tvUploading.setEnabled(true);
    }

    private void uploadingView() {
        flUploadingTip.setVisibility(View.VISIBLE);
        tvUploading.setText("上传中...");
        tvUploading.setEnabled(false);
        imvVideo.setVisibility(View.GONE);
        uploadingProgress.setProgress(0);
        uploadingProgress.setMax(100);
        uploadingProgress.setVisibility(View.VISIBLE);
    }

    private void uploadError() {
        flUploadingTip.setVisibility(View.VISIBLE);
        tvUploading.setText("重新上传");
        tvUploading.setEnabled(true);
        uploadingProgress.setVisibility(View.GONE);
        tvFocusRight.setEnabled(true);
    }

    /**
     * 上传图片或视频
     */
    private void doUploadSource() {
        if (TextViewUtil.isEmpty(selectMedia.path)) {
            return;
        }
        uploadingView();

        mUploadSb = Observable.just(selectMedia).map(new Func1<SelectMedia, OSSAsyncTask>() {
            @Override
            public OSSAsyncTask call(SelectMedia selectM) {
                if (!"photo".equals(selectM.type)) {
                    Bitmap bitmap = Batman.getInstance().getBitMap(EditNewDongTaiActivity.this, selectM.uri);
                    if (bitmap != null) {
                        selectMedia.videImg = ToolsUtil.saveImageToGallery(EditNewDongTaiActivity.this, bitmap);
                    }
                }

                XLog.e("selectMedia.videImg =" + selectM.videImg);
                if (!TextViewUtil.isEmpty(selectM.videImg)) {
                    selectMedia.vidImgUrl = AliOss.getInstance().putObjectFromLocalFile(AliOss.DIR_HEAD_PORTRAIT, selectM.videImg);
                }

//                if (!TextViewUtil.isEmpty(selectMedia.path)) {
//                    selectMedia.url = AliOss.getInstance().putObjectFromLocalFile(AliOss.DIR_HEAD_PORTRAIT, selectMedia.path, putObjectRequest);
//                }

                return AliOss.getInstance().asyncPutObjectFromLocalFile(AliOss.DIR_HEAD_PORTRAIT, selectM.path, putObjectRequest, ossCompletedCallback);
            }
        }).compose(ToolsUtil.<OSSAsyncTask>applayScheduers()).subscribe(new Action1<OSSAsyncTask>() {
            @Override
            public void call(OSSAsyncTask ossAsyncTask) {
                mOssAsyncTask = ossAsyncTask;
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                uploadError();
                ToastUtil.showFalseToast(EditNewDongTaiActivity.this, "网络有问题，请稍后重试");
            }
        });

//        mSubscription = Observable.just(selectMedia).map(new Func1<SelectMedia, SelectMedia>() {
//            @Override
//            public SelectMedia call(SelectMedia selectMedia) {
//                if (!TextViewUtil.isEmpty(selectMedia.path)) {
//                    selectMedia.url = AliOss.getInstance().putObjectFromLocalFile(AliOss.DIR_HEAD_PORTRAIT, selectMedia.path, putObjectRequest);
//                }
//
//                if (!"photo".equals(selectMedia.type)) {
//                    Bitmap bitmap = Batman.getInstance().getBitMap(EditNewDongTaiActivity.this, selectMedia.uri);
//                    if (bitmap != null) {
//                        selectMedia.videImg = ToolsUtil.saveImageToGallery(EditNewDongTaiActivity.this, bitmap);
//                    }
//                }
//
//                XLog.e("selectMedia.videImg =" + selectMedia.videImg);
//                if (!TextViewUtil.isEmpty(selectMedia.videImg)) {
//                    selectMedia.vidImgUrl = AliOss.getInstance().putObjectFromLocalFile(AliOss.DIR_HEAD_PORTRAIT, selectMedia.videImg, putObjectRequest);
//                }
//                return selectMedia;
//            }
//        }).flatMap(new Func1<SelectMedia, Observable<BaseResponse>>() {
//            @Override
//            public Observable<BaseResponse> call(SelectMedia selectMedia) {
//                HashMap<String, Object> params = getParams();
//
//                if ("photo".equals(selectMedia.type)) {
//                    params.put("resourceType", "1");
//                    params.put("imgUrl", selectMedia.url);
//                } else {
//                    params.put("resourceType", "1");
//                    params.put("vedioUrl", selectMedia.url);
//                    params.put("vedioImgUrl", selectMedia.vidImgUrl);
//                }
//
//                return ToolsUtil.createService(IpServices.class).publishDongTai(params);
//            }
//        }).compose(ToolsUtil.<BaseResponse>applayScheduers()).subscribe(new LoadingNetSubscriber<BaseResponse>() {
//            @Override
//            public void response(BaseResponse response) {
//                if (response != null && response.isSuccess()) {
//                    ToastUtil.showTrueToast(EditNewDongTaiActivity.this, "发布成功");
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//                ToastUtil.showTrueToast(EditNewDongTaiActivity.this, "发布失败");
//            }
//        });
    }

    private boolean check() {
        if (selectMedia == null) {
            ToastUtil.showFalseToast(this, "请选择图片");
            return false;
        }

        if (TextViewUtil.isEmpty(etContent.getContent())) {
            ToastUtil.showFalseToast(this, "请输入内容");
            return false;
        }
        return true;
    }

    private HashMap<String, Object> getParams() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("content", etContent.getContent());
        if (selectAct != null) {
            hashMap.put("activityId", selectAct.getId());
            hashMap.put("activityName", selectAct.getName());
        }

        if (tvType.isSelected()) { //私密
            hashMap.put("publicType", "0");
        } else {
            hashMap.put("publicType", "1");
        }

        StringBuffer sb = null;
        if (mSelect != null && mSelect.size() > 0) {
            sb = new StringBuffer();
            for (int i = 0; i < mSelect.size(); i++) {
                sb.append(mSelect.get(i).getId());
                if (i != mSelect.size() - 1) {
                    sb.append(",");
                }
            }
        }

        if (sb != null) {
            hashMap.put("atUsers", sb.toString());
        }

        if (selectMedia != null) {
            if ("photo".equals(selectMedia.type)) {
                hashMap.put("resourceType", "1");
                hashMap.put("imgUrl", selectMedia.url);
            } else {
                hashMap.put("resourceType", "2");
                hashMap.put("vedioUrl", selectMedia.url);
                hashMap.put("vedioImgUrl", selectMedia.vidImgUrl);
            }
        }

        return hashMap;
    }

    public void publishDongTai() {
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).publishDongTai(getParams()), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (response.isSuccess()) {
                    ToastUtil.showTrueToast(EditNewDongTaiActivity.this, "发布成功");
                    finish();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                uploadError();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelUploadPic();
    }
}
