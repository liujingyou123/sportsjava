package com.zhihu.matisse.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.camerafragment.CameraFragment;
import com.github.florent37.camerafragment.CameraFragmentApi;
import com.github.florent37.camerafragment.PreviewActivity;
import com.github.florent37.camerafragment.configuration.Configuration;
import com.github.florent37.camerafragment.listeners.CameraFragmentControlsAdapter;
import com.github.florent37.camerafragment.listeners.CameraFragmentResultAdapter;
import com.github.florent37.camerafragment.listeners.CameraFragmentResultListener;
import com.github.florent37.camerafragment.listeners.CameraFragmentStateAdapter;
import com.github.florent37.camerafragment.listeners.CameraFragmentVideoRecordTextAdapter;
import com.github.florent37.camerafragment.widgets.CameraSettingsView;
import com.github.florent37.camerafragment.widgets.CameraSwitchView;
import com.github.florent37.camerafragment.widgets.FlashSwitchView;
import com.github.florent37.camerafragment.widgets.MediaActionSwitchView;
import com.github.florent37.camerafragment.widgets.RecordButton;
import com.zhihu.matisse.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jingyouliu on 17/7/16.
 */

public class TakePhotoActivity extends AppCompatActivity implements View.OnClickListener {
    CameraSettingsView settingsView;
    FlashSwitchView flashSwitchView;
    CameraSwitchView frontBackCameraSwitcher;
    RecordButton recordButton;
    TextView recordDurationText;
    TextView recordSizeMbText;
    MediaActionSwitchView photoVideoCameraSwitcher;
    RelativeLayout cameraLayout;

    public static final String FRAGMENT_TAG = "camera";
    private static final int REQUEST_CAMERA_PERMISSIONS = 931;
    private static final int REQUEST_PREVIEW_CODE = 1001;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takephoto);
        setCamera();

        settingsView = findView(R.id.settings_view);
        flashSwitchView = findView(R.id.flash_switch_view);
        frontBackCameraSwitcher = findView(R.id.front_back_camera_switcher);
        recordButton = findView(R.id.record_button);
        recordDurationText = findView(R.id.record_duration_text);
        recordSizeMbText = findView(R.id.record_size_mb_text);
        photoVideoCameraSwitcher = findView(R.id.photo_video_camera_switcher);
        cameraLayout = findView(R.id.cameraLayout);

        settingsView.setOnClickListener(this);
        flashSwitchView.setOnClickListener(this);
        frontBackCameraSwitcher.setOnClickListener(this);
        recordButton.setOnClickListener(this);
        recordDurationText.setOnClickListener(this);
        recordSizeMbText.setOnClickListener(this);
        photoVideoCameraSwitcher.setOnClickListener(this);
        cameraLayout.setOnClickListener(this);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//            }
//        }, 500);
    }

    private <T> T findView(int id) {
        return (T) findViewById(id);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length != 0) {
            addCamera();
        }
    }

    @RequiresPermission(Manifest.permission.CAMERA)
    public void addCamera() {
//        cameraLayout.setVisibility(View.VISIBLE);

        Configuration.Builder builder = new Configuration.Builder();
        builder.setCamera(Configuration.CAMERA_FACE_REAR);
        builder.setMediaAction(Configuration.MEDIA_ACTION_VIDEO);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        final CameraFragment cameraFragment = CameraFragment.newInstance(builder.build());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, cameraFragment, FRAGMENT_TAG)
                .commitAllowingStateLoss();

        if (cameraFragment != null) {
            cameraFragment.setResultListener(new CameraFragmentResultListener() {
                @Override
                public void onVideoRecorded(String filePath) {
                    Intent intent = getIntent();
                    intent.putExtra("path", filePath);
                    intent.putExtra("type", "video");
                    setResult(RESULT_OK, intent);
                    finish();
//                    Intent intent = PreviewActivity.newIntentVideo(TakePhotoActivity.this, filePath);
//                    startActivityForResult(intent, REQUEST_PREVIEW_CODE);
                }

                @Override
                public void onPhotoTaken(byte[] bytes, String filePath) {
                    Intent intent = getIntent();
                    intent.putExtra("path", filePath);
                    intent.putExtra("type", "photo");
                    setResult(RESULT_OK, intent);
                    finish();

//                    Intent intent = PreviewActivity.newIntentPhoto(TakePhotoActivity.this, filePath);
//                    startActivityForResult(intent, REQUEST_PREVIEW_CODE);
                }
            });

            cameraFragment.setStateListener(new CameraFragmentStateAdapter() {

                @Override
                public void onCurrentCameraBack() {
                    frontBackCameraSwitcher.displayBackCamera();
                }

                @Override
                public void onCurrentCameraFront() {
                    frontBackCameraSwitcher.displayFrontCamera();
                }

                @Override
                public void onFlashAuto() {
                    flashSwitchView.displayFlashAuto();
                }

                @Override
                public void onFlashOn() {
                    flashSwitchView.displayFlashOn();
                }

                @Override
                public void onFlashOff() {
                    flashSwitchView.displayFlashOff();
                }

                @Override
                public void onCameraSetupForPhoto() {
                    photoVideoCameraSwitcher.displayActionWillSwitchVideo();

                    recordButton.displayPhotoState();
                    flashSwitchView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onCameraSetupForVideo() {
                    photoVideoCameraSwitcher.displayActionWillSwitchPhoto();

                    recordButton.displayVideoRecordStateReady();
                    flashSwitchView.setVisibility(View.GONE);
                }

                @Override
                public void shouldRotateControls(int degrees) {
                    ViewCompat.setRotation(frontBackCameraSwitcher, degrees);
                    ViewCompat.setRotation(photoVideoCameraSwitcher, degrees);
                    ViewCompat.setRotation(flashSwitchView, degrees);
                    ViewCompat.setRotation(recordDurationText, degrees);
                    ViewCompat.setRotation(flashSwitchView, degrees);
                }

                @Override
                public void onRecordStateVideoReadyForRecord() {
                    recordButton.displayVideoRecordStateReady();
                }

                @Override
                public void onRecordStateVideoInProgress() {
                    recordButton.displayVideoRecordStateInProgress();
                }

                @Override
                public void onRecordStatePhoto() {
                    recordButton.displayPhotoState();
                }

                @Override
                public void onStopVideoRecord() {
                    flashSwitchView.setVisibility(View.GONE);
                    //cameraSwitchView.setVisibility(View.VISIBLE);
                    settingsView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onStartVideoRecord(File outputFile) {
                }
            });

            cameraFragment.setControlsListener(new CameraFragmentControlsAdapter() {
                @Override
                public void lockControls() {
                    frontBackCameraSwitcher.setEnabled(false);
                    recordButton.setEnabled(false);
                    settingsView.setEnabled(false);
                    flashSwitchView.setEnabled(false);
                }

                @Override
                public void unLockControls() {
                    frontBackCameraSwitcher.setEnabled(true);
                    recordButton.setEnabled(true);
                    settingsView.setEnabled(true);
                    flashSwitchView.setEnabled(true);
                }

                @Override
                public void allowCameraSwitching(boolean allow) {
                    frontBackCameraSwitcher.setVisibility(allow ? View.VISIBLE : View.GONE);
                }

                @Override
                public void allowRecord(boolean allow) {
                    recordButton.setEnabled(allow);
                }

                @Override
                public void setMediaActionSwitchVisible(boolean visible) {
                    photoVideoCameraSwitcher.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
                }
            });

            cameraFragment.setTextListener(new CameraFragmentVideoRecordTextAdapter() {
                @Override
                public void setRecordSizeText(long size, String text) {
                    recordSizeMbText.setText(text);
                }

                @Override
                public void setRecordSizeTextVisible(boolean visible) {
                    recordSizeMbText.setVisibility(visible ? View.VISIBLE : View.GONE);
                }

                @Override
                public void setRecordDurationText(String text) {
                    recordDurationText.setText(text);
                }

                @Override
                public void setRecordDurationTextVisible(boolean visible) {
                    recordDurationText.setVisibility(visible ? View.VISIBLE : View.GONE);
                }
            });
        }
    }

    private CameraFragmentApi getCameraFragment() {
        return (CameraFragmentApi) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
    }

    private void setCamera() {
        if (Build.VERSION.SDK_INT > 15) {
            final String[] permissions = {
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE};

            final List<String> permissionsToRequest = new ArrayList<>();
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    permissionsToRequest.add(permission);
                }
            }
            if (!permissionsToRequest.isEmpty()) {
                ActivityCompat.requestPermissions(this, permissionsToRequest.toArray(new String[permissionsToRequest.size()]), REQUEST_CAMERA_PERMISSIONS);
            } else addCamera();
        } else {
            addCamera();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.settings_view) {
            final CameraFragmentApi cameraFragment4 = getCameraFragment();
            if (cameraFragment4 != null) {
                cameraFragment4.openSettingDialog();
            }
        } else if (v.getId() == R.id.flash_switch_view) {
            final CameraFragmentApi cameraFragment = getCameraFragment();
            if (cameraFragment != null) {
                cameraFragment.toggleFlashMode();
            }
        } else if (v.getId() == R.id.front_back_camera_switcher) {
            final CameraFragmentApi cameraFragment2 = getCameraFragment();
            if (cameraFragment2 != null) {
                cameraFragment2.switchCameraTypeFrontBack();
            }
        } else if (v.getId() == R.id.record_button) {
            final CameraFragmentApi cameraFragment3 = getCameraFragment();
            if (cameraFragment3 != null) {
                cameraFragment3.takePhotoOrCaptureVideo(new CameraFragmentResultAdapter() {
                                                            @Override
                                                            public void onVideoRecorded(String filePath) {
                                                            }

                                                            @Override
                                                            public void onPhotoTaken(byte[] bytes, String filePath) {
                                                            }
                                                        },
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath(),
                        null);
            }
        } else if (v.getId() == R.id.photo_video_camera_switcher) {
            final CameraFragmentApi cameraFragment5 = getCameraFragment();
            if (cameraFragment5 != null) {
                cameraFragment5.switchActionPhotoVideo();
            }
        }

    }
}
