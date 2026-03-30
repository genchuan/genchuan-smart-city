package cn.iocoder.yudao.module.data.controller.admin.eventcategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 监测事件分类新增/修改 Request VO")
@Data
public class EventCategorySaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

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

}