package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import cn.idev.excel.annotation.ExcelIgnore;

@Schema(description = "管理后台 - 押金方案创建 Request VO")
@Data
public class DepositPlanCreateReqVO {

    @Schema(description = "所属场站ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "所属场站不能为空")
    private Long stationId;

    @Schema(description = "押金金额", requiredMode = Schema.RequiredMode.REQUIRED, example = "20.00")
    @NotNull(message = "押金金额不能为空")
    private BigDecimal depositAmount;

    @Schema(description = "适用场景", requiredMode = Schema.RequiredMode.REQUIRED, example = "预约停车")
    @NotEmpty(message = "适用场景不能为空")
    private String scene;

    @Schema(description = "备注", example = "预约停车押金方案")
    private String remark;

    @Schema(description = "备用字段1", example = "备用信息1")
    @ExcelIgnore
    private String reserve1;

    @Schema(description = "备用字段2", example = "备用信息2")
    @ExcelIgnore
    private String reserve2;
}
