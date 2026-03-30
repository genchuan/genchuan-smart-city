package cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.roadcleaning;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Schema(description = "环境卫生管理 - 道路清扫计划批量复核 Request VO")
@Data
public class RoadCleaningBatchReviewReqVO {

    @Schema(description = "选中计划的清扫ID列表", required = true)
    @NotEmpty(message = "请至少选择一个清扫计划")
    private List<Long> ids;

    @Schema(description = "调整值", required = true)
    @NotNull(message = "请填写调整值")
    private String reviewValue;

    @Schema(description = "复核意见")
    private String reviewDesc;
}