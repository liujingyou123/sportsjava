package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/26.
 */

public class FineShowListResponse extends BaseResponse{


    /**
     * content : 秀出你的人生
     * headPortrait : http://img1.imgtn.bdimg.com/it/u=2670034815,688956640&fm=26&gp=0.jpg
     * id : 1
     * imgUrl : www.baidu.com
     * orderNum : null
     * praiseNum : 2
     * publishUserId : 8
     * publishUserName : 廖智明121
     * resourceType : 1
     * status : 1
     * thumbnailUrl : null
     * title : 精彩秀秀
     * topFlag : 1
     * type : 2
     * vedioThumbnailUrl : null
     * vedioUrl :
     */

    private List<FineShowList> data;

    public List<FineShowList> getData() {
        return data;
    }

    public void setData(List<FineShowList> data) {
        this.data = data;
    }
}
