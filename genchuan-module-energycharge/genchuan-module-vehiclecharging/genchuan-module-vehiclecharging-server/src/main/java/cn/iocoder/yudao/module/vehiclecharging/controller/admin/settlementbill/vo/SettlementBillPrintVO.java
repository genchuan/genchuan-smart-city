package cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "结算单打印 VO")
@Data
public class SettlementBillPrintVO {
    private String billCode;
    private String cooperator;
    private String settlementCycle;
    private BigDecimal settlementAmount;
    private BigDecimal sharingAmount;
    private String billStatus;
    private String auditUser;
    private LocalDateTime auditTime;
    private String auditRemark;
    private LocalDateTime settlementTime;
    private String settlementChannel;
    private String remark;
}
