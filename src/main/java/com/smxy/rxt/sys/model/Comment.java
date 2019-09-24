package com.smxy.rxt.sys.model;

import javax.persistence.*;

public class Comment {
    @Id
    @Column(name = "commentId")
    private Integer commentid;

    /**
     * 分类员
     */
    @Column(name = "sortId")
    private Integer sortid;

    /**
     * 评论者
     */
    @Column(name = "userId")
    private Integer userid;

    private String content;

    /**
     * @return commentId
     */
    public Integer getCommentid() {
        return commentid;
    }

    /**
     * @param commentid
     */
    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    /**
     * 获取分类员
     *
     * @return sortId - 分类员
     */
    public Integer getSortid() {
        return sortid;
    }

    /**
     * 设置分类员
     *
     * @param sortid 分类员
     */
    public void setSortid(Integer sortid) {
        this.sortid = sortid;
    }

    /**
     * 获取评论者
     *
     * @return userId - 评论者
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置评论者
     *
     * @param userid 评论者
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }
}