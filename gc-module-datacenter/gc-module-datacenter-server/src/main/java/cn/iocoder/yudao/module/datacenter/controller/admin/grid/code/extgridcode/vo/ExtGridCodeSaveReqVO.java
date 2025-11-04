package cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.extgridcode.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 扩展网格编码新增/修改 Request VO")
@Data
public class ExtGridCodeSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "编码ID")
    private String extCodeId;

    @Schema(description = "扩展网格标识码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "扩展网格标识码不能为空")
    private String extGridCode;

    @Schema(description = "所属扩展网格ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属扩展网格ID不能为空")
    private String extGridId;

    @Schema(description = "关联行政区划ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "关联行政区划ID不能为空")
    private Long areaId;

    @Schema(description = "行政区划完整代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划完整代码不能为空")
    private String areaFullCode;

    @Schema(description = "顺序码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "顺序码不能为空")
    private String seqCode;

    @Schema(description = "类型码(G/Z/S/Q)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "类型码(G/Z/S/Q)不能为空")
    private String typeCode;

    @Schema(description = "编码生成时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "编码生成时间不能为空")
    private LocalDateTime generateTime;

    @Schema(description = "编码状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "编码状态不能为空")
    private String codeStatus;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}