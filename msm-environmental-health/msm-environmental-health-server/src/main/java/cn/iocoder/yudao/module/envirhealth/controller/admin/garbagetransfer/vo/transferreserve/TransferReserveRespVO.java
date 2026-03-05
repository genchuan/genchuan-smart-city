package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferreserve;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 进站预约 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TransferReserveRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27300")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "预约主键（UUID）", example = "15220")
    @ExcelProperty("预约主键（UUID）")
    private String reserveId;

    @Schema(description = "关联sys_vehicle.id", example = "1744")
    @ExcelProperty("关联sys_vehicle.id")
    private String vehicleId;

    @Schema(description = "关联sys_garbage_type.id", example = "9608")
    @ExcelProperty("关联sys_garbage_type.id")
    private String garbageTypeId;

    @Schema(description = "预计进站时间")
    @ExcelProperty("预计进站时间")
    private LocalDateTime expectedTime;

    @Schema(description = "垃圾重量（单位：吨）")
    @ExcelProperty("垃圾重量（单位：吨）")
    private BigDecimal garbageWeight;

    @Schema(description = "关联sys_area.area_code")
    @ExcelProperty("关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "预约状态：待排序/已排序/已进站", example = "1")
    @ExcelProperty("预约状态：待排序/已排序/已进站")
    private String reserveStatus;

    @Schema(description = "排序序号")
    @ExcelProperty("排序序号")
    private Integer sortNo;

    @Schema(description = "创建时间（业务字段）")
    @ExcelProperty("创建时间（业务字段）")
    private LocalDateTime abnormalCreateTime;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("关联sys_user.id")
    private String handleBy;

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