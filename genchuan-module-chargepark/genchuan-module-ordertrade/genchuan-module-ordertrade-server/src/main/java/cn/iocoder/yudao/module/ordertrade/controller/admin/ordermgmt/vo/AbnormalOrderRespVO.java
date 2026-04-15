package cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 异常订单 Response VO")
@Data
public class AbnormalOrderRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "订单ID")
    @ExcelProperty("订单ID")
    private Long orderId;

    @Schema(description = "异常原因")
    @ExcelProperty("异常原因")
    private String reason;

    @Schema(description = "发生时间")
    @ExcelProperty("发生时间")
    private LocalDateTime happenTime;

    @Schema(description = "状态")
    @ExcelProperty("状态")
    private String status;

    @Schema(description = "处置人ID")
    @ExcelProperty("处置人ID")
    private Long handlerId;

    @Schema(description = "处置时间")
    @ExcelProperty("处置时间")
    private LocalDateTime handleTime;

    @Schema(description = "处置结果")
    @ExcelProperty("处置结果")
    private String handleResult;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
