package cn.iocoder.yudao.module.studentmgmt.controller.admin.aidwork.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 奖助勤贷 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AidWorkRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19452")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10682")
    @ExcelProperty("学生 ID")
    private Long studentId;

    @Schema(description = "资助类型：奖学金/助学金/助学贷款/勤工俭学", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("资助类型")
    private String aidType;

    @Schema(description = "申请金额")
    @ExcelProperty("申请金额")
    private BigDecimal applyAmount;

    @Schema(description = "申报时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("申报时间")
    private LocalDateTime applyTime;

    @Schema(description = "审核人")
    @ExcelProperty("审核人")
    private String auditUser;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "流程状态：跟进中/已完成", example = "2")
    @ExcelProperty("流程状态")
    private String processStatus;

    @Schema(description = "状态：待审核/已通过/已完成", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态")
    private String status;

    @Schema(description = "备注", example = "随便")
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
