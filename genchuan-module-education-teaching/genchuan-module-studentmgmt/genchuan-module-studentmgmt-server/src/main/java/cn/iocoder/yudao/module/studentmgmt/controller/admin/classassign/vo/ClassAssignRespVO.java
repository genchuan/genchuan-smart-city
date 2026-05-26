package cn.iocoder.yudao.module.studentmgmt.controller.admin.classassign.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 分班管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ClassAssignRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26674")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "分班规则", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分班规则")
    private String ruleContent;

    @Schema(description = "分班学生数")
    @ExcelProperty("分班学生数")
    private Integer studentNum;

    @Schema(description = "分班时间")
    @ExcelProperty("分班时间")
    private LocalDateTime assignTime;

    @Schema(description = "确认人")
    @ExcelProperty("确认人")
    private String confirmUser;

    @Schema(description = "确认时间")
    @ExcelProperty("确认时间")
    private LocalDateTime confirmTime;

    @Schema(description = "状态：未分班/已分班", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
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
