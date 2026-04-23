package cn.iocoder.yudao.module.inspectop.controller.admin.shiftapply.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "巡查巡检 - 换班申请 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ShiftApplyRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "申请人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("申请人ID")
    private Long applyUserId;

    @Schema(description = "申请人姓名")
    @ExcelProperty("申请人姓名")
    private String applyUserName;

    @Schema(description = "换班对象ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("换班对象ID")
    private Long targetUserId;

    @Schema(description = "换班对象姓名")
    @ExcelProperty("换班对象姓名")
    private String targetUserName;

    @Schema(description = "原日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("原日期")
    private LocalDateTime oldDate;

    @Schema(description = "新日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("新日期")
    private LocalDateTime newDate;

    @Schema(description = "申请状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("申请状态")
    private String status;

    @Schema(description = "审核人ID")
    @ExcelProperty("审核人ID")
    private Long auditUserId;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "生效时间")
    @ExcelProperty("生效时间")
    private LocalDateTime effectTime;

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

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}