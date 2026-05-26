package cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 异常离场 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AbnormalLeaveRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车牌")
    private String plateNo;

    @Schema(description = "异常类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("异常类型")
    private String abnormalType;

    @Schema(description = "识别时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("识别时间")
    private LocalDateTime identifyTime;

    @Schema(description = "处置状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("处置状态")
    private String status;

    @Schema(description = "场站ID")
    private Long stationId;

    @Schema(description = "场站名称")
    @ExcelProperty("场站名称")
    private String stationName;

    @Schema(description = "处置人姓名")
    @ExcelProperty("处置人姓名")
    private String handleUserName;

    @Schema(description = "处置时间")
    @ExcelProperty("处置时间")
    private LocalDateTime handleTime;

    @Schema(description = "处置进度")
    @ExcelProperty("处置进度")
    private String handleProgress;

    @Schema(description = "忽略理由")
    @ExcelProperty("忽略理由")
    private String ignoreReason;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}