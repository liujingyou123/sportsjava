package com.sports.limitsport.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liuworkmac on 17/8/11.
 */

public class ClubDetail implements Serializable{

    private int authEntity;
    private int authStatus;
    private String clubImgUrl;
    private String clubIntroduction;
    private String clubName;
    private int clubType;
    private String clubTypeName;
    private String clubVedioUrl;
    private String createTime;
    private String creator;
    private int id;
    private String isActivity;
    private String isJoin;
    private int joinClubFlag;
    private String logoUrl;
    private int memberNum;
    private int memberRule;
    private String memberRuleDesc;
    private String modifier;
    private int resourceType;
    private String thumbnailUrl;
    private String vedioImgUrl;
    private String vedioThumbnailUrl;
    /**
     * clubId : 1
     * headPortrait : http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg
     * id : 1
     * memberId : 8
     * memberName : 廖智明11
     * rule : 1
     * ruleDesc : 创始人
     * status : null
     * userIntroduction : 我就是我 哈哈
     * version : null
     */

    private List<ManagerListBean> managerList;

    public int getAuthEntity() {
        return authEntity;
    }

    public void setAuthEntity(int authEntity) {
        this.authEntity = authEntity;
    }

    public int getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(int authStatus) {
        this.authStatus = authStatus;
    }

    public String getClubImgUrl() {
        return clubImgUrl;
    }

    public void setClubImgUrl(String clubImgUrl) {
        this.clubImgUrl = clubImgUrl;
    }

    public String getClubIntroduction() {
        return clubIntroduction;
    }

    public void setClubIntroduction(String clubIntroduction) {
        this.clubIntroduction = clubIntroduction;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public int getClubType() {
        return clubType;
    }

    public void setClubType(int clubType) {
        this.clubType = clubType;
    }

    public String getClubTypeName() {
        return clubTypeName;
    }

    public void setClubTypeName(String clubTypeName) {
        this.clubTypeName = clubTypeName;
    }

    public String getClubVedioUrl() {
        return clubVedioUrl;
    }

    public void setClubVedioUrl(String clubVedioUrl) {
        this.clubVedioUrl = clubVedioUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsActivity() {
        return isActivity;
    }

    public void setIsActivity(String isActivity) {
        this.isActivity = isActivity;
    }

    public String getIsJoin() {
        return isJoin;
    }

    public void setIsJoin(String isJoin) {
        this.isJoin = isJoin;
    }

    public int getJoinClubFlag() {
        return joinClubFlag;
    }

    public void setJoinClubFlag(int joinClubFlag) {
        this.joinClubFlag = joinClubFlag;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public int getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }

    public int getMemberRule() {
        return memberRule;
    }

    public void setMemberRule(int memberRule) {
        this.memberRule = memberRule;
    }

    public String getMemberRuleDesc() {
        return memberRuleDesc;
    }

    public void setMemberRuleDesc(String memberRuleDesc) {
        this.memberRuleDesc = memberRuleDesc;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public int getResourceType() {
        return resourceType;
    }

    public void setResourceType(int resourceType) {
        this.resourceType = resourceType;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getVedioImgUrl() {
        return vedioImgUrl;
    }

    public void setVedioImgUrl(String vedioImgUrl) {
        this.vedioImgUrl = vedioImgUrl;
    }

    public String getVedioThumbnailUrl() {
        return vedioThumbnailUrl;
    }

    public void setVedioThumbnailUrl(String vedioThumbnailUrl) {
        this.vedioThumbnailUrl = vedioThumbnailUrl;
    }

    public List<ManagerListBean> getManagerList() {
        return managerList;
    }

    public void setManagerList(List<ManagerListBean> managerList) {
        this.managerList = managerList;
    }

    public static class ManagerListBean implements Serializable{
        private int clubId;
        private String headPortrait;
        private int id;
        private int memberId;
        private String memberName;
        private int rule;
        private String ruleDesc;
        private Object status;
        private String userIntroduction;
        private Object version;

        public int getClubId() {
            return clubId;
        }

        public void setClubId(int clubId) {
            this.clubId = clubId;
        }

        public String getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public int getRule() {
            return rule;
        }

        public void setRule(int rule) {
            this.rule = rule;
        }

        public String getRuleDesc() {
            return ruleDesc;
        }

        public void setRuleDesc(String ruleDesc) {
            this.ruleDesc = ruleDesc;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public String getUserIntroduction() {
            return userIntroduction;
        }

        public void setUserIntroduction(String userIntroduction) {
            this.userIntroduction = userIntroduction;
        }

        public Object getVersion() {
            return version;
        }

        public void setVersion(Object version) {
            this.version = version;
        }
    }
}
