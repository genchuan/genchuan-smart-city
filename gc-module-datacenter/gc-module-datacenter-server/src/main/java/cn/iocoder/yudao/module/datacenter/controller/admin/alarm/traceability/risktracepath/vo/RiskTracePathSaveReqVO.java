package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.risktracepath.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 风险溯源路径新增/修改 Request VO")
@Data
public class RiskTracePathSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "路径ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "路径ID不能为空")
    private String tracePathId;

    @Schema(description = "预警ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "预警ID不能为空")
    private String earlyWarnId;

    @Schema(description = "预警名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "预警名称不能为空")
    private String earlyWarnName;

    @Schema(description = "溯源时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "溯源时间不能为空")
    private LocalDateTime traceTime;

    @Schema(description = "路径节点1（直接现象）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "路径节点1（直接现象）不能为空")
    private String node1Content;

    @Schema(description = "路径节点2（直接原因）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "路径节点2（直接原因）不能为空")
    private String node2Content;

    @Schema(description = "路径节点3（根本原因）")
    private String node3Content;

    @Schema(description = "溯源结论", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "溯源结论不能为空")
    private String traceConclusion;

    @Schema(description = "分析人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分析人ID不能为空")
    private String analystUserId;

    @Schema(description = "分析人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分析人姓名不能为空")
    private String analystUserName;

    @Schema(description = "是否自动分析", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "是否自动分析不能为空")
    private String isAutoAnalysis;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}