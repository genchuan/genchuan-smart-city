package cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompdatacfg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 监测部件数据配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MonCompDataCfgRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17337")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "配置ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "16316")
    @ExcelProperty("配置ID，唯一编码，UUID生成")
    private String monCompDataId;

    @Schema(description = "部件分类ID，关联监测部件分类表小类ID；监测部件分类配置表(sys_mon_comp_cat)", requiredMode = Schema.RequiredMode.REQUIRED, example = "5330")
    @ExcelProperty("部件分类ID，关联监测部件分类表小类ID；监测部件分类配置表(sys_mon_comp_cat)")
    private String compCatId;

    @Schema(description = "部件分类名称，与分类ID同步，自动填充；监测部件分类配置表(sys_mon_comp_cat)", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("部件分类名称，与分类ID同步，自动填充；监测部件分类配置表(sys_mon_comp_cat)")
    private String compCatName;

    @Schema(description = "数据类型，标识数据类型，空间对应坐标等，属性对应字段信息", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("数据类型，标识数据类型，空间对应坐标等，属性对应字段信息")
    private String dataType;

    @Schema(description = "坐标系类型，条件必选(数据类型为空间数据时)，固定为2000国家大地坐标系，不可修改")
    @ExcelProperty("坐标系类型，条件必选(数据类型为空间数据时)，固定为2000国家大地坐标系，不可修改")
    private String coordSystem;

    @Schema(description = "定位精度级别，条件必选(数据类型为空间数据时)，A类(±0.5m)、B类(±1.0m)，参照GB/T XXXXX.6")
    @ExcelProperty("定位精度级别，条件必选(数据类型为空间数据时)，A类(±0.5m)、B类(±1.0m)，参照GB/T XXXXX.6")
    private String accuracyLevel;

    @Schema(description = "高程基准，条件必选(数据类型为空间数据时)，固定为1985国家高程基准，不可修改")
    @ExcelProperty("高程基准，条件必选(数据类型为空间数据时)，固定为1985国家高程基准，不可修改")
    private String elevationDatum;

    @Schema(description = "字段名称，条件必选（数据类型为属性数据时），部件标识码/主管部门代码等", example = "张三")
    @ExcelProperty("字段名称，条件必选（数据类型为属性数据时），部件标识码/主管部门代码等")
    private String fieldName;

    @Schema(description = "字段代码，条件必选（数据类型为属性数据时），采用蛇形命名法，与数据库字段一致")
    @ExcelProperty("字段代码，条件必选（数据类型为属性数据时），采用蛇形命名法，与数据库字段一致")
    private String fieldCode;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}