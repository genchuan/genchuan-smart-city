package cn.iocoder.yudao.module.data.controller.admin.eventcategory.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 监测事件分类分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EventCategoryPageReqVO extends PageParam {

    @Schema(description = "分类名称")
    private String categoryName;

    @Schema(description = "分类代码")
    private String categoryCode;

    @Schema(description = "上级分类ID")
    private String parentId;

    @Schema(description = "上级分类名称")
    private String parentCategory;

    @Schema(description = "关联监测分类ID")
    private String monitorTypeId;

    @Schema(description = "监测部件类型")
    private String relatedMonitorType;

    @Schema(description = "管理事项类型ID")
    private String matterTypeId;

    @Schema(description = "管理事项类型")
    private String relatedMatterType;

    @Schema(description = "事件等级")
    private String eventLevel;

    @Schema(description = "推送规则")
    private String pushRule;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "审核状态")
    private String auditStatus;

    @Schema(description = "关联实例数")
    private Integer instanceCount;

    @Schema(description = "用途说明")
    private String purpose;

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

    // 新增字段：树形查询参数
    @Schema(description = "树形查询的父节点ID（点击树节点时传入，会查询该节点及其所有子节点）", example = "1")
    private String treeParentId;

    @Schema(description = "是否包含父节点自身（当treeParentId不为空时有效，默认true）", example = "true")
    private Boolean includeSelf = true;
}