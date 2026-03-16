package cn.iocoder.yudao.module.data.controller.admin.mattercategory.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 管理事项分类分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MatterCategoryPageReqVO extends PageParam {

    @Schema(description = "管理事项分类ID", example = "18735")
    private String matterCategoryId;

    @Schema(description = "分类名称", example = "张三")
    private String categoryName;

    @Schema(description = "分类代码")
    private String categoryCode;

    @Schema(description = "上级分类ID", example = "10662")
    private String parentId;

    @Schema(description = "上级分类名称", example = "张三")
    private String parentName;

    @Schema(description = "主管部门ID", example = "15094")
    private String deptId;

    @Schema(description = "主管部门名称", example = "芋艿")
    private String deptName;

    @Schema(description = "处置时限（小时）")
    private Integer dealLimit;

    @Schema(description = "工作流ID", example = "4355")
    private String workflowId;

    @Schema(description = "工作流编码")
    private String workflowCode;

    @Schema(description = "工作流描述")
    private String workflowDesc;

    @Schema(description = "分类类型ID", example = "20172")
    private String categoryTypeId;

    @Schema(description = "分类类型名称", example = "芋艿")
    private String categoryType;

    @Schema(description = "状态", example = "芋艿")
    private String status;

    @Schema(description = "审核状态", example = "李四")
    private String auditStatus;

    @Schema(description = "关联事项数", example = "31735")
    private Integer relatedMatterCount;

    @Schema(description = "用途说明")
    private String purpose;

    @Schema(description = "备注", example = "随便")
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

    @Schema(description = "树形查询的父节点ID（点击树节点时传入，会查询该节点及其所有子节点）", example = "CAT001")
    private String treeParentId;

    @Schema(description = "是否包含父节点自身（当treeParentId不为空时有效，默认true）", example = "true")
    private Boolean includeSelf = true;
}