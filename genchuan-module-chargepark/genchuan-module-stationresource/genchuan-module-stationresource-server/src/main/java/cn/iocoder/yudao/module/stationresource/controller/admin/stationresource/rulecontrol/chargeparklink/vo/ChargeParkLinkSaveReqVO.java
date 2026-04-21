package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 充停联动新增/修改 Request VO")
@Data
public class ChargeParkLinkSaveReqVO {

    @Schema(description = "[主键ID] 主键，自增", requiredMode = Schema.RequiredMode.REQUIRED, example = "7436")
    private Long id;

    @Schema(description = "[所属场站] 关联场站信息表 station_info", requiredMode = Schema.RequiredMode.REQUIRED, example = "6922")
    @NotNull(message = "[所属场站] 关联场站信息表 station_info不能为空")
    private Long stationId;

    @Schema(description = "[优惠类型] 如：停车减免/充电减免/费用合并", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[优惠类型] 如：停车减免/充电减免/费用合并不能为空")
    private String discountType;

    @Schema(description = "[优惠幅度] 单位 %", requiredMode = Schema.RequiredMode.REQUIRED, example = "26204")
    @NotNull(message = "[优惠幅度] 单位 %不能为空")
    private BigDecimal discount;

    @Schema(description = "[适用车型] 如：小型车/中型车/大型车/新能源车", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[适用车型] 如：小型车/中型车/大型车/新能源车不能为空")
    private String carType;

    @Schema(description = "[状态] 如：待生效/已生效/已禁用", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "[状态] 如：待生效/已生效/已禁用不能为空")
    private String status;

    @Schema(description = "[审核时间] 审核通过的时间")
    private LocalDateTime auditTime;

    @Schema(description = "[审核人] 关联芋道用户表 system_user", example = "23331")
    private Long auditUserId;

    @Schema(description = "[今日订单量] 当日订单数量", example = "7681")
    private Integer todayOrderCount;

    @Schema(description = "[今日营收] 当日营收金额")
    private BigDecimal todayIncome;

    @Schema(description = "[支付率] 支付成功率，单位 %")
    private BigDecimal payRate;

    @Schema(description = "[备注] 扩展说明", example = "你猜")
    private String remark;

    @Schema(description = "[备用字段1] 预留扩展")
    private String reserve1;

    @Schema(description = "[备用字段2] 预留扩展")
    private String reserve2;

}
