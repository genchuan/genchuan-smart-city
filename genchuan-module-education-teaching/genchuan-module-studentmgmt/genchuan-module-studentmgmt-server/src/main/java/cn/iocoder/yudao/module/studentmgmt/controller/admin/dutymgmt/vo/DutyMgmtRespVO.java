package cn.iocoder.yudao.module.studentmgmt.controller.admin.dutymgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 值班管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DutyMgmtRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22240")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "值班日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("值班日期")
    private LocalDate dutyDate;

    @Schema(description = "值班人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("值班人")
    private String dutyUser;

    @Schema(description = "打卡时间")
    @ExcelProperty("打卡时间")
    private LocalDateTime checkInTime;

    @Schema(description = "打卡状态：未打卡/已打卡", example = "1")
    @ExcelProperty("打卡状态：未打卡/已打卡")
    private String checkInStatus;

    @Schema(description = "调班原因", example = "不香")
    @ExcelProperty("调班原因")
    private String transferReason;

    @Schema(description = "调班替代人")
    @ExcelProperty("调班替代人")
    private String transferUser;

    @Schema(description = "调班状态：无/待审批/已通过/已驳回", example = "1")
    @ExcelProperty("调班状态：无/待审批/已通过/已驳回")
    private String transferStatus;

    @Schema(description = "出车事由", example = "不对")
    @ExcelProperty("出车事由")
    private String carReason;

    @Schema(description = "出车目的地")
    @ExcelProperty("出车目的地")
    private String carDestination;

    @Schema(description = "出车状态：无/待审批/已通过", example = "2")
    @ExcelProperty("出车状态：无/待审批/已通过")
    private String carStatus;

    @Schema(description = "值班记录")
    @ExcelProperty("值班记录")
    private String recordContent;

    @Schema(description = "记录上传时间")
    @ExcelProperty("记录上传时间")
    private LocalDateTime recordUploadTime;

    @Schema(description = "状态：待打卡/待调班审批/待出车审批/已完成", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("状态：待打卡/待调班审批/待出车审批/已完成")
    private String status;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段 1")
    @ExcelProperty("备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    @ExcelProperty("备用字段 2")
    private String reserve2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
