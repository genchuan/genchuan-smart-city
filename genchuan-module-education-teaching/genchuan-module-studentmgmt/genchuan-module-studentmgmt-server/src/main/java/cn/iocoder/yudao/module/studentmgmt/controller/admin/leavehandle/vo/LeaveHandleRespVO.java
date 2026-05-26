package cn.iocoder.yudao.module.studentmgmt.controller.admin.leavehandle.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 离校办理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class LeaveHandleRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12005")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25257")
    @ExcelProperty("学生 ID")
    private Long studentId;

    @Schema(description = "离校时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("离校时间")
    private LocalDateTime leaveTime;

    @Schema(description = "离校去处")
    @ExcelProperty("离校去处")
    private String leaveAddress;

    @Schema(description = "家长确认时间")
    @ExcelProperty("家长确认时间")
    private LocalDateTime parentConfirmTime;

    @Schema(description = "办理人")
    @ExcelProperty("办理人")
    private String handleUser;

    @Schema(description = "办理时间")
    @ExcelProperty("办理时间")
    private LocalDateTime handleTime;

    @Schema(description = "退宿时间")
    @ExcelProperty("退宿时间")
    private LocalDateTime checkoutTime;

    @Schema(description = "退宿状态：未退宿/已退宿", example = "1")
    @ExcelProperty("退宿状态")
    private String checkoutStatus;

    @Schema(description = "办理完成率")
    @ExcelProperty("办理完成率")
    private BigDecimal finishRate;

    @Schema(description = "状态：待确认/待办理/已离校", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态")
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
