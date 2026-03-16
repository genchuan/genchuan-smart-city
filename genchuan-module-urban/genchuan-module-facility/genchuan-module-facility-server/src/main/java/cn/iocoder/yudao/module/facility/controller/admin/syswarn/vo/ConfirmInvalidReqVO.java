package cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 确认有效 VO")
@Data
public class ConfirmInvalidReqVO {
    @Schema(description = "[主键ID] 主键，预警记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "[主键ID] 不能为空")
//    @Schema(hidden = true)
    private Long id;

//    @Schema(description = "[预警状态] 如：待处置/有效待派单/已派单/超时/无效已归档", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
//    @NotEmpty(message = "[预警状态] 如：待处置/有效待派单/已派单/超时/无效已归档不能为空")
//    private String status;


    @Schema(description = "[无效原因] 如设备故障/数据波动/人为误触等", example = "设备故障")
    @NotEmpty(message = "[无效原因] 不能为空")
    private String invalidReason;

}
