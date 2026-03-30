package cn.iocoder.yudao.module.waterdetection.controller.admin.samplingfrequency.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 采样频率设置新增/修改 Request VO")
@Data
public class SamplingFrequencySaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "采样点编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "采样点编号不能为空")
    private String pointCode;

    @Schema(description = "指标名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "指标名称不能为空")
    private String indicatorName;

    @Schema(description = "采样频率(次/月/季)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "采样频率(次/月/季)不能为空")
    private String frequency;

    @Schema(description = "执行周期")
    private String executionCycle;

    @Schema(description = "特殊时段(如汛期)调整规则")
    private String specialPeriodRule;

}