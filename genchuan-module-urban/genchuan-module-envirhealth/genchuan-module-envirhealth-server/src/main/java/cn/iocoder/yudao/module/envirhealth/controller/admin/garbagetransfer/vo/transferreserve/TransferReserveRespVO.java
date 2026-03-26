package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferreserve;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "环境卫生管理 - 进站预约 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TransferReserveRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "预约主键（UUID）", example = "15220")
    @ExcelProperty("预约主键")
    private String reserveId;

    @Schema(description = "关联sys_vehicle.id", example = "1744")
    @ExcelProperty("车牌号")
    private String vehicleId;

    @Schema(description = "关联sys_garbage_type.id", example = "9608")
    @ExcelProperty("垃圾类型编号")
    private String garbageTypeId;

    @Schema(description = "预计进站时间")
    @ExcelProperty("预计进站时间")
    private LocalDateTime expectedTime;

    @Schema(description = "垃圾重量（单位：吨）")
    @ExcelProperty("垃圾重量")
    private BigDecimal garbageWeight;

    @Schema(description = "关联sys_area.area_code")
    @ExcelProperty("区域编码")
    private String areaCode;

    @Schema(description = "预约状态：待排序/已排序/已进站", example = "1")
    @ExcelProperty("预约状态")
    private String reserveStatus;

    @Schema(description = "排序序号")
    @ExcelProperty("排序序号")
    private Integer sortNo;

    @Schema(description = "创建时间（业务字段）")
    @ExcelProperty("创建时间")
    private LocalDateTime abnormalCreateTime;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("处理人员")
    private String handleBy;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "转运站编号")
    @ExcelProperty("转运站编号")
    private String transferId;
}