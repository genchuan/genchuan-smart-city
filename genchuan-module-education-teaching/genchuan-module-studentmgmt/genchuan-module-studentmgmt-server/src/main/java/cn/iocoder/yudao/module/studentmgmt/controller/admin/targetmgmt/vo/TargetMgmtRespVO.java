package cn.iocoder.yudao.module.studentmgmt.controller.admin.targetmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 指标管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TargetMgmtRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20828")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "指标名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("指标名称")
    private String targetName;

    @Schema(description = "指标总分", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("指标总分")
    private BigDecimal totalScore;

    @Schema(description = "预警阈值")
    @ExcelProperty("预警阈值")
    private BigDecimal warnThreshold;

    @Schema(description = "评价人类型：教职工/家长/领导", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("评价人类型")
    private String evaluatorType;

    @Schema(description = "计分方式：累计赋分/接口赋分", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("计分方式")
    private String scoreType;

    @Schema(description = "启用时间")
    @ExcelProperty("启用时间")
    private LocalDateTime enableTime;

    @Schema(description = "停用时间")
    @ExcelProperty("停用时间")
    private LocalDateTime disableTime;

    @Schema(description = "状态：未启用/已启用", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
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
