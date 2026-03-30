package cn.iocoder.yudao.module.data.controller.admin.monitorcategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 监测部件分类新增/修改 Request VO")
@Data
public class MonitorCategorySaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "分类代码")
    private String categoryCode;

    @Schema(description = "上级分类ID", example = "1")
    private String parentId;

    @Schema(description = "上级分类名称")
    private String parentCategory;

    @Schema(description = "核心监测指标")
    private String coreIndicators;

    @Schema(description = "告警阈值规则")
    private String thresholdRules;

    @Schema(description = "分类类型", example = "2")
    private String categoryType;

    @Schema(description = "状态", example = "1")
    private String status;

    @Schema(description = "审核状态", example = "0")
    private String auditStatus;

    @Schema(description = "关联实例数")
    private Integer instanceCount;

    @Schema(description = "用途说明")
    private String purpose;

    @Schema(description = "分类变更通知标识")
    private Boolean notifyFlag;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建人")
    private String creator;
}