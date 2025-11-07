package cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.mnggridrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 管理网格统计新增/修改 Request VO")
@Data
public class MngGridRptSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "统计ID")
    private String mgStatId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期不能为空")
    private String statCycle;

    @Schema(description = "统计周期名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期名称不能为空")
    private String statCycleName;

    @Schema(description = "行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划代码不能为空")
    private String regionCode;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划名称不能为空")
    private String regionName;

    @Schema(description = "网格员ID")
    private String gridUserId;

    @Schema(description = "网格员姓名")
    private String gridUserName;

    @Schema(description = "所含单元数范围")
    private String unitCountRange;

    @Schema(description = "网格总数(个)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "网格总数(个)不能为空")
    private Integer totalCount;

    @Schema(description = "总面积(m²)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "总面积(m²)不能为空")
    private Integer totalArea;

    @Schema(description = "平均所含单元数(个)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "平均所含单元数(个)不能为空")
    private BigDecimal avgUnitCount;

    @Schema(description = "总所含单元网格数(个)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "总所含单元网格数(个)不能为空")
    private Integer totalUnitCount;

    @Schema(description = "网格员覆盖数(个)")
    private Integer gridUserCoverCount;

    @Schema(description = "新增网格数(个)")
    private Integer newCount;

    @Schema(description = "统计人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计人不能为空")
    private String statUserId;

    @Schema(description = "统计时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计时间不能为空")
    private LocalDateTime statTime;

    @Schema(description = "报表备注")
    private String rptRemark;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}