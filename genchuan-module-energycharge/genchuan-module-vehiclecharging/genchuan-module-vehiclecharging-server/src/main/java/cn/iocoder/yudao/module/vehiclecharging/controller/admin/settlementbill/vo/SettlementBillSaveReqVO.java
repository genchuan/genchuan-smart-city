package cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "汽车充电 - 结算单新增/修改 Request VO")
@Data
public class SettlementBillSaveReqVO {

    @Schema(description = "[主键ID] 结算单唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "6425")
    private Long id;

    @Schema(description = "[结算单编号] 结算单唯一编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[结算单编号] 结算单唯一编号不能为空")
    private String billCode;

    @Schema(description = "[合作方] 合作方名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[合作方] 合作方名称不能为空")
    private String cooperator;

    @Schema(description = "[结算周期] 结算周期描述", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[结算周期] 结算周期描述不能为空")
    private String settlementCycle;

    @Schema(description = "[结算金额] 总结算金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[结算金额] 总结算金额不能为空")
    private BigDecimal settlementAmount;

    @Schema(description = "[分账金额] 分账结算金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[分账金额] 分账结算金额不能为空")
    private BigDecimal sharingAmount;

    @Schema(description = "[结算状态] 如:待审核/审核通过/结算中/已完成/已驳回", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[结算状态] 如:待审核/审核通过/结算中/已完成/已驳回不能为空")
    private String billStatus;

    @Schema(description = "[审核人员] 审核人姓名/账号")
    private String auditUser;

    @Schema(description = "[审核时间] 审核操作时间")
    private LocalDateTime auditTime;

    @Schema(description = "[审核备注] 审核补充说明", example = "随便")
    private String auditRemark;

    @Schema(description = "[结算时间] 实际结算时间")
    private LocalDateTime settlementTime;

    @Schema(description = "[结算渠道] 结算支付渠道")
    private String settlementChannel;

    @Schema(description = "[备注] 结算单补充说明", example = "随便")
    private String remark;

    @Schema(description = "[备用字段1] 备用扩展字段")
    private String reserve1;

    @Schema(description = "[备用字段2] 备用扩展字段")
    private String reserve2;

}
