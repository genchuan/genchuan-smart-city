package cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 批量修改无效状态 VO")
@Data
public class SysWarnBatchConfirmValidReqVO {
    @Schema(description = "[批量修改的预警id列表]", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[批量修改的预警id列表]")
//    @Schema(hidden = true)
    private List<Long> idList;

//    @Schema(description = "[预警状态] 如：待处置/有效待派单/已派单/超时/无效已归档", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
////    @NotEmpty(message = "[预警状态] 如：待处置/有效待派单/已派单/超时/无效已归档不能为空")
//    private String status;
}
