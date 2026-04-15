package cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 临时停车订单 Response VO")
@Data
public class TempParkOrderRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "主订单ID")
    @ExcelProperty("主订单ID")
    private Long orderId;

    @Schema(description = "车位ID")
    @ExcelProperty("车位ID")
    private Long spaceId;

    @Schema(description = "入场时间")
    @ExcelProperty("入场时间")
    private LocalDateTime inTime;

    @Schema(description = "离场时间")
    @ExcelProperty("离场时间")
    private LocalDateTime outTime;

    @Schema(description = "停车时长（小时）")
    @ExcelProperty("停车时长(h)")
    private BigDecimal parkHour;

    @Schema(description = "停车费用")
    @ExcelProperty("停车费用")
    private BigDecimal fee;

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
