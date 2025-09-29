package cn.iocoder.yudao.module.gc.controller.admin.alarminformation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 预警信息新增/修改 Request VO")
@Data
public class AlarmInformationSaveReqVO {

    @Schema(description = "预警ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28999")
    private String alarmId;

    @Schema(description = "预警编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "预警编号不能为空")
    private String alarmCode;

    @Schema(description = "风险类型ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17761")
    @NotEmpty(message = "风险类型ID不能为空")
    private String riskTypeId;

    @Schema(description = "风险类型名称", example = "张三")
    private String riskTypeName;

    @Schema(description = "预警等级", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "预警等级不能为空")
    private String alarmLevel;

    @Schema(description = "所属分域ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23037")
    @NotEmpty(message = "所属分域ID不能为空")
    private String domainId;

    @Schema(description = "所属分域名称", example = "赵六")
    private String domainName;

    @Schema(description = "发生区域", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "发生区域不能为空")
    private String occurRegion;

    @Schema(description = "GPS坐标")
    private String gpsCoordinate;

    @Schema(description = "触发时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "触发时间不能为空")
    private LocalDateTime triggerTime;

    @Schema(description = "预警状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "预警状态不能为空")
    private String alarmStatus;

    @Schema(description = "触发原因", requiredMode = Schema.RequiredMode.REQUIRED, example = "不好")
    @NotEmpty(message = "触发原因不能为空")
    private String triggerReason;

    @Schema(description = "关联指标ID", example = "13170")
    private String indicatorId;

    @Schema(description = "处置责任人ID", example = "30406")
    private String handlerId;

    @Schema(description = "处置责任人姓名", example = "赵六")
    private String handlerName;

    @Schema(description = "最后更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "最后更新时间不能为空")
    private LocalDateTime lastUpdateTime;

}