package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.area.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 行政区划配置表 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AreaRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25790")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "上级行政区划ID（0表示根节点）", requiredMode = Schema.RequiredMode.REQUIRED, example = "25850")
    @ExcelProperty("上级行政区划ID（0表示根节点）")
    private Long parentId;

    @Schema(description = "完整行政区划代码（12位）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("完整行政区划代码（12位）")
    private String fullCode;

    @Schema(description = "短代码（省/市/县6位，乡镇/社区3位）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("短代码（省/市/县6位，乡镇/社区3位）")
    private String shortCode;

    @Schema(description = "社区ID", example = "13248")
    @ExcelProperty("社区ID")
    private String commId;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("行政区划名称")
    private String name;

    @Schema(description = "层级：1-省级 2-市级 3-县级 4-乡镇 5-社区", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("层级：1-省级 2-市级 3-县级 4-乡镇 5-社区")
    private Integer level;

    @Schema(description = "类型（街道/镇/乡/社区/村）", example = "2")
    @ExcelProperty("类型（街道/镇/乡/社区/村）")
    private String areaType;

    @Schema(description = "生效时间")
    @ExcelProperty("生效时间")
    private LocalDateTime effectiveTime;

    @Schema(description = "失效时间")
    @ExcelProperty("失效时间")
    private LocalDateTime invalidTime;

    @Schema(description = "社区边界坐标（2000坐标系）")
    @ExcelProperty("社区边界坐标（2000坐标系）")
    private String boundary;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
