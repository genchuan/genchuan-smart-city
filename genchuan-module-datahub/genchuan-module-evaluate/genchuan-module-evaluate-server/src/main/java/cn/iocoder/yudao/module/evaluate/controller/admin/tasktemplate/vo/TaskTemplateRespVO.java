package cn.iocoder.yudao.module.evaluate.controller.admin.tasktemplate.vo;


import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 评价任务模板 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TaskTemplateRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26143")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "评价任务模板ID（UUID）", example = "29228")
    @ExcelProperty("评价任务模板ID（UUID）")
    private String templateId;

    @Schema(description = "模板名称", example = "赵六")
    @ExcelProperty("模板名称")
    private String name;

    @Schema(description = "模板编码")
    @ExcelProperty("模板编码")
    private String code;

    @Schema(description = "适用对象类型ID（关联sys_object_type.type_id）", example = "8538")
    @ExcelProperty("适用对象类型ID（关联sys_object_type.type_id）")
    private String objectTypeId;

    @Schema(description = "关联指标体系ID（关联eval_index_system.system_id）", example = "20800")
    @ExcelProperty("关联指标体系ID（关联eval_index_system.system_id）")
    private String systemId;

    @Schema(description = "评价主体ID（关联eval_subject.subject_id）", example = "30157")
    @ExcelProperty("评价主体ID（关联eval_subject.subject_id）")
    private String subjectId;

    @Schema(description = "任务周期ID（关联sys_cycle_type.type_id）", example = "13209")
    @ExcelProperty("任务周期ID（关联sys_cycle_type.type_id）")
    private String cycleTypeId;

    @Schema(description = "描述信息")
    @ExcelProperty("描述信息")
    private String description;

    @Schema(description = "使用次数", example = "32234")
    @ExcelProperty("使用次数")
    private Integer useCount;

    @Schema(description = "最近使用时间")
    @ExcelProperty("最近使用时间")
    private LocalDateTime lastUseTime;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "7893")
    @ExcelProperty("状态ID（关联sys_status.status_id）")
    private Integer statusId;

    @Schema(description = "创建人ID（关联sys_user.user_id）")
    @ExcelProperty("创建人ID（关联sys_user.user_id）")
    private String createBy;

    @Schema(description = "更新人ID（关联sys_user.user_id）")
    @ExcelProperty("更新人ID（关联sys_user.user_id）")
    private String updateBy;

    @Schema(description = "创建时间（业务字段）")
    @ExcelProperty("创建时间（业务字段）")
    private LocalDateTime bizCreateTime;

    @Schema(description = "更新时间（业务字段）")
    @ExcelProperty("更新时间（业务字段）")
    private LocalDateTime bizUpdateTime;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;
//----------------------------------新增出参
// ===== 新增关联表名称类展示字段（核心）=====
    @Schema(description = "适用对象类型名称（关联sys_object_type.name）", example = "部门")
    @ExcelProperty("适用对象类型")
    private String objectTypeName; // 对应sys_object_type.name

    @Schema(description = "关联指标体系名称（关联eval_index_system.name）", example = "通用评价体系")
    @ExcelProperty("关联指标体系")
    private String indexSystemName; // 对应eval_index_system.name

    @Schema(description = "评价主体名称（关联eval_subject.name）", example = "员工")
    @ExcelProperty("评价主体")
    private String subjectName; // 对应eval_subject.name

    @Schema(description = "任务周期名称（关联sys_cycle_type.name）", example = "年度")
    @ExcelProperty("任务周期")
    private String cycleTypeName; // 对应sys_cycle_type.name

    @Schema(description = "状态名称（关联sys_status.name）", example = "启用")
    @ExcelProperty("状态")
    private String statusName; // 对应sys_status.name

    @Schema(description = "创建人名称（关联sys_user.user_name）", example = "张三")
    @ExcelProperty("创建人")
    private String createUserName; // 对应sys_user.user_name
}
