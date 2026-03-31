package cn.iocoder.yudao.module.vehiclecharging.controller.admin.charginglot.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 充电车位 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ChargingLotRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "车位编号")
    @ExcelProperty("车位编号")
    private String lotCode;

    @Schema(description = "所属场站编码")
    @ExcelProperty("所属场站编码")
    private String stationCode;

    @Schema(description = "所属场站ID")
    @ExcelProperty("所属场站ID")
    private Long stationId;

    @Schema(description = "车位类型")
    @ExcelProperty("车位类型")
    private String lotType;

    @Schema(description = "关联充电桩编号")
    @ExcelProperty("关联充电桩编号")
    private String pileCode;

    @Schema(description = "关联充电桩ID")
    @ExcelProperty("关联充电桩ID")
    private Long pileId;

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

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}