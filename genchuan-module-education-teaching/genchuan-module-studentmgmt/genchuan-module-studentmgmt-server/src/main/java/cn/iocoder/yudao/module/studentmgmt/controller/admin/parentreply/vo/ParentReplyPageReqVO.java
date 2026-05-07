package cn.iocoder.yudao.module.studentmgmt.controller.admin.parentreply.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 家长回复分页 Request VO")
@Data
public class ParentReplyPageReqVO extends PageParam {

    @Schema(description = "关联沟通消息ID", example = "12168")
    private Long communicateId;

    @Schema(description = "学生ID", example = "128")
    private Long studentId;

    @Schema(description = "学生姓名", example = "张三")
    private String studentName;

    @Schema(description = "家长回复内容")
    private String parentReplyContent;

    @Schema(description = "家长回复时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] parentReplyTime;

    @Schema(description = "老师回复内容")
    private String teacherReplyContent;

    @Schema(description = "老师回复时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] teacherReplyTime;

    @Schema(description = "阅读状态：未读/已读", example = "2")
    private String readStatus;

    @Schema(description = "回复状态：未回复/已回复", example = "2")
    private String replyStatus;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}