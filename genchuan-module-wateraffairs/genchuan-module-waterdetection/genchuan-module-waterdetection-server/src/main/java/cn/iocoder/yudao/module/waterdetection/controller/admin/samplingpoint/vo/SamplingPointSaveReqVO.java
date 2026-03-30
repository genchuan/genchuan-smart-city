package cn.iocoder.yudao.module.waterdetection.controller.admin.samplingpoint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 采样点规划新增/修改 Request VO")
@Data
public class SamplingPointSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "采样点编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "采样点编号不能为空")
    private String pointCode;

    @Schema(description = "经度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "经度不能为空")
    private Double longitude;

    @Schema(description = "纬度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "纬度不能为空")
    private Double latitude;

    @Schema(description = "类型(水源/水厂/管网/末梢)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "类型(水源/水厂/管网/末梢)不能为空")
    private String pointType;

    @Schema(description = "覆盖人口")
    private Double coveredPopulation;

    @Schema(description = "周边环境描述")
    private String surroundingDesc;

    @Schema(description = "规划依据")
    private String planningBasis;

}