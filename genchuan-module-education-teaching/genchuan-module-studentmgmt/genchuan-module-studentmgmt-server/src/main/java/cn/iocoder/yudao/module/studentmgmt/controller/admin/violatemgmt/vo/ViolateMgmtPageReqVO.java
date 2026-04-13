package cn.iocoder.yudao.module.studentmgmt.controller.admin.violatemgmt.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 违纪管理分页 Request VO")
@Data
public class ViolateMgmtPageReqVO extends PageParam {

    @Schema(description = "学生 ID", example = "27164")
    private Long studentId;

    @Schema(description = "违纪类型：仪容仪表/行为违规/其他", example = "1")
    private String violateType;

    @Schema(description = "处分类型：警告/记过/留校察看/开除", example = "1")
    private String punishType;

    @Schema(description = "违纪时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] violateTime;

    @Schema(description = "违纪原因", example = "不香")
    private String violateReason;

    @Schema(description = "审批人")
    private String auditUser;

    @Schema(description = "审批时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] auditTime;

    @Schema(description = "家长推送时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] pushTime;

    @Schema(description = "预警时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] warnTime;

    @Schema(description = "状态：待审批/已执行/已预警", example = "1")
    private String status;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}