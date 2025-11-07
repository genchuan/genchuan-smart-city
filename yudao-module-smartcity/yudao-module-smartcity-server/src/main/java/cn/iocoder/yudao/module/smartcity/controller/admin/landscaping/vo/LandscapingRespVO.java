package cn.iocoder.yudao.module.smartcity.controller.admin.landscaping.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 园林绿化 Response VO")
@Data
@ExcelIgnoreUnannotated
public class LandscapingRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "7332")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "绿地名称", example = "王五")
    @ExcelProperty("绿地名称")
    private String greenSpaceName;

    @Schema(description = "绿地类型", example = "1")
    @ExcelProperty("绿地类型")
    private String greenbeltType;

    @Schema(description = "位置")
    @ExcelProperty("位置")
    private String position;

    @Schema(description = "面积")
    @ExcelProperty("面积")
    private String area;

    @Schema(description = "绿地率")
    @ExcelProperty("绿地率")
    private String greeningRate;

    @Schema(description = "绿化覆盖率")
    @ExcelProperty("绿化覆盖率")
    private String greenCoverageRate;

    @Schema(description = "植物名称", example = "王五")
    @ExcelProperty("植物名称")
    private String plantName;

    @Schema(description = "科属")
    @ExcelProperty("科属")
    private String familyAndGenus;

    @Schema(description = "数量")
    @ExcelProperty("数量")
    private String quantity;

    @Schema(description = "种植密度")
    @ExcelProperty("种植密度")
    private String plantingDensity;

    @Schema(description = "项目总投资")
    @ExcelProperty("项目总投资")
    private String totalInvestmentOfTheProject;

    @Schema(description = "土地成本")
    @ExcelProperty("土地成本")
    private String landCost;

    @Schema(description = "苗木采购成本")
    @ExcelProperty("苗木采购成本")
    private String seedlingProcurementCost;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}