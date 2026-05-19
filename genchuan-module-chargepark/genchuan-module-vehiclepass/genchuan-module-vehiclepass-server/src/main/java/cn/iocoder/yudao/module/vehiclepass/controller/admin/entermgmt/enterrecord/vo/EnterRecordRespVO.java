package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.enterrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 入场记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EnterRecordRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28895")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车牌")
    private String plateNo;

    @Schema(description = "车牌颜色：蓝牌/黄牌/绿牌/其他，关联字典enter_record_plate_color", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车牌颜色：蓝牌/黄牌/绿牌/其他，关联字典enter_record_plate_color")
    private String plateColor;

    @Schema(description = "车位编号")
    @ExcelProperty("车位编号")
    private String spaceNo;

    @Schema(description = "入场时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("入场时间")
    private LocalDateTime enterTime;

    @Schema(description = "记录类型：自动识别/人工补录，关联字典enter_record_record_type", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("记录类型：自动识别/人工补录，关联字典enter_record_record_type")
    private String recordType;

    @Schema(description = "记录状态：正常记录/异常记录，关联字典enter_record_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("记录状态：正常记录/异常记录，关联字典enter_record_status")
    private String status;

    @Schema(description = "场站ID，关联场站表", requiredMode = Schema.RequiredMode.REQUIRED, example = "20633")
    @ExcelProperty("场站ID，关联场站表")
    private Long stationId;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "佐证图片地址")
    @ExcelProperty("佐证图片地址")
    private String proofImage;

    @Schema(description = "修正日志标记：0-未修正 1-已修正 2-已确认", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("修正日志标记：0-未修正 1-已修正 2-已确认")
    private Integer isCorrected;

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