package cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridcodeannotate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 网格标识码注记配置新增/修改 Request VO")
@Data
public class GridCodeAnnotateSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22593")
    private Long id;

    @Schema(description = "注记ID", example = "20109")
    private String annotateId;

    @Schema(description = "比例尺", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "比例尺不能为空")
    private String scale;

    @Schema(description = "网格类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "网格类型不能为空")
    private String gridType;

    @Schema(description = "字高(mm)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "字高(mm)不能为空")
    private BigDecimal fontHeight;

    @Schema(description = "字型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "字型不能为空")
    private String fontType;

    @Schema(description = "颜色C值(%)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "颜色C值(%)不能为空")
    private BigDecimal colorC;

    @Schema(description = "颜色M值(%)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "颜色M值(%)不能为空")
    private BigDecimal colorM;

    @Schema(description = "颜色Y值(%)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "颜色Y值(%)不能为空")
    private BigDecimal colorY;

    @Schema(description = "颜色K值(%)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "颜色K值(%)不能为空")
    private BigDecimal colorK;

    @Schema(description = "创建人", requiredMode = Schema.RequiredMode.REQUIRED, example = "13374")
    @NotEmpty(message = "创建人不能为空")
    private String createUserId;

    @Schema(description = "备注", example = "你猜")
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