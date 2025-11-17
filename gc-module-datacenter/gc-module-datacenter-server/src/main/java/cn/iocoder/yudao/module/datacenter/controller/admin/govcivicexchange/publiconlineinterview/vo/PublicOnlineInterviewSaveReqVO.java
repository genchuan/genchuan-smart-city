package cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publiconlineinterview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 在线访谈新增/修改 Request VO")
@Data
public class PublicOnlineInterviewSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29155")
    private Long id;

    @Schema(description = "访谈ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "21965")
    @NotEmpty(message = "访谈ID，唯一编码，UUID生成不能为空")
    private String interviewId;

    @Schema(description = "访谈主题，访谈核心主题，如“社保新政在线访谈”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "访谈主题，访谈核心主题，如“社保新政在线访谈”不能为空")
    private String interviewTopic;

    @Schema(description = "访谈单位编码，主办部门编码，部门信息表（sys_org）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "访谈单位编码，主办部门编码，部门信息表（sys_org）不能为空")
    private String deptCode;

    @Schema(description = "访谈单位名称，与部门编码同步，部门信息表（sys_org）", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "访谈单位名称，与部门编码同步，部门信息表（sys_org）不能为空")
    private String deptName;

    @Schema(description = "嘉宾信息，嘉宾姓名、职务、简介，多个用逗号分隔", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "嘉宾信息，嘉宾姓名、职务、简介，多个用逗号分隔不能为空")
    private String guestInfo;

    @Schema(description = "访谈时间，访谈开始时间，格式“yyyy-MM-dd HH:mm”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "访谈时间，访谈开始时间，格式“yyyy-MM-dd HH:mm”不能为空")
    private LocalDateTime interviewTime;

    @Schema(description = "访谈时长，访谈预计时长，单位：分钟，如60=1小时", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "访谈时长，访谈预计时长，单位：分钟，如60=1小时不能为空")
    private Integer interviewEndure;

    @Schema(description = "访谈状态：1=未开始，2=进行中，3=已结束，4=实录公示，流程状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "访谈状态：1=未开始，2=进行中，3=已结束，4=实录公示，流程状态不能为空")
    private Integer interviewStatus;

    @Schema(description = "提问总数，市民提问累计数量，默认0", requiredMode = Schema.RequiredMode.REQUIRED, example = "31598")
    @NotNull(message = "提问总数，市民提问累计数量，默认0不能为空")
    private Integer questionCount;

    @Schema(description = "回复总数，嘉宾回复累计数量，默认0", requiredMode = Schema.RequiredMode.REQUIRED, example = "20760")
    @NotNull(message = "回复总数，嘉宾回复累计数量，默认0不能为空")
    private Integer replyCount;

    @Schema(description = "实录内容，访谈后整理的问答实录，支持富文本")
    private String recContent;

    @Schema(description = "实录发布时间，实录公示的时间")
    private LocalDateTime recPublishTime;

    @Schema(description = "发布人ID，发布访谈的人员ID，用户表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "5420")
    @NotNull(message = "发布人ID，发布访谈的人员ID，用户表（sys_user）不能为空")
    private Long publishUserId;

    @Schema(description = "发布人姓名，与发布人ID同步，用户表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "发布人姓名，与发布人ID同步，用户表（sys_user）不能为空")
    private String publishUserName;

    @Schema(description = "备注，补充说明，如“访谈将在抖音同步直播”", example = "你说的对")
    private String remark;

    @Schema(description = "分类扩展字段1，预留，标识访谈形式，如纯文字/视频")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，标识访谈平台，如官网/APP")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储直播链接")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储实录下载地址")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}