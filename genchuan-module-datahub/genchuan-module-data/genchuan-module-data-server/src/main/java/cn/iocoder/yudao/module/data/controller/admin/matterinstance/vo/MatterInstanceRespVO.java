package cn.iocoder.yudao.module.data.controller.admin.matterinstance.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 管理事项实例 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MatterInstanceRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3039")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "管理事项实例ID", example = "9652")
    @ExcelProperty("管理事项实例ID")
    private String matterInstanceId;

    @Schema(description = "事项名称", example = "芋艿")
    @ExcelProperty("事项名称")
    private String name;

    @Schema(description = "16位标识码")
    @ExcelProperty("16位标识码")
    private String uniqueCode;

    @Schema(description = "所属分类ID", example = "9172")
    @ExcelProperty("所属分类ID")
    private String categoryId;

    @Schema(description = "所属分类名称", example = "芋艿")
    @ExcelProperty("所属分类名称")
    private String categoryName;

    @Schema(description = "上级分类ID", example = "22607")
    @ExcelProperty("上级分类ID")
    private String parentCategoryId;

    @Schema(description = "事发位置")
    @ExcelProperty("事发位置")
    private String location;

    @Schema(description = "所在网格ID", example = "799")
    @ExcelProperty("所在网格ID")
    private String gridId;

    @Schema(description = "所在网格名称", example = "赵六")
    @ExcelProperty("所在网格名称")
    private String gridName;

    @Schema(description = "描述信息", example = "你说的对")
    @ExcelProperty("描述信息")
    private String description;

    @Schema(description = "状态ID", example = "16708")
    @ExcelProperty("状态ID")
    private String statusId;

    @Schema(description = "状态名称", example = "李四")
    @ExcelProperty("状态名称")
    private String status;

    @Schema(description = "主管部门ID", example = "19737")
    @ExcelProperty("主管部门ID")
    private String deptId;

    @Schema(description = "主管部门名称", example = "王五")
    @ExcelProperty("主管部门名称")
    private String deptName;

    @Schema(description = "附件信息列表")
    @ExcelProperty("附件信息列表")
    private String attachmentInfo;

    @Schema(description = "关联管理部件ID列表")
    @ExcelProperty("关联管理部件ID列表")
    private String partIds;

    @Schema(description = "关联部件数", example = "29562")
    @ExcelProperty("关联部件数")
    private Integer partCount;

    @Schema(description = "超时标识")
    @ExcelProperty("超时标识")
    private Boolean timeoutFlag;

    @Schema(description = "超时时长（分钟）")
    @ExcelProperty("超时时长（分钟）")
    private Integer timeoutDuration;

    @Schema(description = "处置意见")
    @ExcelProperty("处置意见")
    private String dealOpinion;

    @Schema(description = "处置人ID")
    @ExcelProperty("处置人ID")
    private String dealBy;

    @Schema(description = "处置人名称", example = "张三")
    @ExcelProperty("处置人名称")
    private String handler;

    @Schema(description = "处置时间")
    @ExcelProperty("处置时间")
    private LocalDateTime dealTime;

    @Schema(description = "备注", example = "随便")
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