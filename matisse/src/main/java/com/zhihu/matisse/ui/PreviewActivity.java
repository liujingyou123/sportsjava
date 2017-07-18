package com.zhihu.matisse.ui;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhihu.matisse.R;
import com.zhihu.matisse.internal.entity.SelectionSpec;
import com.zhihu.matisse.internal.utils.PhotoMetadataUtils;
import com.zhihu.matisse.internal.utils.Platform;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;

/**
 * Created by liuworkmac on 17/7/18.
 */

public class PreviewActivity extends Activity implements View.OnClickListener {
    private ImageViewTouch imageViewTouch;
    private ImageView imvVideo;
    private String path;
    private String uri;
    private String type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_custom);
//        if (Platform.hasKitKat()) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }

        getData();
        initView();
    }

    private void getData() {
        Intent intent = getIntent();
        if (intent != null) {
            path = intent.getStringExtra("path");
            uri = intent.getStringExtra("uri");
            type = intent.getStringExtra("type");
        }
    }

    private void initView() {
        findViewById(R.id.imv_focus_house_back).setOnClickListener(this);
        findViewById(R.id.tv_ok).setOnClickListener(this);
        imageViewTouch = (ImageViewTouch) findViewById(R.id.image_view);
        imvVideo = (ImageView) findViewById(R.id.video_play_button);
        imvVideo.setOnClickListener(this);

        imageViewTouch.setDisplayType(ImageViewTouchBase.DisplayType.FIT_TO_SCREEN);

        if ("video".equals(type)) {
            imvVideo.setVisibility(View.VISIBLE);
            imvVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(uri), "video/*");
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(PreviewActivity.this, R.string.error_no_video_activity, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            imvVideo.setVisibility(View.GONE);
        }

        Point size = PhotoMetadataUtils.getBitmapSize(Uri.parse(uri), this);
        SelectionSpec.getInstance().imageEngine.loadImage(this, size.x, size.y, imageViewTouch,
                Uri.parse(uri));

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imv_focus_house_back) {
            finish();
        } else if (v.getId() == R.id.tv_ok) {
            Intent intent = getIntent();

            intent.putExtra("type", type);
            intent.putExtra("path", path);
            intent.putExtra("uri", uri);

            setResult(RESULT_OK, intent);
            finish();
        } else if (v.getId() == R.id.video_play_button) {

        }
    }
}
