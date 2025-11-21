package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultarch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 结果存档管理新增/修改 Request VO")
@Data
public class EvalResultArchSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "存档ID")
    private String resultArchId;

    @Schema(description = "存档编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "存档编号不能为空")
    private String archCode;

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

    @Schema(description = "最终评价等级", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "最终评价等级不能为空")
    private String finalEvalLevel;

    @Schema(description = "最终评价得分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "最终评价得分不能为空")
    private BigDecimal finalEvalScore;

    @Schema(description = "存档原因", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "存档原因不能为空")
    private String archReason;

    @Schema(description = "存档时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "存档时间不能为空")
    private LocalDateTime archTime;

    @Schema(description = "存档附件")
    private String archAttachs;

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