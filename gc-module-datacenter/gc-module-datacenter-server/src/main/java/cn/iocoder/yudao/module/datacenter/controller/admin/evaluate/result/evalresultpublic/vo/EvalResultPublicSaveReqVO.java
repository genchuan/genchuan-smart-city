package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultpublic.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 结果公示新增/修改 Request VO")
@Data
public class EvalResultPublicSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "公示ID")
    private String resultPublicId;

    @Schema(description = "关联审核ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联审核ID不能为空")
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

    @Schema(description = "评价等级", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评价等级不能为空")
    private String evalLevel;

    @Schema(description = "评价得分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "评价得分不能为空")
    private BigDecimal evalScore;

    @Schema(description = "公示范围", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "公示范围不能为空")
    private String publicScope;

    @Schema(description = "公示开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "公示开始时间不能为空")
    private LocalDateTime publicStartTime;

    @Schema(description = "公示结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "公示结束时间不能为空")
    private LocalDateTime publicEndTime;

    @Schema(description = "公示状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "公示状态不能为空")
    private String publicStatus;

    @Schema(description = "公示链接")
    private String publicUrl;

    @Schema(description = "异议数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "异议数量不能为空")
    private Integer objectCount;

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