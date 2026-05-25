package cn.iocoder.yudao.module.inspectop.controller.admin.shiftapply.vo;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "巡查巡检 - 换班申请分页 Request VO")
@Data
public class ShiftApplyPageReqVO extends PageParam {

    @Schema(description = "申请人ID")
    private Long applyUserId;

    @Schema(description = "换班对象ID")
    private Long targetUserId;

    @Schema(description = "原日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] oldDate;

    @Schema(description = "新日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] newDate;

    @Schema(description = "申请状态")
    private String status;

    @Schema(description = "审核人ID")
    private Long auditUserId;

    @Schema(description = "审核时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] auditTime;

    @Schema(description = "生效时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] effectTime;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "趋势过滤日期 (格式: yyyy-MM-dd)，用于按创建日期过滤")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // 注意：此处格式与前端传入的字符串匹配
    private LocalDate trendTime; // 使用 LocalDate 接收，因为它只包含年月日

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}