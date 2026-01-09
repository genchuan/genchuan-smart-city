package cn.iocoder.yudao.module.smartcity.controller.admin.landscaping.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 园林绿化新增/修改 Request VO")
@Data
public class LandscapingSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "7332")
    private Long id;

    @Schema(description = "绿地名称", example = "王五")
    private String greenSpaceName;

    @Schema(description = "绿地类型", example = "1")
    private String greenbeltType;

    @Schema(description = "位置")
    private String position;

    @Schema(description = "面积")
    private String area;

    @Schema(description = "绿地率")
    private String greeningRate;

    @Schema(description = "绿化覆盖率")
    private String greenCoverageRate;

    @Schema(description = "植物名称", example = "王五")
    private String plantName;

    @Schema(description = "科属")
    private String familyAndGenus;

    @Schema(description = "数量")
    private String quantity;

    @Schema(description = "种植密度")
    private String plantingDensity;

    @Schema(description = "项目总投资")
    private String totalInvestmentOfTheProject;

    @Schema(description = "土地成本")
    private String landCost;

    @Schema(description = "苗木采购成本")
    private String seedlingProcurementCost;

}