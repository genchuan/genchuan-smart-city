package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkwo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 停车订单 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkWoRespVO {

    @Schema(description = "主键ID，唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "22934")
    @ExcelProperty("主键ID，唯一标识")
    private Long id;

//    @Schema(description = "订单ID，唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "WO20251226001")
//    @ExcelProperty("订单ID，唯一标识")
//    private String woId;

    @Schema(description = "订单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "NO20251226001")
    @ExcelProperty("订单编号")
    private String woNo;

    @Schema(description = "停车场名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "学校停车场")
    @ExcelProperty("停车场名称")
    private String parkName;

    @Schema(description = "停车时长（分钟）", requiredMode = Schema.RequiredMode.REQUIRED, example = "120")
    @ExcelProperty("停车时长（分钟）")
    private Integer parkEndure;

    @Schema(description = "应收金额", requiredMode = Schema.RequiredMode.REQUIRED, example = "50.00")
    @ExcelProperty("应收金额")
    private BigDecimal receivableAmount;

    @Schema(description = "欠费原因说明", example = "忘记缴纳")
    @ExcelProperty("欠费原因说明")
    private String arrearsReason;

    @Schema(description = "创建时间", example = "2025-12-26T10:00:00")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", example = "2025-12-26T11:00:00")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "分类扩展字段1", example = "C类")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2", example = "停车")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1", example = "备注信息1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2", example = "备用信息1")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

}
