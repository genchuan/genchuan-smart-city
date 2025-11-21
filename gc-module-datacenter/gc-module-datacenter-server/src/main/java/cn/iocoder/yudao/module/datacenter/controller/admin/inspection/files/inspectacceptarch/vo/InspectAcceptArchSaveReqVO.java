package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectacceptarch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 巡查巡检验收档案新增/修改 Request VO")
@Data
public class InspectAcceptArchSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "档案ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "档案ID不能为空")
    private String acceptArchId;

    @Schema(description = "档案编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "档案编码不能为空")
    private String acceptArchCode;

    @Schema(description = "档案名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "档案名称不能为空")
    private String acceptArchName;

    @Schema(description = "所属区域代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属区域代码不能为空")
    private String areaCode;

    @Schema(description = "所属区域名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属区域名称不能为空")
    private String areaName;

    @Schema(description = "归档周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "归档周期不能为空")
    private String acceptArchCycle;

    @Schema(description = "包含验收记录数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "包含验收记录数不能为空")
    private Integer includeAcceptCount;

    @Schema(description = "验收合格率", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "验收合格率不能为空")
    private BigDecimal acceptQualifiedRate;

    @Schema(description = "档案附件URLs")
    private String acceptAttachUrls;

    @Schema(description = "归档人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "归档人不能为空")
    private String acceptArchUser;

    @Schema(description = "归档时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "归档时间不能为空")
    private LocalDateTime acceptArchTime;

    @Schema(description = "档案状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "档案状态不能为空")
    private String acceptArchStatus;

    @Schema(description = "销毁时间")
    private LocalDateTime acceptDestroyTime;

    @Schema(description = "归档说明")
    private String acceptArchDesc;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}