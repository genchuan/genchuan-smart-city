package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectledgerarch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 巡查巡检台账档案新增/修改 Request VO")
@Data
public class InspectLedgerArchSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "档案ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "档案ID不能为空")
    private String ledgerArchId;

    @Schema(description = "档案编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "档案编码不能为空")
    private String ledgerArchCode;

    @Schema(description = "档案名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "档案名称不能为空")
    private String ledgerArchName;

    @Schema(description = "所属区域代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属区域代码不能为空")
    private String areaCode;

    @Schema(description = "所属区域名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属区域名称不能为空")
    private String areaName;

    @Schema(description = "台账周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "台账周期不能为空")
    private String ledgerCycle;

    @Schema(description = "核心指标JSON", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "核心指标JSON不能为空")
    private String coreIdxJson;

    @Schema(description = "包含档案类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "包含档案类型不能为空")
    private String includeArchTypes;

    @Schema(description = "台账附件URL", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "台账附件URL不能为空")
    private String ledgerAttachUrl;

    @Schema(description = "归档人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "归档人不能为空")
    private String ledgerArchUser;

    @Schema(description = "归档时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "归档时间不能为空")
    private LocalDateTime ledgerArchTime;

    @Schema(description = "档案状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "档案状态不能为空")
    private String ledgerArchStatus;

    @Schema(description = "销毁时间")
    private LocalDateTime ledgerDestroyTime;

    @Schema(description = "归档说明")
    private String ledgerArchDesc;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}