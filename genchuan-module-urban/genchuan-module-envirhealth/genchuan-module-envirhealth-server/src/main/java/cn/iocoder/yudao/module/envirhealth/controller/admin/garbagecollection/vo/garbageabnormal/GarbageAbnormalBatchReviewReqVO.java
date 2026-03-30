package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Schema(description = "环境卫生管理 - 垃圾异常记录批量复核 Request VO")
@Data
public class GarbageAbnormalBatchReviewReqVO {

    @Schema(description = "异常记录ID列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "异常记录ID列表不能为空")
    private List<Long> ids;

    @Schema(description = "复核状态：通过/退回", requiredMode = Schema.RequiredMode.REQUIRED, example = "通过")
    @NotNull(message = "复核状态不能为空")
    private String reviewStatus;

    @Schema(description = "复核意见", example = "整改通过")
    private String reviewDesc;
}