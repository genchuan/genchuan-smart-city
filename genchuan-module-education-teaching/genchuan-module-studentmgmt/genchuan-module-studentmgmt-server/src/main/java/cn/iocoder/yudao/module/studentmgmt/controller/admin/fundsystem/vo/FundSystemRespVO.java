package cn.iocoder.yudao.module.studentmgmt.controller.admin.fundsystem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 资助系统 Response VO")
@Data
@ExcelIgnoreUnannotated
public class FundSystemRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15012")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30366")
    @ExcelProperty("学生 ID")
    private Long studentId;

    @Schema(description = "资助类型：助学金/勤工俭学/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("资助类型：助学金/勤工俭学/其他")
    private String fundType;

    @Schema(description = "申请金额")
    @ExcelProperty("申请金额")
    private BigDecimal applyAmount;

    @Schema(description = "申请时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("申请时间")
    private LocalDateTime applyTime;

    @Schema(description = "审核人")
    @ExcelProperty("审核人")
    private String auditUser;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "状态：待审核/已汇总", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("状态：待审核/已汇总")
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