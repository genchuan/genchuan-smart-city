package cn.iocoder.yudao.module.studentmgmt.controller.admin.leavehandle.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 离校办理分页 Request VO")
@Data
public class LeaveHandlePageReqVO extends PageParam {

    @Schema(description = "学生 ID", example = "25257")
    private Long studentId;

    @Schema(description = "离校时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] leaveTime;

    @Schema(description = "离校去处")
    private String leaveAddress;

    @Schema(description = "家长确认时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] parentConfirmTime;

    @Schema(description = "办理人")
    private String handleUser;

    @Schema(description = "办理时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] handleTime;

    @Schema(description = "退宿时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] checkoutTime;

    @Schema(description = "退宿状态：未退宿/已退宿", example = "1")
    private String checkoutStatus;

    @Schema(description = "办理完成率")
    private BigDecimal finishRate;

    @Schema(description = "状态：待确认/待办理/已离校", example = "2")
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