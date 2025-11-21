package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.relatedriskident.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 关联风险识别 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RelatedRiskIdentRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "识别ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("识别ID")
    private String identId;

    @Schema(description = "触发预警ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("触发预警ID")
    private String triggerWarnId;

    @Schema(description = "触发预警名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("触发预警名称")
    private String triggerWarnName;

    @Schema(description = "触发预警类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("触发预警类型")
    private String triggerWarnType;

    @Schema(description = "关联风险名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联风险名称")
    private String relatedRiskName;

    @Schema(description = "关联风险类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联风险类型")
    private String relatedRiskType;

    @Schema(description = "关联依据", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联依据")
    private String relatedBasis;

    @Schema(description = "风险等级", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("风险等级")
    private String riskLevel;

    @Schema(description = "识别时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("识别时间")
    private LocalDateTime identTime;

    @Schema(description = "识别方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("识别方式")
    private String identMethod;

    @Schema(description = "识别用户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("识别用户ID")
    private String identifyUserId;

    @Schema(description = "识别用户姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("识别用户姓名")
    private String identifyUserName;

    @Schema(description = "是否已处置", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否已处置")
    private String isHandled;

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

    @Schema(description = "create_time")
    @ExcelProperty("create_time")
    private LocalDateTime createTime;

}