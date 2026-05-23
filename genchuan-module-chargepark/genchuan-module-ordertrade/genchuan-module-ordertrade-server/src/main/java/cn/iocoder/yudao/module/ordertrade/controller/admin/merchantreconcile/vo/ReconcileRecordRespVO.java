package cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.ordertrade.enums.ReconcileRecordStatusEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 对账记录 Response VO")
@Data
public class ReconcileRecordRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "所属对账单ID")
    @ExcelProperty("所属对账单ID")
    private Long billId;

    @Schema(description = "状态：normal/abnormal")
    @ExcelProperty(value = "状态", converter = EnumExcelConverter.class)
    @EnumFormat(ReconcileRecordStatusEnum.class)
    private String status;

    @Schema(description = "状态更新时间")
    @ExcelProperty("状态更新时间")
    private LocalDateTime statusUpdateTime;

    @Schema(description = "异常原因")
    @ExcelProperty("异常原因")
    private String errorReason;

    @Schema(description = "核查人ID")
    @ExcelProperty("核查人ID")
    private Long checkerId;

    @Schema(description = "核查时间")
    @ExcelProperty("核查时间")
    private LocalDateTime checkTime;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;
}
