package cn.iocoder.yudao.module.park.controller.admin.park.resource.parkinglotinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 停车场信息管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkingLotInfoRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14847")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "停车场ID", example = "14750")
    @ExcelProperty("停车场ID")
    private String lotId;

    @Schema(description = "停车场名称", example = "王五")
    @ExcelProperty("停车场名称")
    private String lotName;

    @Schema(description = "车场类型", example = "2")
    @ExcelProperty("车场类型")
    private String lotType;

    @Schema(description = "所属区域")
    @ExcelProperty("所属区域")
    private String region;

    @Schema(description = "总车位数")
    @ExcelProperty("总车位数")
    private Integer totalSpaces;

    @Schema(description = "可用车位数")
    @ExcelProperty("可用车位数")
    private Integer availableSpaces;

    @Schema(description = "车场状态", example = "1")
    @ExcelProperty("车场状态")
    private String lotStatus;

    @Schema(description = "收费标准")
    @ExcelProperty("收费标准")
    private String feeStandard;

    @Schema(description = "营业时间")
    @ExcelProperty("营业时间")
    private String businessHours;

    @Schema(description = "运营商户")
    @ExcelProperty("运营商户")
    private String operator;

    @Schema(description = "联系人")
    @ExcelProperty("联系人")
    private String contactPerson;

    @Schema(description = "联系电话")
    @ExcelProperty("联系电话")
    private String contactPhone;

    @Schema(description = "所属行政区划")
    @ExcelProperty("所属行政区划")
    private String areaCode;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}