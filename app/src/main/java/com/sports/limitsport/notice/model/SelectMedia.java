package com.sports.limitsport.notice.model;

import android.net.Uri;

/**
 * Created by liuworkmac on 17/7/14.
 */

public class SelectMedia {
    public static final String TYPE_IMAGE = "type_image";
    public static final String TYPE_VIDEO = "type_video";
    public String path;
    public Uri uri;
    public String type;
    public String videImg;

    public String url;// 资源 上传后的路径
    public String vidImgUrl;// 视频缩略图 上传后的路径
}
