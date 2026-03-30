package cn.iocoder.yudao.module.data.controller.admin.sceneinstance.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 应用场景实例分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SceneInstancePageReqVO extends PageParam {

    @Schema(description = "场景名称")
    private String sceneName;

    @Schema(description = "场景编码")
    private String sceneCode;

    @Schema(description = "关联场景分类ID")
    private String categoryId;

    @Schema(description = "所属分类")
    private String categoryName;

    @Schema(description = "所在网格ID列表")
    private String gridIds;

    @Schema(description = "所在网格")
    private String gridName;

    @Schema(description = "涉及设施ID列表")
    private String facilityIds;

    @Schema(description = "涉及设施")
    private String facilities;

    @Schema(description = "关联监测部件ID列表")
    private String monitorIds;

    @Schema(description = "关联监测部件")
    private String monitorName;

    @Schema(description = "关联监测事件类型ID列表")
    private String eventTypeIds;

    @Schema(description = "关联监测事件类型")
    private String eventType;

    @Schema(description = "关联资产设备ID列表")
    private String assetIds;

    @Schema(description = "负责人")
    private String manager;

    @Schema(description = "处置流程")
    private String process;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "配置触发标识")
    private Boolean configTriggerFlag;

    @Schema(description = "关联部件数")
    private Integer partCount;

    @Schema(description = "关联事件数")
    private Integer eventCount;

    @Schema(description = "启用/停用时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] statusTime;

    @Schema(description = "运行日志")
    private String runLog;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建人")
    private String creator;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "树形查询的父节点ID（点击树节点时传入，会查询该节点及其所有子节点）", example = "1")
    private String treeParentId;

    @Schema(description = "是否包含父节点自身（当treeParentId不为空时有效，默认true）", example = "true")
    private Boolean includeSelf = true;
}