package cn.iocoder.yudao.module.data.controller.admin.sceneinstance.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 应用场景实例 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SceneInstanceRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "场景名称")
    @ExcelProperty("场景名称")
    private String sceneName;

    @Schema(description = "场景编码")
    @ExcelProperty("场景编码")
    private String sceneCode;

    @Schema(description = "关联场景分类ID")
    @ExcelProperty("关联场景分类ID")
    private String categoryId;

    @Schema(description = "所属分类")
    @ExcelProperty("所属分类")
    private String categoryName;

    @Schema(description = "所在网格ID列表")
    @ExcelProperty("所在网格ID列表")
    private String gridIds;

    @Schema(description = "所在网格")
    @ExcelProperty("所在网格")
    private String gridName;

    @Schema(description = "涉及设施ID列表")
    @ExcelProperty("涉及设施ID列表")
    private String facilityIds;

    @Schema(description = "涉及设施")
    @ExcelProperty("涉及设施")
    private String facilities;

    @Schema(description = "关联监测部件ID列表")
    @ExcelProperty("关联监测部件ID列表")
    private String monitorIds;

    @Schema(description = "关联监测部件")
    @ExcelProperty("关联监测部件")
    private String monitorName;

    @Schema(description = "关联监测事件类型ID列表")
    @ExcelProperty("关联监测事件类型ID列表")
    private String eventTypeIds;

    @Schema(description = "关联监测事件类型")
    @ExcelProperty("关联监测事件类型")
    private String eventType;

    @Schema(description = "关联资产设备ID列表")
    @ExcelProperty("关联资产设备ID列表")
    private String assetIds;

    @Schema(description = "负责人")
    @ExcelProperty("负责人")
    private String manager;

    @Schema(description = "处置流程")
    @ExcelProperty("处置流程")
    private String process;

    @Schema(description = "状态")
    @ExcelProperty("状态")
    private String status;

    @Schema(description = "配置触发标识")
    @ExcelProperty("配置触发标识")
    private Boolean configTriggerFlag;

    @Schema(description = "关联部件数")
    @ExcelProperty("关联部件数")
    private Integer partCount;

    @Schema(description = "关联事件数")
    @ExcelProperty("关联事件数")
    private Integer eventCount;

    @Schema(description = "启用/停用时间")
    @ExcelProperty("启用/停用时间")
    private LocalDateTime statusTime;

    @Schema(description = "运行日志")
    @ExcelProperty("运行日志")
    private String runLog;

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