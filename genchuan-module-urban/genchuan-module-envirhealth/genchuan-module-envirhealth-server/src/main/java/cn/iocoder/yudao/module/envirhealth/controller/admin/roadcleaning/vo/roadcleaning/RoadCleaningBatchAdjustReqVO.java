package cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.roadcleaning;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Schema(description = "环境卫生管理 - 道路清扫计划批量调整 Request VO")
@Data
public class RoadCleaningBatchAdjustReqVO {

    @Schema(description = "选中计划的清扫ID列表", required = true)
    @NotEmpty(message = "请至少选择一个清扫计划")
    private List<Long> ids;

    @Schema(description = "调整维度：time_period-时段，frequency-频次，staff-人员", required = true)
    @NotNull(message = "请选择调整维度")
    private String adjustDimension;

    @Schema(description = "调整值（根据维度不同含义不同）", required = true)
    @NotNull(message = "请填写调整值")
    private String adjustValue;

    @Schema(description = "调整说明/复核意见（可选）")
    private String adjustRemark; // 可选
}