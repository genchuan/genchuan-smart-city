package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspect.evalinspectrec.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 考察记录数据新增/修改 Request VO")
@Data
public class EvalInspectRecSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "记录ID")
    private String inspectRecId;

    @Schema(description = "关联计划ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联计划ID不能为空")
    private String inspectPlanId;

    @Schema(description = "关联计划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联计划名称不能为空")
    private String inspectPlanName;

    @Schema(description = "考察对象ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "考察对象ID不能为空")
    private String evalObjectId;

    @Schema(description = "考察对象名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "考察对象名称不能为空")
    private String evalObjectName;

    @Schema(description = "关联指标项ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联指标项ID不能为空")
    private String idxItemId;

    @Schema(description = "关联指标项名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联指标项名称不能为空")
    private String idxItemName;

    @Schema(description = "考察得分")
    private BigDecimal inspectScore;

    @Schema(description = "考察等级")
    private String inspectLevel;

    @Schema(description = "问题描述")
    private String problemDesc;

    @Schema(description = "现场照片")
    private String onSitePhotos;

    @Schema(description = "考察时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "考察时间不能为空")
    private LocalDateTime inspectTime;

    @Schema(description = "考察人员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "考察人员ID不能为空")
    private String inspectorId;

    @Schema(description = "考察人员姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "考察人员姓名不能为空")
    private String inspectorName;

    @Schema(description = "记录状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "记录状态不能为空")
    private String recStatus;

    @Schema(description = "审核人ID")
    private String auditorId;

    @Schema(description = "审核人姓名")
    private String auditorName;

    @Schema(description = "审核意见")
    private String auditOpinion;

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