package cn.iocoder.yudao.module.data.controller.admin.matterinstance.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 管理事项实例分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MatterInstancePageReqVO extends PageParam {

    @Schema(description = "管理事项实例ID", example = "9652")
    private String matterInstanceId;

    @Schema(description = "事项名称", example = "芋艿")
    private String name;

    @Schema(description = "16位标识码")
    private String uniqueCode;

    @Schema(description = "所属分类ID", example = "9172")
    private String categoryId;

    @Schema(description = "所属分类名称", example = "芋艿")
    private String categoryName;

    @Schema(description = "上级分类ID", example = "22607")
    private String parentCategoryId;

    @Schema(description = "事发位置")
    private String location;

    @Schema(description = "所在网格ID", example = "799")
    private String gridId;

    @Schema(description = "所在网格名称", example = "赵六")
    private String gridName;

    @Schema(description = "描述信息", example = "你说的对")
    private String description;

    @Schema(description = "状态ID", example = "16708")
    private String statusId;

    @Schema(description = "状态名称", example = "李四")
    private String status;

    @Schema(description = "主管部门ID", example = "19737")
    private String deptId;

    @Schema(description = "主管部门名称", example = "王五")
    private String deptName;

    @Schema(description = "附件信息列表")
    private String attachmentInfo;

    @Schema(description = "关联管理部件ID列表")
    private String partIds;

    @Schema(description = "关联部件数", example = "29562")
    private Integer partCount;

    @Schema(description = "超时标识")
    private Boolean timeoutFlag;

    @Schema(description = "超时时长（分钟）")
    private Integer timeoutDuration;

    @Schema(description = "处置意见")
    private String dealOpinion;

    @Schema(description = "处置人ID")
    private String dealBy;

    @Schema(description = "处置人名称", example = "张三")
    private String handler;

    @Schema(description = "处置时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] dealTime;

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

    @Schema(description = "树形查询的父分类ID（点击树节点时传入，会查询属于该分类及其子分类下的事项实例）", example = "CAT001")
    private String treeParentId;

    @Schema(description = "是否包含父分类自身下的事项实例（当treeParentCategoryId不为空时有效，默认true）", example = "true")
    private Boolean includeSelf = true;

}