package cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.griddataqualck.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 网格数据质量检查新增/修改 Request VO")
@Data
public class GridDataQualCkSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6244")
    private Long id;

    @Schema(description = "质量检查ID", example = "10584")
    private String qualCkId;

    @Schema(description = "网格ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31757")
    @NotEmpty(message = "网格ID不能为空")
    private String gridId;

    @Schema(description = "网格类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "网格类型不能为空")
    private String gridType;

    @Schema(description = "网格编码")
    private String gridCode;

    @Schema(description = "检查项", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "检查项不能为空")
    private String ckItem;

    @Schema(description = "检查结果", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "检查结果不能为空")
    private String ckResult;

    @Schema(description = "错误描述")
    private String errorDesc;

    @Schema(description = "整改建议")
    private String rectifySugg;

    @Schema(description = "检查时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "检查时间不能为空")
    private LocalDateTime ckTime;

    @Schema(description = "检查人员", requiredMode = Schema.RequiredMode.REQUIRED, example = "25277")
    @NotEmpty(message = "检查人员不能为空")
    private String ckUserId;

    @Schema(description = "整改状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "整改状态不能为空")
    private String rectifyStatus;

    @Schema(description = "整改时间")
    private LocalDateTime rectifyTime;

    @Schema(description = "整改人员", example = "21785")
    private String rectifyUserId;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}