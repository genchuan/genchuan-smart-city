package cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 离场记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class LeaveRecordRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25996")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车牌")
    private String plateNo;

    @Schema(description = "入场时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("入场时间")
    private LocalDateTime enterTime;

    @Schema(description = "离场时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("离场时间")
    private LocalDateTime leaveTime;

    @Schema(description = "停车时长，单位：分钟，自动计算", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("停车时长，单位：分钟，自动计算")
    private Integer parkDuration;

    @Schema(description = "记录状态：正常记录/异常记录，关联字典：leave_record_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("记录状态：正常记录/异常记录，关联字典：leave_record_status")
    private String status;

    @Schema(description = "场站名称")
    @ExcelProperty("场站名称")
    private String stationName;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "佐证图片地址")
    @ExcelProperty("佐证图片地址")
    private String proofImage;

    @Schema(description = "修正日志标记：0-未修正 1-已修正", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("修正日志标记：0-未修正 1-已修正")
    private Boolean isCorrected;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}