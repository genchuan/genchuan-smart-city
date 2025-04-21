package cn.iocoder.yudao.module.smartcity.controller.admin.parkingfee.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 停车收费管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkingFeeRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "22566")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "车牌号")
    @ExcelProperty("车牌号")
    private String licensePlateNumber;

    @Schema(description = "入场时间")
    @ExcelProperty("入场时间")
    private LocalDateTime admissionTime;

    @Schema(description = "出场时间")
    @ExcelProperty("出场时间")
    private LocalDateTime exitTime;

    @Schema(description = "停车时长")
    @ExcelProperty("停车时长")
    private String parkingDuration;

    @Schema(description = "收费金额")
    @ExcelProperty("收费金额")
    private String feeAmount;

    @Schema(description = "缴费方式")
    @ExcelProperty("缴费方式")
    private String paymentMethod;

    @Schema(description = "实收金额")
    @ExcelProperty("实收金额")
    private String actualReceivedAmount;

    @Schema(description = "车位编号")
    @ExcelProperty("车位编号")
    private String parkingSpaceNumber;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}