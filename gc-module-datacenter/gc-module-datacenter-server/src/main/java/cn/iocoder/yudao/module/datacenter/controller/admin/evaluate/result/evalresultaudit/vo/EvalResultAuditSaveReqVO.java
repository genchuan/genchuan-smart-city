package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultaudit.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 结果审核新增/修改 Request VO")
@Data
public class EvalResultAuditSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "审核ID")
    private String resultAuditId;

    @Schema(description = "关联任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联任务ID不能为空")
    private String evalTaskId;

    @Schema(description = "关联任务名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联任务名称不能为空")
    private String evalTaskName;

    @Schema(description = "评价对象ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评价对象ID不能为空")
    private String evalObjectId;

    @Schema(description = "评价对象名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评价对象名称不能为空")
    private String evalObjectName;

    @Schema(description = "自动计算结果", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "自动计算结果不能为空")
    private String autoCalcResult;

    @Schema(description = "自动计算得分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "自动计算得分不能为空")
    private BigDecimal autoCalcScore;

    @Schema(description = "审核状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "审核状态不能为空")
    private String auditStatus;

    @Schema(description = "审核人ID")
    private String auditorId;

    @Schema(description = "审核人姓名")
    private String auditorName;

    @Schema(description = "审核意见")
    private String auditOpinion;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "创建人(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人(业务)不能为空")
    private String createUserBiz;

    @Schema(description = "创建时间(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间(业务)不能为空")
    private LocalDateTime createTimeBiz;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}