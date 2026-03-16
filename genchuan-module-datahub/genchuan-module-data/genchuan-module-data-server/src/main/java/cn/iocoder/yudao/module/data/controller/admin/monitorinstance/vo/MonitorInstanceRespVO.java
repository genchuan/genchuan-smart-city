package cn.iocoder.yudao.module.data.controller.admin.monitorinstance.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 监测部件实例 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MonitorInstanceRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "部件名称")
    @ExcelProperty("部件名称")
    private String name;

    @Schema(description = "18位标识码")
    @ExcelProperty("18位标识码")
    private String uniqueCode;

    @Schema(description = "关联分类ID")
    @ExcelProperty("关联分类ID")
    private String categoryId;

    @Schema(description = "所属分类")
    @ExcelProperty("所属分类")
    private String categoryName;

    @Schema(description = "关联网格ID")
    @ExcelProperty("关联网格ID")
    private String gridId;

    @Schema(description = "所在网格")
    @ExcelProperty("所在网格")
    private String gridName;

    @Schema(description = "坐标信息")
    @ExcelProperty("坐标信息")
    private String coordinate;

    @Schema(description = "运行状态")
    @ExcelProperty("运行状态")
    private String runStatus;

    @Schema(description = "协议类型")
    @ExcelProperty("协议类型")
    private String protocol;

    @Schema(description = "安装时间")
    @ExcelProperty("安装时间")
    private LocalDateTime installTime;

    @Schema(description = "校准周期（天）")
    @ExcelProperty("校准周期（天）")
    private Integer calibrateCycle;

    @Schema(description = "下次校准时间")
    @ExcelProperty("下次校准时间")
    private LocalDateTime nextCalibrateTime;

    @Schema(description = "最后校准时间")
    @ExcelProperty("最后校准时间")
    private LocalDateTime lastCalibrateTime;

    @Schema(description = "最后数据质量校验时间")
    @ExcelProperty("最后数据质量校验时间")
    private LocalDateTime lastCheckTime;

    @Schema(description = "行政区划编码")
    @ExcelProperty("行政区划编码")
    private String areaCode;

    @Schema(description = "行政区划归属")
    @ExcelProperty("行政区划归属")
    private String areaName;

    @Schema(description = "关联管理部件ID")
    @ExcelProperty("关联管理部件ID")
    private String relatedPartId;

    @Schema(description = "关联管理部件")
    @ExcelProperty("关联管理部件")
    private String relatedPartName;

    @Schema(description = "校准日志")
    @ExcelProperty("校准日志")
    private String calibrateLog;

    @Schema(description = "备注")
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