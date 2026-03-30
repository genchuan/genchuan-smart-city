package cn.iocoder.yudao.module.facility.controller.admin.workorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 工单新增/修改 Request VO")
@Data
public class BatchRemindReqVO {
    @Schema(description = "[工单ID列表]", requiredMode = Schema.RequiredMode.REQUIRED, example = "[1,2]")
    @NotEmpty(message = "工单id列表不能为空")
    private List<Long> idList;
}
