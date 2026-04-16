package cn.iocoder.yudao.module.vehiclecharging.controller.admin.charginglot.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "汽车充电 - 充电车位 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ChargingLotRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "车位编号")
    @ExcelProperty("车位编号")
    private String lotCode;

    @Schema(description = "所属场站ID")
    @ExcelProperty("所属场站ID")
    private Long stationId;

    @Schema(description = "所属场站名称（模拟）")
    private String stationName;

    @Schema(description = "车位类型")
    @ExcelProperty("车位类型")
    private String lotType;

    @Schema(description = "关联充电桩ID")
    @ExcelProperty("关联充电桩ID")
    private Long pileId;

    @Schema(description = "绑定充电桩名称（模拟）")
    private String pileName;

    @Schema(description = "占用时长（分钟）")
    @ExcelProperty("占用时长（分钟）")
    private Integer occupyTime;

    @Schema(description = "车位状态")
    @ExcelProperty("车位状态")
    private String lotStatus;

    @Schema(description = "占用超时时间（分钟）")
    @ExcelProperty("占用超时时间（分钟）")
    private Integer occupyTimeout;

    @Schema(description = "维护原因")
    @ExcelProperty("维护原因")
    private String maintainReason;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String creator;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}