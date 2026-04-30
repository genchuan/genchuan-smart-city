package cn.iocoder.yudao.module.studentmgmt.controller.admin.checkin.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 报到管理分页 Request VO")
@Data
public class CheckInPageReqVO extends PageParam {

    @Schema(description = "学生 ID", example = "8524")
    private Long studentId;

    @Schema(description = "中考成绩")
    private BigDecimal examScore;

    @Schema(description = "补充信息")
    private String supplyInfo;

    @Schema(description = "报到确认时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] confirmTime;

    @Schema(description = "审核人")
    private String auditUser;

    @Schema(description = "审核时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] auditTime;

    @Schema(description = "账号创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] accountCreateTime;

    @Schema(description = "账号状态：未创建/已创建", example = "2")
    private String accountStatus;

    @Schema(description = "状态：待确认/待审核/已报到", example = "1")
    private String status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}