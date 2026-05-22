package cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.oilmonitor.vo;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "巡查巡检 - 油车占位监测分页 Request VO")
@Data
public class OilMonitorPageReqVO extends PageParam {

    @Schema(description = "车位ID")
    private Long spaceId;

    @Schema(description = "场站ID")
    private Long stationId;

    // 【新增字段】支持按场站名称模糊查询
    @Schema(description = "场站名称")
    private String stationName;

    @Schema(description = "识别时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] identifyTime;

    // 【优化描述】明确过滤规则
    @Schema(description = "处置状态，模糊匹配")
    private String processStatus;

    @Schema(description = "处置人ID")
    private Long processUserId;

    @Schema(description = "处置时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] processTime;

    @Schema(description = "忽略理由")
    private String ignoreReason;

    @Schema(description = "处置进度")
    private Integer processProgress;

    @Schema(description = "经度")
    private BigDecimal longitude;

    @Schema(description = "纬度")
    private BigDecimal latitude;

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

    // 【新增字段】创建日期（年月日）
    @Schema(description = "创建日期（年月日）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY) // 使用年月日格式接收参数
    private LocalDate createDate;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}