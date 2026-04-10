package cn.iocoder.yudao.module.studentmgmt.controller.admin.treatmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 就诊管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TreatMgmtRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5311")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19989")
    @ExcelProperty("学生 ID")
    private Long studentId;

    @Schema(description = "就诊类型：门诊/急诊/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("就诊类型：门诊/急诊/其他")
    private String treatType;

    @Schema(description = "症状描述")
    @ExcelProperty("症状描述")
    private String symptom;

    @Schema(description = "就诊登记时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("就诊登记时间")
    private LocalDateTime registerTime;

    @Schema(description = "就诊内容")
    @ExcelProperty("就诊内容")
    private String treatContent;

    @Schema(description = "预约时间")
    @ExcelProperty("预约时间")
    private LocalDateTime applyTime;

    @Schema(description = "审核人")
    @ExcelProperty("审核人")
    private String auditUser;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "家长反馈时间")
    @ExcelProperty("家长反馈时间")
    private LocalDateTime feedbackTime;

    @Schema(description = "状态：待审核/已就诊", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("状态：待审核/已就诊")
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