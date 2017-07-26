package com.sports.limitsport.model;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/26.
 */

public class CommentList {
    private int articleId;
    private int commentType;
    private int commentatorId;
    private String commentatorName;
    private Object commentedUserId;
    private Object commentedUserName;
    private String content;
    private String createTime;
    private String headPortrait;
    private int id;
    private String showCreateTime;
    private List<ReplyList> replyList;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getCommentType() {
        return commentType;
    }

    public void setCommentType(int commentType) {
        this.commentType = commentType;
    }

    public int getCommentatorId() {
        return commentatorId;
    }

    public void setCommentatorId(int commentatorId) {
        this.commentatorId = commentatorId;
    }

    public String getCommentatorName() {
        return commentatorName;
    }

    public void setCommentatorName(String commentatorName) {
        this.commentatorName = commentatorName;
    }

    public Object getCommentedUserId() {
        return commentedUserId;
    }

    public void setCommentedUserId(Object commentedUserId) {
        this.commentedUserId = commentedUserId;
    }

    public Object getCommentedUserName() {
        return commentedUserName;
    }

    public void setCommentedUserName(Object commentedUserName) {
        this.commentedUserName = commentedUserName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public String getShowCreateTime() {
        return showCreateTime;
    }

    public void setShowCreateTime(String showCreateTime) {
        this.showCreateTime = showCreateTime;
    }

    public List<ReplyList> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ReplyList> replyList) {
        this.replyList = replyList;
    }


    public static class ReplyList {
        private String commentUserId;
        private String commentUserName;
        private String createTime;
        private String creater;
        private String id;
        private String replyCommentId;
        private String replyContent;
        private String replyUserId;
        private String replyUserName;

        public String getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(String commentUserId) {
            this.commentUserId = commentUserId;
        }

        public String getCommentUserName() {
            return commentUserName;
        }

        public void setCommentUserName(String commentUserName) {
            this.commentUserName = commentUserName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreater() {
            return creater;
        }

        public void setCreater(String creater) {
            this.creater = creater;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getReplyCommentId() {
            return replyCommentId;
        }

        public void setReplyCommentId(String replyCommentId) {
            this.replyCommentId = replyCommentId;
        }

        public String getReplyContent() {
            return replyContent;
        }

        public void setReplyContent(String replyContent) {
            this.replyContent = replyContent;
        }

        public String getReplyUserId() {
            return replyUserId;
        }

        public void setReplyUserId(String replyUserId) {
            this.replyUserId = replyUserId;
        }

        public String getReplyUserName() {
            return replyUserName;
        }

        public void setReplyUserName(String replyUserName) {
            this.replyUserName = replyUserName;
        }
    }
}
