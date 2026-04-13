package cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "汽车充电 - 结算单 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SettlementBillRespVO {

    @Schema(description = "[主键ID] 结算单唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "6425")
    @ExcelProperty("[主键ID] 结算单唯一标识")
    private Long id;

    @Schema(description = "[结算单编号] 结算单唯一编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[结算单编号] 结算单唯一编号")
    private String billCode;

    @Schema(description = "[合作方] 合作方名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[合作方] 合作方名称")
    private String cooperator;

    @Schema(description = "[结算周期] 结算周期描述", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[结算周期] 结算周期描述")
    private String settlementCycle;

    @Schema(description = "[结算金额] 总结算金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[结算金额] 总结算金额")
    private BigDecimal settlementAmount;

    @Schema(description = "[分账金额] 分账结算金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[分账金额] 分账结算金额")
    private BigDecimal sharingAmount;

    @Schema(description = "[结算状态] 如:待审核/审核通过/结算中/已完成/已驳回", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("[结算状态] 如:待审核/审核通过/结算中/已完成/已驳回")
    private String billStatus;

    @Schema(description = "[审核人员] 审核人姓名/账号")
    @ExcelProperty("[审核人员] 审核人姓名/账号")
    private String auditUser;

    @Schema(description = "[审核时间] 审核操作时间")
    @ExcelProperty("[审核时间] 审核操作时间")
    private LocalDateTime auditTime;

    @Schema(description = "[审核备注] 审核补充说明", example = "随便")
    @ExcelProperty("[审核备注] 审核补充说明")
    private String auditRemark;

    @Schema(description = "[结算时间] 实际结算时间")
    @ExcelProperty("[结算时间] 实际结算时间")
    private LocalDateTime settlementTime;

    @Schema(description = "[结算渠道] 结算支付渠道")
    @ExcelProperty("[结算渠道] 结算支付渠道")
    private String settlementChannel;

    @Schema(description = "[备注] 结算单补充说明", example = "随便")
    @ExcelProperty("[备注] 结算单补充说明")
    private String remark;

    @Schema(description = "[备用字段1] 备用扩展字段")
    @ExcelProperty("[备用字段1] 备用扩展字段")
    private String reserve1;

    @Schema(description = "[备用字段2] 备用扩展字段")
    @ExcelProperty("[备用字段2] 备用扩展字段")
    private String reserve2;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

}
