package cn.iocoder.yudao.module.industry.controller.admin.park.cardriveinrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 车辆入场记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CarDriveinRecordRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2438")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "停车编号（车辆在停车场停车唯一编号）", example = "7627")
    @ExcelProperty("停车编号（车辆在停车场停车唯一编号）")
    private String recordId;

    @Schema(description = "入口编号")
    @ExcelProperty("入口编号")
    private String entranceNo;

    @Schema(description = "入口名称", example = "赵六")
    @ExcelProperty("入口名称")
    private String entranceName;

    @Schema(description = "车牌类型", example = "1")
    @ExcelProperty("车牌类型")
    private String plateType;

    @Schema(description = "车牌号")
    @ExcelProperty("车牌号")
    private String plateNumber;

    @Schema(description = "进场时间")
    @ExcelProperty("进场时间")
    private LocalDateTime driveInTime;

    @Schema(description = "进场图片，图片URL地址或base64格式")
    @ExcelProperty("进场图片，图片URL地址或base64格式")
    private String driveInPhoto;

    @Schema(description = "空闲车位数")
    @ExcelProperty("空闲车位数")
    private Integer emptyPlot;

    @Schema(description = "收费员账号", example = "25003")
    @ExcelProperty("收费员账号")
    private String operatorId;

    @Schema(description = "收费员名称", example = "李四")
    @ExcelProperty("收费员名称")
    private String operatorName;

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

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}