package cn.iocoder.yudao.module.park.controller.admin.park.resource.inputcar.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 泊位录入车辆 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkInputCarRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25588")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "车场ID", example = "1")
    @ExcelProperty("车场ID")
    private String parkId;

    @Schema(description = "目标泊位号")
    @ExcelProperty("目标泊位号")
    private String targetBerthNo;

    @Schema(description = "车牌号码")
    @ExcelProperty("车牌号码")
    private String carNumber;

    @Schema(description = "车辆类型", example = "2")
    @ExcelProperty("车辆类型")
    private String carType;

    @Schema(description = "车牌颜色")
    @ExcelProperty("车牌颜色")
    private String plateColor;

    @Schema(description = "停车状态", example = "1")
    @ExcelProperty("停车状态")
    private String parkingStatus;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "入场时间")
    @ExcelProperty("入场时间")
    private LocalDateTime entryTime;

    @Schema(description = "出场时间")
    @ExcelProperty("出场时间")
    private LocalDateTime exitTime;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}