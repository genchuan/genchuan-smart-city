package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnrelease.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 预警告警解除 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EarlyWarnReleaseRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "解除ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("解除ID")
    private String releaseId;

    @Schema(description = "预警ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预警ID")
    private String earlyWarnId;

    @Schema(description = "预警名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预警名称")
    private String earlyWarnName;

    @Schema(description = "解除申请人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("解除申请人ID")
    private String applyUserId;

    @Schema(description = "解除申请人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("解除申请人姓名")
    private String applyUserName;

    @Schema(description = "解除审核人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("解除审核人ID")
    private String auditUserId;

    @Schema(description = "解除审核人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("解除审核人姓名")
    private String auditUserName;

    @Schema(description = "解除状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("解除状态")
    private String releaseStatus;

    @Schema(description = "解除原因", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("解除原因")
    private String releaseReason;

    @Schema(description = "申请时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("申请时间")
    private LocalDateTime applyTime;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "驳回原因")
    @ExcelProperty("驳回原因")
    private String rejectReason;

    @Schema(description = "是否复发", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否复发")
    private String isRecurrence;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}