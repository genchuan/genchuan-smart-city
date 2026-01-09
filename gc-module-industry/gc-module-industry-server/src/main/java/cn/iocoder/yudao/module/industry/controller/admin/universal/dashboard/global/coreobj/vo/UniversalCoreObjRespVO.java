package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coreobj.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 通用大屏核心对象分布视图 Response VO")
@Data
public class UniversalCoreObjRespVO {

    @Schema(description = "设施ID（部件ID）", example = "c8f3a1b2e9f64a8a9d1234567890abcd")
    @ExcelProperty("设施ID")
    private String facilityId;

    @Schema(description = "设施名称", example = "XX路电力井盖")
    @ExcelProperty("设施名称")
    private String facilityName;

    @Schema(description = "设施类型（部件小类名称）", example = "电力井盖")
    @ExcelProperty("设施类型")
    private String facilityType;

    @Schema(description = "所属区域名称", example = "朝阳区")
    @ExcelProperty("所属区域")
    private String regionName;



    @Schema(description = "设施状态（完好/破损/丢失/废弃）", example = "破损")
    @ExcelProperty("设施状态")
    private String status;

//    @Schema(description = "坐标X（经度）", example = "116.38")
//    @ExcelProperty("坐标X")
//    private BigDecimal coordX;
//
//    @Schema(description = "坐标Y（纬度）", example = "39.90")
//    @ExcelProperty("坐标Y")
//    private BigDecimal coordY;

    @Schema(description = "主管部门名称", example = "朝阳区住建局")
    @ExcelProperty("主管单位")
    private String deptName;

//    @Schema(description = "网格名称", example = "XX网格")
//    @ExcelProperty("所属网格")
//    private String gridName;
}
