package cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.vehicleaccess.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import cn.idev.excel.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 车辆通行 Response VO")
@Data
@ExcelIgnoreUnannotated
public class VehicleAccessRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "车牌号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车牌号")
    private String plateNo;

    @Schema(description = "车辆类型（小型车/大型车）")
    @ExcelProperty("车辆类型")
    private String vehicleType;

    @Schema(description = "停车场名称")
    @ExcelProperty("停车场名称")
    private String parkName;

    @Schema(description = "通行时间")
    @ExcelProperty("通行时间")
    private LocalDateTime accessTime;

    @Schema(description = "通行状态（进场中/出场中/已离场）")
    @ExcelProperty("通行状态")
    private String accessStatus;

    @Schema(description = "停车时长（分钟）")
    @ExcelProperty("停车时长")
    private Integer parkDuration;

    @Schema(description = "费用金额")
    @ExcelProperty("费用金额")
    private BigDecimal feeAmount;

    @Schema(description = "缴费状态（已缴费/未缴费）")
    @ExcelProperty("缴费状态")
    private String payStatus;

    @Schema(description = "操作人账号")
    @ExcelProperty("操作人账号")
    private String handleUser;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}
