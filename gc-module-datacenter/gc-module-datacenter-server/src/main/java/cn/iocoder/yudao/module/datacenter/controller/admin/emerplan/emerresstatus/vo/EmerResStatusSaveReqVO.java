package cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerresstatus.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资源状态统计新增/修改 Request VO")
@Data
public class EmerResStatusSaveReqVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26787")
    private Long id;

    @Schema(description = "统计ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26864")
    @NotEmpty(message = "统计ID不能为空")
    private String statId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期不能为空")
    private String statCycle;

    @Schema(description = "统计时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计时间不能为空")
    private LocalDateTime statTime;

    @Schema(description = "资源类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "资源类型不能为空")
    private String resType;

    @Schema(description = "行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划代码不能为空")
    private String regionCode;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "行政区划名称不能为空")
    private String regionName;

    @Schema(description = "总数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "总数量不能为空")
    private Integer totalQuantity;

    @Schema(description = "可用数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "可用数量不能为空")
    private Integer availableQuantity;

    @Schema(description = "异常数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "异常数量不能为空")
    private Integer abnQuantity;

}