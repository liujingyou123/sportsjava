package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liuworkmac on 17/8/7.
 */

public class ClubDetailResponse extends BaseResponse{

    /**
     * authEntity : 2
     * authStatus : 1
     * clubImgUrl : http://ex-fans-tst.img-cn-hangzhou.aliyuncs.com/clubFile/IMG_1501692076269_99027IMG000002.jpeg
     * clubIntroduction : null
     * clubName : 滑雪俱乐部
     * clubType : 1
     * clubTypeName : 滑雪
     * clubVedioUrl : null
     * createTime : null
     * creator : null
     * id : 1
     * isActivity : null
     * isJoin : null
     * joinClubFlag : 0
     * logoUrl : http://ex-fans-tst.img-cn-hangzhou.aliyuncs.com/clubFile/IMG_1501692076269_99027IMG000002.jpeg
     * managerList : [{"clubId":1,"headPortrait":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg","id":1,"memberId":8,"memberName":"廖智明11","rule":1,"ruleDesc":"创始人","status":null,"userIntroduction":"我就是我 哈哈","version":null}]
     * memberNum : 5
     * memberRule : 1
     * memberRuleDesc : 创始人
     * modifier : null
     * resourceType : 1
     * thumbnailUrl : null
     * vedioImgUrl : null
     * vedioThumbnailUrl : null
     */

    private ClubDetail data;

    public ClubDetail getData() {
        return data;
    }

    public void setData(ClubDetail data) {
        this.data = data;
    }

}
