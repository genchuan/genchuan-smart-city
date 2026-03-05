package cn.iocoder.yudao.module.industry.controller.admin.park.cardriveoutrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 车辆出场记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CarDriveoutRecordRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10131")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "停车编号（车辆在停车场唯一编号）", example = "28727")
    @ExcelProperty("停车编号（车辆在停车场唯一编号）")
    private String recordId;

    @Schema(description = "入口编号")
    @ExcelProperty("入口编号")
    private String entranceNo;

    @Schema(description = "入口名称", example = "李四")
    @ExcelProperty("入口名称")
    private String entranceName;

    @Schema(description = "车牌类型，参考附录", example = "2")
    @ExcelProperty("车牌类型，参考附录")
    private String plateType;

    @Schema(description = "车牌号")
    @ExcelProperty("车牌号")
    private String plateNumber;

    @Schema(description = "进场时间，格式yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("进场时间，格式yyyy-MM-dd HH:mm:ss")
    private LocalDateTime driveInTime;

    @Schema(description = "进场图片，图片URL地址或base64格式")
    @ExcelProperty("进场图片，图片URL地址或base64格式")
    private String driveInPhoto;

    @Schema(description = "出口编号")
    @ExcelProperty("出口编号")
    private String exitNo;

    @Schema(description = "出口名称", example = "王五")
    @ExcelProperty("出口名称")
    private String exitName;

    @Schema(description = "出场时间，格式yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("出场时间，格式yyyy-MM-dd HH:mm:ss")
    private LocalDateTime driveOutTime;

    @Schema(description = "出场图片，图片URL地址或base64格式")
    @ExcelProperty("出场图片，图片URL地址或base64格式")
    private String driveOutPhoto;

    @Schema(description = "空闲车位数")
    @ExcelProperty("空闲车位数")
    private Integer emptyPlot;

    @Schema(description = "收费员账号", example = "22144")
    @ExcelProperty("收费员账号")
    private String operatorId;

    @Schema(description = "收费员名称", example = "李四")
    @ExcelProperty("收费员名称")
    private String operatorName;

    @Schema(description = "应付金额，单位分")
    @ExcelProperty("应付金额，单位分")
    private BigDecimal shouldPay;

    @Schema(description = "实付金额，单位分")
    @ExcelProperty("实付金额，单位分")
    private BigDecimal actualPay;

    @Schema(description = "出场类型，参考附录", example = "1")
    @ExcelProperty("出场类型，参考附录")
    private String outType;

    @Schema(description = "免费原因或异常原因", example = "随便")
    @ExcelProperty("免费原因或异常原因")
    private String outRemark;

    @Schema(description = "支付方式，参考附录")
    @ExcelProperty("支付方式，参考附录")
    private String payMethod;

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