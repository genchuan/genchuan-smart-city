package cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 监测部件信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MonCompInfoRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2297")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "部件ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "20317")
    @ExcelProperty("部件ID，唯一编码，UUID生成")
    private String monCompId;

    @Schema(description = "部件标识码，按18位规则生成，如110101010200100001；监测部件标识码规则表(sys_mon_comp_code_rule)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("部件标识码，按18位规则生成，如110101010200100001；监测部件标识码规则表(sys_mon_comp_code_rule)")
    private String compCode;

    @Schema(description = "部件名称，关联分类名称，如“燃气压力传感器”；监测部件分类配置表(sys_mon_comp_cat)", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("部件名称，关联分类名称，如“燃气压力传感器”；监测部件分类配置表(sys_mon_comp_cat)")
    private String compName;

    @Schema(description = "部件分类ID，关联监测部件分类表小类ID；监测部件分类配置表(sys_mon_comp_cat)", requiredMode = Schema.RequiredMode.REQUIRED, example = "13998")
    @ExcelProperty("部件分类ID，关联监测部件分类表小类ID；监测部件分类配置表(sys_mon_comp_cat)")
    private String compCatId;

    @Schema(description = "主管部门代码，主管部门统一社会信用代码；部门信息表(sys_org)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主管部门代码，主管部门统一社会信用代码；部门信息表(sys_org)")
    private String deptCode;

    @Schema(description = "主管部门名称，与部门代码同步，自动填充；部门信息表(sys_org)", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("主管部门名称，与部门代码同步，自动填充；部门信息表(sys_org)")
    private String deptName;

    @Schema(description = "所在行政区域代码，符合GB/T 2260，县级行政区域代码；行政区划表(sys_area)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所在行政区域代码，符合GB/T 2260，县级行政区域代码；行政区划表(sys_area)")
    private String regionCode;

    @Schema(description = "所在行政区域名称，与行政代码同步，自动填充；行政区划表(sys_area)", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("所在行政区域名称，与行政代码同步，自动填充；行政区划表(sys_area)")
    private String regionName;

    @Schema(description = "所在网格ID，关联单元网格ID；单元网格划分表(biz_unit_grid_div)", requiredMode = Schema.RequiredMode.REQUIRED, example = "14167")
    @ExcelProperty("所在网格ID，关联单元网格ID；单元网格划分表(biz_unit_grid_div)")
    private String gridId;

    @Schema(description = "所在网格名称，与网格ID同步，自动填充；单元网格划分表(biz_unit_grid_div)", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("所在网格名称，与网格ID同步，自动填充；单元网格划分表(biz_unit_grid_div)")
    private String gridName;

    @Schema(description = "坐标X，经度，2000国家大地坐标系", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("坐标X，经度，2000国家大地坐标系")
    private BigDecimal coordX;

    @Schema(description = "坐标Y，纬度，2000国家大地坐标系", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("坐标Y，纬度，2000国家大地坐标系")
    private BigDecimal coordY;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}