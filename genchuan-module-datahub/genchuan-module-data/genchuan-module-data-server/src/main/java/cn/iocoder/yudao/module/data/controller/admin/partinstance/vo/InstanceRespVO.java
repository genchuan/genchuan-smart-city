package cn.iocoder.yudao.module.data.controller.admin.partinstance.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 管理部件实例 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InstanceRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17931")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "部件名称", example = "王五")
    @ExcelProperty("部件名称")
    private String partName; // 修改：与 DO 的 partName 对齐

    @Schema(description = "16位标识码")
    @ExcelProperty("16位标识码")
    private String uniqueCode;

    @Schema(description = "关联分类ID", example = "16262")
    @ExcelProperty("关联分类ID")
    private String parentCategoryId; // 修改：与 DO 的 parentCategoryId 对齐

    @Schema(description = "所属分类名称")
    @ExcelProperty("所属分类名称")
    private String categoryName; // 新增：分类名称

    @Schema(description = "关联网格ID", example = "17369")
    @ExcelProperty("关联网格ID")
    private String gridId;

    @Schema(description = "所在网格")
    @ExcelProperty("所在网格")
    private String gridName; // 新增：对应 DO 的 gridName

    @Schema(description = "经度")
    @ExcelProperty("经度")
    private BigDecimal longitude;

    @Schema(description = "纬度")
    @ExcelProperty("纬度")
    private BigDecimal latitude;

    @Schema(description = "坐标校验标识")
    @ExcelProperty("坐标校验标识")
    private Boolean coordVerifyFlag;

    @Schema(description = "坐标信息")
    @ExcelProperty("坐标信息")
    private String coordinate; // 新增：对应 DO 的 coordinate

    @Schema(description = "关联运行状态ID", example = "19277")
    @ExcelProperty("关联运行状态ID")
    private String runStatus; // 修改：与 DO 的 runStatus 对齐

    @Schema(description = "主管部门")
    @ExcelProperty("主管部门")
    private String deptName; // 修改：与 DO 的 deptName 对齐

    @Schema(description = "关联行政区划代码")
    @ExcelProperty("关联行政区划代码")
    private String areaCode;

    @Schema(description = "行政区划归属")
    @ExcelProperty("行政区划归属")
    private String areaName; // 新增：对应 DO 的 areaName

    @Schema(description = "关联监测部件ID列表")
    @ExcelProperty("关联监测部件ID列表")
    private String monitorIds;

    @Schema(description = "关联监测部件数", example = "4262")
    @ExcelProperty("关联监测部件数")
    private Integer monitorCount;

    @Schema(description = "关联事件数", example = "20441")
    @ExcelProperty("关联事件数")
    private Integer eventCount;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String creator;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}