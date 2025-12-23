package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspectionstatistics.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 环卫考核统计结果新增/修改 Request VO")
@Data
public class InspectionStatisticsSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "考核日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "考核日期不能为空")
    private String inspectionDate;

    @Schema(description = "场所类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "场所类型不能为空")
    private String areaType;

    @Schema(description = "区域/地点名称")
    private String areaName;

    @Schema(description = "该场所总得分")
    private BigDecimal totalScore;

    @Schema(description = "该类型满分")
    private BigDecimal maxScore;

    @Schema(description = "权重")
    private BigDecimal weight;

    @Schema(description = "最终得分")
    private BigDecimal scoreWeighted;

    @Schema(description = "样本数")
    private Integer sampleCount;

    @Schema(description = "考核状态")
    private String inspectionStatus;

}