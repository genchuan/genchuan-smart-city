package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnhandletrace.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 预警告警处置跟踪 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EarlyWarnHandleTraceRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "跟踪ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("跟踪ID")
    private String handleTraceId;

    @Schema(description = "派单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("派单ID")
    private String dispatchId;

    @Schema(description = "派单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("派单编号")
    private String dispatchNo;

    @Schema(description = "预警ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预警ID")
    private String earlyWarnId;

    @Schema(description = "处置进度", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("处置进度")
    private String handleProgress;

    @Schema(description = "进度描述")
    @ExcelProperty("进度描述")
    private String progressDesc;

    @Schema(description = "操作人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("操作人ID")
    private String operUserId;

    @Schema(description = "操作人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("操作人姓名")
    private String operUserName;

    @Schema(description = "操作时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("操作时间")
    private LocalDateTime operTime;

    @Schema(description = "附件URL")
    @ExcelProperty("附件URL")
    private String attachUrl;

    @Schema(description = "是否关键节点", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否关键节点")
    private String isKeyNode;

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