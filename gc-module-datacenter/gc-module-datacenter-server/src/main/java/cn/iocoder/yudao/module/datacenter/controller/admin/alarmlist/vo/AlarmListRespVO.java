package cn.iocoder.yudao.module.datacenter.controller.admin.alarmlist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 预警告警列 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AlarmListRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24973")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "预警ID（UUID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "30266")
    @ExcelProperty("预警ID（UUID）")
    private String alarmId;

    @Schema(description = "预警编号（AL+分域编码+8位流水号）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预警编号（AL+分域编码+8位流水号）")
    private String alarmCode;

    @Schema(description = "风险类型ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10366")
    @ExcelProperty("风险类型ID")
    private String riskTypeId;

    @Schema(description = "风险类型名称", example = "赵六")
    @ExcelProperty("风险类型名称")
    private String riskTypeName;

    @Schema(description = "预警等级（一般/较大/重大/特别重大）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预警等级（一般/较大/重大/特别重大）")
    private String alarmLevel;

    @Schema(description = "所属分域ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3752")
    @ExcelProperty("所属分域ID")
    private String domainId;

    @Schema(description = "所属分域名称", example = "赵六")
    @ExcelProperty("所属分域名称")
    private String domainName;

    @Schema(description = "发生区域", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发生区域")
    private String occurRegion;

    @Schema(description = "GPS坐标（经度,纬度）")
    @ExcelProperty("GPS坐标（经度,纬度）")
    private String gpsCoordinate;

    @Schema(description = "触发时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("触发时间")
    private LocalDateTime triggerTime;

    @Schema(description = "预警状态（待处置/处置中/已完成/已解除）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("预警状态（待处置/处置中/已完成/已解除）")
    private String alarmStatus;

    @Schema(description = "触发原因", requiredMode = Schema.RequiredMode.REQUIRED, example = "不香")
    @ExcelProperty("触发原因")
    private String triggerReason;

    @Schema(description = "关联指标ID", example = "9591")
    @ExcelProperty("关联指标ID")
    private String indicatorId;

    @Schema(description = "处置责任人ID", example = "24403")
    @ExcelProperty("处置责任人ID")
    private String handlerId;

    @Schema(description = "处置责任人姓名", example = "芋艿")
    @ExcelProperty("处置责任人姓名")
    private String handlerName;

    @Schema(description = "最后更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("最后更新时间")
    private LocalDateTime lastUpdateTime;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}