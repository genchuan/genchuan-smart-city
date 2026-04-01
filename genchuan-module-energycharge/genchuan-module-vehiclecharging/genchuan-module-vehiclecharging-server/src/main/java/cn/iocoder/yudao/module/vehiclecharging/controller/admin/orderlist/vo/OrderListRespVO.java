package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderlist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 订单列表 Response VO")
@Data
@ExcelIgnoreUnannotated
public class OrderListRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "订单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("订单编号")
    private String orderCode;

    @Schema(description = "用户ID")
    @ExcelProperty("用户ID")
    private String userId;

    @Schema(description = "车牌号")
    @ExcelProperty("车牌号")
    private String plateNo;

    @Schema(description = "充电桩编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("充电桩编号")
    private String pileCode;

    @Schema(description = "充电时长")
    @ExcelProperty("充电时长")
    private Integer chargeTime;

    @Schema(description = "充电量")
    @ExcelProperty("充电量")
    private BigDecimal chargeAmount;

    @Schema(description = "充电金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("充电金额")
    private BigDecimal chargeMoney;

    @Schema(description = "支付状态：未支付/已支付", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("支付状态：未支付/已支付")
    private String payStatus;

    @Schema(description = "订单状态：待支付/已支付/充电中/已完成/已取消", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("订单状态：待支付/已支付/充电中/已完成/已取消")
    private String orderStatus;

    @Schema(description = "支付方式")
    @ExcelProperty("支付方式")
    private String payType;

    @Schema(description = "终止充电原因")
    @ExcelProperty("终止充电原因")
    private String stopReason;

    @Schema(description = "评价")
    @ExcelProperty("评价")
    private String evaluate;

    @Schema(description = "评价时间")
    @ExcelProperty("评价时间")
    private LocalDateTime evaluateTime;

    @Schema(description = "取消时间")
    @ExcelProperty("取消时间")
    private LocalDateTime cancelTime;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}