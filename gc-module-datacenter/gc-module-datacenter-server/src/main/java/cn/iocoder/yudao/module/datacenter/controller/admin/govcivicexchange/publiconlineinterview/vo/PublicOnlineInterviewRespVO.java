package cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publiconlineinterview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 在线访谈 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PublicOnlineInterviewRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29155")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "访谈ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "21965")
    @ExcelProperty("访谈ID，唯一编码，UUID生成")
    private String interviewId;

    @Schema(description = "访谈主题，访谈核心主题，如“社保新政在线访谈”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("访谈主题，访谈核心主题，如“社保新政在线访谈”")
    private String interviewTopic;

    @Schema(description = "访谈单位编码，主办部门编码，部门信息表（sys_org）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("访谈单位编码，主办部门编码，部门信息表（sys_org）")
    private String deptCode;

    @Schema(description = "访谈单位名称，与部门编码同步，部门信息表（sys_org）", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("访谈单位名称，与部门编码同步，部门信息表（sys_org）")
    private String deptName;

    @Schema(description = "嘉宾信息，嘉宾姓名、职务、简介，多个用逗号分隔", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("嘉宾信息，嘉宾姓名、职务、简介，多个用逗号分隔")
    private String guestInfo;

    @Schema(description = "访谈时间，访谈开始时间，格式“yyyy-MM-dd HH:mm”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("访谈时间，访谈开始时间，格式“yyyy-MM-dd HH:mm”")
    private LocalDateTime interviewTime;

    @Schema(description = "访谈时长，访谈预计时长，单位：分钟，如60=1小时", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("访谈时长，访谈预计时长，单位：分钟，如60=1小时")
    private Integer interviewEndure;

    @Schema(description = "访谈状态：1=未开始，2=进行中，3=已结束，4=实录公示，流程状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("访谈状态：1=未开始，2=进行中，3=已结束，4=实录公示，流程状态")
    private Integer interviewStatus;

    @Schema(description = "提问总数，市民提问累计数量，默认0", requiredMode = Schema.RequiredMode.REQUIRED, example = "31598")
    @ExcelProperty("提问总数，市民提问累计数量，默认0")
    private Integer questionCount;

    @Schema(description = "回复总数，嘉宾回复累计数量，默认0", requiredMode = Schema.RequiredMode.REQUIRED, example = "20760")
    @ExcelProperty("回复总数，嘉宾回复累计数量，默认0")
    private Integer replyCount;

    @Schema(description = "实录内容，访谈后整理的问答实录，支持富文本")
    @ExcelProperty("实录内容，访谈后整理的问答实录，支持富文本")
    private String recContent;

    @Schema(description = "实录发布时间，实录公示的时间")
    @ExcelProperty("实录发布时间，实录公示的时间")
    private LocalDateTime recPublishTime;

    @Schema(description = "发布人ID，发布访谈的人员ID，用户表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "5420")
    @ExcelProperty("发布人ID，发布访谈的人员ID，用户表（sys_user）")
    private Long publishUserId;

    @Schema(description = "发布人姓名，与发布人ID同步，用户表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("发布人姓名，与发布人ID同步，用户表（sys_user）")
    private String publishUserName;

    @Schema(description = "备注，补充说明，如“访谈将在抖音同步直播”", example = "你说的对")
    @ExcelProperty("备注，补充说明，如“访谈将在抖音同步直播”")
    private String remark;

    @Schema(description = "分类扩展字段1，预留，标识访谈形式，如纯文字/视频")
    @ExcelProperty("分类扩展字段1，预留，标识访谈形式，如纯文字/视频")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，标识访谈平台，如官网/APP")
    @ExcelProperty("分类扩展字段2，预留，标识访谈平台，如官网/APP")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储直播链接")
    @ExcelProperty("通用扩展字段1，预留，存储直播链接")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储实录下载地址")
    @ExcelProperty("通用扩展字段2，预留，存储实录下载地址")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}