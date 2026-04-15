package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 追缴跟踪新增/修改 Request VO")
@Data
public class CollectTrackSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "逃费记录ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "逃费记录不能为空")
    private Long recordId;

    @Schema(description = "追缴方式：sms/phone/visit", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "追缴方式不能为空")
    private String way;

    @Schema(description = "推送时间")
    private LocalDateTime pushTime;

    @Schema(description = "状态：pending/collecting/completed")
    private String status;

    @Schema(description = "执行人ID")
    private Long handlerId;

    @Schema(description = "备注")
    private String remark;
}
