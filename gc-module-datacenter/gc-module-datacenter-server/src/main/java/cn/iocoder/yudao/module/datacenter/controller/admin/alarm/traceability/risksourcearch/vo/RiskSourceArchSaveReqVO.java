package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.risksourcearch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 风险源档案新增/修改 Request VO")
@Data
public class RiskSourceArchSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "风险源ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "风险源ID不能为空")
    private String riskSourceId;

    @Schema(description = "风险源编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "风险源编号不能为空")
    private String riskSourceNo;

    @Schema(description = "风险源名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "风险源名称不能为空")
    private String riskSourceName;

    @Schema(description = "风险源类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "风险源类型不能为空")
    private String riskSourceType;

    @Schema(description = "风险等级", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "风险等级不能为空")
    private String riskLevel;

    @Schema(description = "所在行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所在行政区划代码不能为空")
    private String regionCode;

    @Schema(description = "所在行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所在行政区划名称不能为空")
    private String regionName;

    @Schema(description = "所在位置描述", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所在位置描述不能为空")
    private String locationDesc;

    @Schema(description = "关联资产ID")
    private String assetId;

    @Schema(description = "关联资产名称")
    private String assetName;

    @Schema(description = "责任部门ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "责任部门ID不能为空")
    private String deptId;

    @Schema(description = "责任部门名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "责任部门名称不能为空")
    private String deptName;

    @Schema(description = "历史预警次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "历史预警次数不能为空")
    private Integer historyWarnCount;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}