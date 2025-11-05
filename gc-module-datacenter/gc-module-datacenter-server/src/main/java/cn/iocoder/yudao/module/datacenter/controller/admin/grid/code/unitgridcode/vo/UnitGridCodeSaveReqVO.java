package cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.unitgridcode.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 单元网格编码新增/修改 Request VO")
@Data
public class UnitGridCodeSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5052")
    private Long id;

    @Schema(description = "编码ID", example = "8933")
    private String unitCodeId;

    @Schema(description = "单元网格标识码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "单元网格标识码不能为空")
    private String unitGridCode;

    @Schema(description = "所属单元网格ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19876")
    @NotEmpty(message = "所属单元网格ID不能为空")
    private String unitGridId;

    @Schema(description = "行政区划完整代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划完整代码不能为空")
    private String areaFullCode;

    @Schema(description = "顺序码")
    private String seqCode;

    @Schema(description = "编码生成时间")
    private LocalDateTime generateTime;

    @Schema(description = "编码状态", example = "1")
    @NotEmpty(message = "编码状态不能为空")
    private String codeStatus;

    @Schema(description = "备注", example = "随便")
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