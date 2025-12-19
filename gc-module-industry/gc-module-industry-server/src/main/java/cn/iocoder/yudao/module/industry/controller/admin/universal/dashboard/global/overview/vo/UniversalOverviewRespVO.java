package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.overview.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 通用大屏全域数据地图 Response VO")
@Data
public class UniversalOverviewRespVO {

    @Schema(description = "设施ID（管理部件ID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "c8f3a1b2e9f64a8a9d1234567890abcd")
    @ExcelProperty("设施ID")
    private String facilityId;

    @Schema(description = "设施名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "XX路电力井盖")
    @ExcelProperty("设施名称")
    private String facilityName;

    @Schema(description = "所属区域名称", example = "朝阳区")
    @ExcelProperty("所属区域")
    private String regionName;

    @Schema(description = "设施类型（管理部件小类名称）", example = "电力井盖")
    @ExcelProperty("设施类型")
    private String facilityType;

    @Schema(description = "设施状态（完好/破损/丢失/废弃）", requiredMode = Schema.RequiredMode.REQUIRED, example = "破损")
    @ExcelProperty("设施状态")
    private String status;

    @Schema(description = "坐标X（经度）", requiredMode = Schema.RequiredMode.REQUIRED, example = "116.38")
    @ExcelProperty("坐标X")
    private BigDecimal coordX;

    @Schema(description = "坐标Y（纬度）", requiredMode = Schema.RequiredMode.REQUIRED, example = "39.90")
    @ExcelProperty("坐标Y")
    private BigDecimal coordY;

}
