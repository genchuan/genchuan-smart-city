package cn.iocoder.yudao.module.studentmgmt.controller.admin.staymgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 留宿管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class StayMgmtRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10528")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30478")
    @ExcelProperty("学生 ID")
    private Long studentId;

    @Schema(description = "留宿日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("留宿日期")
    private LocalDate stayDate;

    @Schema(description = "留宿原因", example = "不好")
    @ExcelProperty("留宿原因")
    private String stayReason;

    @Schema(description = "申请时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("申请时间")
    private LocalDateTime applyTime;

    @Schema(description = "家长确认时间")
    @ExcelProperty("家长确认时间")
    private LocalDateTime parentConfirmTime;

    @Schema(description = "审核人")
    @ExcelProperty("审核人")
    private String auditUser;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "状态：待确认/待审核/已通过", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态：待确认/待审核/已通过")
    private String status;

    @Schema(description = "备注", example = "你说的对")
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
