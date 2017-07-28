package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.base.SelectEntity;

import java.util.List;

/**
 * Created by jingyouliu on 17/7/9.
 */

public class Hobby extends BaseResponse{

    /**
     * hobby : 游泳
     * id : 1
     * imageUrl : 212222
     * index : 127
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends SelectEntity {
        private String hobby;
        private int id;
        private String imageUrl;
        private int index;

        public String getHobby() {
            return hobby;
        }

        public void setHobby(String hobby) {
            this.hobby = hobby;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
}
