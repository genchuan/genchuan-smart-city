package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnarch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 预警告警处置归档新增/修改 Request VO")
@Data
public class EarlyWarnArchSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "归档ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "归档ID不能为空")
    private String archId;

    @Schema(description = "归档编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "归档编号不能为空")
    private String archNo;

    @Schema(description = "预警ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "预警ID不能为空")
    private String earlyWarnId;

    @Schema(description = "预警名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "预警名称不能为空")
    private String earlyWarnName;

    @Schema(description = "预警类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "预警类型不能为空")
    private String warnType;

    @Schema(description = "预警等级", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "预警等级不能为空")
    private String warnLevel;

    @Schema(description = "所在区域", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所在区域不能为空")
    private String regionName;

    @Schema(description = "处置人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "处置人不能为空")
    private String handleUserName;

    @Schema(description = "综合评估得分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "综合评估得分不能为空")
    private BigDecimal comprehensiveScore;

    @Schema(description = "解除状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "解除状态不能为空")
    private String releaseStatus;

    @Schema(description = "归档时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "归档时间不能为空")
    private LocalDateTime archTime;

    @Schema(description = "归档人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "归档人ID不能为空")
    private String archUserId;

    @Schema(description = "归档人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "归档人姓名不能为空")
    private String archUserName;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}