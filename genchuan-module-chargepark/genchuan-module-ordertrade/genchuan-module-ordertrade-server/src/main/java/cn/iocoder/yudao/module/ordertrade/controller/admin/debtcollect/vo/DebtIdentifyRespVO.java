package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.ordertrade.enums.DebtIdentifyStatusEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 逃费识别 Response VO")
@Data
public class DebtIdentifyRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "识别编号")
    @ExcelProperty("识别编号")
    private String identifyNo;

    @Schema(description = "车牌")
    @ExcelProperty("车牌")
    private String plateNo;

    @Schema(description = "欠费金额")
    @ExcelProperty("欠费金额")
    private BigDecimal arrearAmount;

    @Schema(description = "识别时间")
    @ExcelProperty("识别时间")
    private LocalDateTime identifyTime;

    @Schema(description = "状态")
    @ExcelProperty(value = "状态", converter = EnumExcelConverter.class)
    @EnumFormat(DebtIdentifyStatusEnum.class)
    private String status;

    @Schema(description = "所属场站名称")
    @ExcelProperty("所属场站")
    private String stationName;

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
