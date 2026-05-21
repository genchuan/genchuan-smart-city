package cn.iocoder.yudao.module.studentmgmt.controller.admin.accessapply.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 出入申请 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AccessApplyRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28041")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22874")
    @ExcelProperty("学生 ID")
    private Long studentId;

    @Schema(description = "申请类型：应急出入/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("申请类型")
    private String applyType;

    @Schema(description = "申请原因", requiredMode = Schema.RequiredMode.REQUIRED, example = "不喜欢")
    @ExcelProperty("申请原因")
    private String applyReason;

    @Schema(description = "申请时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("申请时间")
    private LocalDateTime applyTime;

    @Schema(description = "审核人")
    @ExcelProperty("审核人")
    private String auditUser;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "状态：待审核/已通过", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
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
