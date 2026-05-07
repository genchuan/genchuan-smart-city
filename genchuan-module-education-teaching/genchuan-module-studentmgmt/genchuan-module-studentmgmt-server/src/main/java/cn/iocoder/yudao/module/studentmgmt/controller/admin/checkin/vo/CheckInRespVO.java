package cn.iocoder.yudao.module.studentmgmt.controller.admin.checkin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 报到管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CheckInRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26468")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8524")
    @ExcelProperty("学生 ID")
    private Long studentId;

    @Schema(description = "中考成绩")
    @ExcelProperty("中考成绩")
    private BigDecimal examScore;

    @Schema(description = "补充信息")
    @ExcelProperty("补充信息")
    private String supplyInfo;

    @Schema(description = "报到确认时间")
    @ExcelProperty("报到确认时间")
    private LocalDateTime confirmTime;

    @Schema(description = "审核人")
    @ExcelProperty("审核人")
    private String auditUser;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "账号创建时间")
    @ExcelProperty("账号创建时间")
    private LocalDateTime accountCreateTime;

    @Schema(description = "账号状态：未创建/已创建", example = "2")
    @ExcelProperty("账号状态：未创建/已创建")
    private String accountStatus;

    @Schema(description = "状态：待确认/待审核/已报到", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("状态：待确认/待审核/已报到")
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
