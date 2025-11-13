package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnhandleeval.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 预警告警处置评估 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EarlyWarnHandleEvalRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "评估ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评估ID")
    private String evalId;

    @Schema(description = "预警ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预警ID")
    private String earlyWarnId;

    @Schema(description = "派单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("派单ID")
    private String dispatchId;

    @Schema(description = "评估人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评估人ID")
    private String evalUserId;

    @Schema(description = "评估人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评估人姓名")
    private String evalUserName;

    @Schema(description = "问题解决度得分", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("问题解决度得分")
    private Integer problemSolveScore;

    @Schema(description = "响应及时性得分", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("响应及时性得分")
    private Integer timelinessScore;

    @Schema(description = "现场规范性得分", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("现场规范性得分")
    private Integer stdizationScore;

    @Schema(description = "综合得分", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("综合得分")
    private BigDecimal comprehensiveScore;

    @Schema(description = "评估等级", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评估等级")
    private String evalLevel;

    @Schema(description = "评估时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评估时间")
    private LocalDateTime evalTime;

    @Schema(description = "评估备注")
    @ExcelProperty("评估备注")
    private String evalRemark;

    @Schema(description = "是否二次处置", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否二次处置")
    private String isSecondHandle;

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