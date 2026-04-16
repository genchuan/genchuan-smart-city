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

    @Schema(description = "关联订单ID")
    @ExcelProperty("关联订单ID")
    private Long orderId;
    @Schema(description = "订单类型")
    @ExcelProperty("订单类型")
    private String orderType;
    @Schema(description = "异常类型")
    @ExcelProperty("异常类型")
    private String abnormalType;
    @Schema(description = "异常识别时间")
    @ExcelProperty("异常识别时间")
    private LocalDateTime identifyTime;
    @Schema(description = "处置状态")
    @ExcelProperty("处置状态")
    private String status;
    @Schema(description = "所属场站ID")
    @ExcelProperty("所属场站ID")
    private Long stationId;
    @Schema(description = "忽略理由")
    @ExcelProperty("忽略理由")
    private String ignoreReason;
    @Schema(description = "处置进度")
    @ExcelProperty("处置进度")
    private String processProgress;
    @Schema(description = "操作人ID")
    @ExcelProperty("操作人ID")
    private Long operatorId;

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
