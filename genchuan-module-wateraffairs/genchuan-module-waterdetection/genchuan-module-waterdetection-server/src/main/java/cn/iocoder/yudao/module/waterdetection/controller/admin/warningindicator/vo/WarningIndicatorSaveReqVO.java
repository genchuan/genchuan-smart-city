package cn.iocoder.yudao.module.waterdetection.controller.admin.warningindicator.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 预警指标配置新增/修改 Request VO")
@Data
public class WarningIndicatorSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "预警指标名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "预警指标名称不能为空")
    private String indicatorName;

    @Schema(description = "指标类型(水质/设备)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "指标类型(水质/设备)不能为空")
    private String indicatorType;

    @Schema(description = "关联监测点类型(水源/水厂/管网)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联监测点类型(水源/水厂/管网)不能为空")
    private String relatedPointType;

    @Schema(description = "数据来源(在线监测/人工检测)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "数据来源(在线监测/人工检测)不能为空")
    private String dataSource;

}