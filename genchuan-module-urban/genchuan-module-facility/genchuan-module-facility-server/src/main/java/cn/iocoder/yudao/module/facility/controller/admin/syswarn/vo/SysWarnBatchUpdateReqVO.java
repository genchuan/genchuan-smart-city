package cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 批量修改 VO")
@Data
public class SysWarnBatchUpdateReqVO {
    @Schema(description = "[批量修改的预警id列表]", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[批量修改的预警id列表]")
//    @Schema(hidden = true)
    private List<Long> idList;

    @Schema(description = "[预警状态] 如：待处置/有效待派单/已派单/超时/无效已归档", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
//    @NotEmpty(message = "[预警状态] 如：待处置/有效待派单/已派单/超时/无效已归档不能为空")
    private String status;

    @Schema(description = "[派单状态] 如：未派单/已派单", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
//    @NotEmpty(message = "[派单状态] 如：未派单/已派单不能为空")
    private String assignStatus;

    @Schema(description = "[确认意见] 人工确认后的描述")
    private String confirmOpinion;

    @Schema(description = "[无效原因] 如设备故障/数据波动/人为误触等", example = "不喜欢")
    private String invalidReason;

    @Schema(description = "[处理建议] 系统或人工给出的处置建议")
    private String suggest;
}
