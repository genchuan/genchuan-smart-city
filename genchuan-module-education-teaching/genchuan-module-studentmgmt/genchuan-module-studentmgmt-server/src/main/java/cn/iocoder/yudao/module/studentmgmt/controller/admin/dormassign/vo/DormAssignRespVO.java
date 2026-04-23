package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormassign.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 宿舍分配 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DormAssignRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5850")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8156")
    @ExcelProperty("学生 ID")
    private Long studentId;

    @Schema(description = "宿舍号")
    @ExcelProperty("宿舍号")
    private String dormNum;

    @Schema(description = "床位 ID", example = "26304")
    @ExcelProperty("床位 ID")
    private Long bedId;

    @Schema(description = "分配规则")
    @ExcelProperty("分配规则")
    private String ruleContent;

    @Schema(description = "分配时间")
    @ExcelProperty("分配时间")
    private LocalDateTime assignTime;

    @Schema(description = "调整时间")
    @ExcelProperty("调整时间")
    private LocalDateTime adjustTime;

    @Schema(description = "分配完成率")
    @ExcelProperty("分配完成率")
    private BigDecimal finishRate;

    @Schema(description = "状态：未分配/已分配", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态：未分配/已分配")
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