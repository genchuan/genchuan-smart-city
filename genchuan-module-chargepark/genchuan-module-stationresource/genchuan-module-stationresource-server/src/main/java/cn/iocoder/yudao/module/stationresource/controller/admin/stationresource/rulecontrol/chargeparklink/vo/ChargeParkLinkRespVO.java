package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 充停联动 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ChargeParkLinkRespVO {

    @Schema(description = "[主键ID] 主键，自增", requiredMode = Schema.RequiredMode.REQUIRED, example = "7436")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "[所属场站] 关联场站信息表 station_info", requiredMode = Schema.RequiredMode.REQUIRED, example = "6922")
    @ExcelProperty("所属场站")
    private Long stationId;

    @Schema(description = "[优惠类型] 如：停车减免/充电减免/费用合并", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("优惠类型")
    private String discountType;

    @Schema(description = "[优惠幅度] 单位 %", requiredMode = Schema.RequiredMode.REQUIRED, example = "26204")
    @ExcelProperty("优惠幅度")
    private BigDecimal discount;

    @Schema(description = "[适用车型] 如：小型车/中型车/大型车/新能源车", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("适用车型")
    private String carType;

    @Schema(description = "[状态] 如：待生效/已生效/已禁用", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("状态")
    private String status;

    @Schema(description = "[审核时间] 审核通过的时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "[审核人] 关联芋道用户表 system_user", example = "23331")
    @ExcelProperty("审核人")
    private Long auditUserId;

    @Schema(description = "[今日订单量] 当日订单数量", example = "7681")
    @ExcelProperty("今日订单量")
    private Integer todayOrderCount;

    @Schema(description = "[今日营收] 当日营收金额")
    @ExcelProperty("今日营收")
    private BigDecimal todayIncome;

    @Schema(description = "[支付率] 支付成功率，单位 %")
    @ExcelProperty("支付率")
    private BigDecimal payRate;

    @Schema(description = "[备注] 扩展说明", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "[备用字段1] 预留扩展")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "[备用字段2] 预留扩展")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "[创建者] 创建人账号/姓名")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "[更新者] 更新人账号/姓名")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[更新时间] 记录最后更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}
