package cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 结算单分页 Request VO")
@Data
public class SettlementBillPageReqVO extends PageParam {

    @Schema(description = "[结算单编号] 结算单唯一编号")
    private String billCode;

    @Schema(description = "[合作方] 合作方名称")
    private String cooperator;

    @Schema(description = "[结算周期] 结算周期描述")
    private String settlementCycle;

    @Schema(description = "[结算金额] 总结算金额")
    private BigDecimal settlementAmount;

    @Schema(description = "[分账金额] 分账结算金额")
    private BigDecimal sharingAmount;

    @Schema(description = "[结算状态] 如:待审核/审核通过/结算中/已完成/已驳回", example = "2")
    private String billStatus;

    @Schema(description = "[审核人员] 审核人姓名/账号")
    private String auditUser;

    @Schema(description = "[审核时间] 审核操作时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] auditTime;

    @Schema(description = "[审核备注] 审核补充说明", example = "随便")
    private String auditRemark;

    @Schema(description = "[结算时间] 实际结算时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] settlementTime;

    @Schema(description = "[结算渠道] 结算支付渠道")
    private String settlementChannel;

    @Schema(description = "[备注] 结算单补充说明", example = "随便")
    private String remark;

    @Schema(description = "[备用字段1] 备用扩展字段")
    private String reserve1;

    @Schema(description = "[备用字段2] 备用扩展字段")
    private String reserve2;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
