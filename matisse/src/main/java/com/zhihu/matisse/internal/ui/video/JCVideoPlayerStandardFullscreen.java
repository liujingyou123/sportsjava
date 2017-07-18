package com.zhihu.matisse.internal.ui.video;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhihu.matisse.R;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Nathen
 * On 2016/04/22 00:54
 */
public class JCVideoPlayerStandardFullscreen extends JCVideoPlayerStandard {

    public ImageView shareButton;

    public JCVideoPlayerStandardFullscreen(Context context) {
        super(context);
    }

    public JCVideoPlayerStandardFullscreen(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init(Context context) {
        super.init(context);
        shareButton = (ImageView) findViewById(R.id.share);
        shareButton.setOnClickListener(this);


    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_standard_jc_fullscreen;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.share) {
            Toast.makeText(getContext(), "Whatever the icon means", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setUp(String url, int screen, Object... objects) {
        super.setUp(url, screen, objects);
        backButton.setVisibility(View.GONE);
        if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
            shareButton.setVisibility(View.INVISIBLE);
            titleTextView.setVisibility(INVISIBLE);
            fullscreenButton.setImageResource(R.drawable.icon_video_cross_fullscreen);
        } else {
            shareButton.setVisibility(View.INVISIBLE);
            titleTextView.setVisibility(View.INVISIBLE);
            fullscreenButton.setImageResource(R.drawable.icon_video_vertical_fullscreen);

        }
    }
}
