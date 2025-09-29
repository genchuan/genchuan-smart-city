package cn.iocoder.yudao.module.gc.controller.admin.alarminformation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 预警信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AlarmInformationRespVO {

    @Schema(description = "预警ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28999")
    @ExcelProperty("预警ID")
    private String alarmId;

    @Schema(description = "预警编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预警编号")
    private String alarmCode;

    @Schema(description = "风险类型ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17761")
    @ExcelProperty("风险类型ID")
    private String riskTypeId;

    @Schema(description = "风险类型名称", example = "张三")
    @ExcelProperty("风险类型名称")
    private String riskTypeName;

    @Schema(description = "预警等级", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预警等级")
    private String alarmLevel;

    @Schema(description = "所属分域ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23037")
    @ExcelProperty("所属分域ID")
    private String domainId;

    @Schema(description = "所属分域名称", example = "赵六")
    @ExcelProperty("所属分域名称")
    private String domainName;

    @Schema(description = "发生区域", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发生区域")
    private String occurRegion;

    @Schema(description = "GPS坐标")
    @ExcelProperty("GPS坐标")
    private String gpsCoordinate;

    @Schema(description = "触发时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("触发时间")
    private LocalDateTime triggerTime;

    @Schema(description = "预警状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("预警状态")
    private String alarmStatus;

    @Schema(description = "触发原因", requiredMode = Schema.RequiredMode.REQUIRED, example = "不好")
    @ExcelProperty("触发原因")
    private String triggerReason;

    @Schema(description = "关联指标ID", example = "13170")
    @ExcelProperty("关联指标ID")
    private String indicatorId;

    @Schema(description = "处置责任人ID", example = "30406")
    @ExcelProperty("处置责任人ID")
    private String handlerId;

    @Schema(description = "处置责任人姓名", example = "赵六")
    @ExcelProperty("处置责任人姓名")
    private String handlerName;

    @Schema(description = "最后更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("最后更新时间")
    private LocalDateTime lastUpdateTime;

}