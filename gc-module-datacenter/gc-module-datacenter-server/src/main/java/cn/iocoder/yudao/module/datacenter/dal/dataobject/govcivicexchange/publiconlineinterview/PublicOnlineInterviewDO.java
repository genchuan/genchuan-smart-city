package cn.iocoder.yudao.module.datacenter.dal.dataobject.govcivicexchange.publiconlineinterview;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 在线访谈 DO
 *
 * @author 亘川智城
 */
@TableName("biz_public_online_interview")
@KeySequence("biz_public_online_interview_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicOnlineInterviewDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 访谈ID，唯一编码，UUID生成
     */
    private String interviewId;
    /**
     * 访谈主题，访谈核心主题，如“社保新政在线访谈”
     */
    private String interviewTopic;
    /**
     * 访谈单位编码，主办部门编码，部门信息表（sys_org）
     */
    private String deptCode;
    /**
     * 访谈单位名称，与部门编码同步，部门信息表（sys_org）
     */
    private String deptName;
    /**
     * 嘉宾信息，嘉宾姓名、职务、简介，多个用逗号分隔
     */
    private String guestInfo;
    /**
     * 访谈时间，访谈开始时间，格式“yyyy-MM-dd HH:mm”
     */
    private LocalDateTime interviewTime;
    /**
     * 访谈时长，访谈预计时长，单位：分钟，如60=1小时
     */
    private Integer interviewEndure;
    /**
     * 访谈状态：1=未开始，2=进行中，3=已结束，4=实录公示，流程状态
     */
    private Integer interviewStatus;
    /**
     * 提问总数，市民提问累计数量，默认0
     */
    private Integer questionCount;
    /**
     * 回复总数，嘉宾回复累计数量，默认0
     */
    private Integer replyCount;
    /**
     * 实录内容，访谈后整理的问答实录，支持富文本
     */
    private String recContent;
    /**
     * 实录发布时间，实录公示的时间
     */
    private LocalDateTime recPublishTime;
    /**
     * 发布人ID，发布访谈的人员ID，用户表（sys_user）
     */
    private Long publishUserId;
    /**
     * 发布人姓名，与发布人ID同步，用户表（sys_user）
     */
    private String publishUserName;
    /**
     * 备注，补充说明，如“访谈将在抖音同步直播”
     */
    private String remark;
    /**
     * 分类扩展字段1，预留，标识访谈形式，如纯文字/视频
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，标识访谈平台，如官网/APP
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储直播链接
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储实录下载地址
     */
    private String extCommon2;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}