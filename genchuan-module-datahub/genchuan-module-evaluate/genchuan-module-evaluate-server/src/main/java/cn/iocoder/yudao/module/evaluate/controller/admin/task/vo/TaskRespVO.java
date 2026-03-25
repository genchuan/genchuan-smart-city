package cn.iocoder.yudao.module.evaluate.controller.admin.task.vo;


import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 评价任务 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TaskRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12873")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "评价任务ID（UUID）", example = "31646")
    @ExcelProperty("评价任务ID（UUID）")
    private String taskId;

    @Schema(description = "任务名称", example = "张三")
    @ExcelProperty("任务名称")
    private String name;

    @Schema(description = "任务编码")
    @ExcelProperty("任务编码")
    private String code;

    @Schema(description = "关联模板ID（关联eval_task_template.template_id）", example = "13278")
    @ExcelProperty("关联模板ID（关联eval_task_template.template_id）")
    private String templateId;

    @Schema(description = "评价对象范围ID（关联sys_scope.scope_id）", example = "19652")
    @ExcelProperty("评价对象范围ID（关联sys_scope.scope_id）")
    private String scopeId;

    @Schema(description = "任务开始时间")
    @ExcelProperty("任务开始时间")
    private LocalDateTime startTime;

    @Schema(description = "任务结束时间")
    @ExcelProperty("任务结束时间")
    private LocalDateTime endTime;

    @Schema(description = "数据采集方式ID（关联sys_collect_type.type_id）", example = "29401")
    @ExcelProperty("数据采集方式ID（关联sys_collect_type.type_id）")
    private String collectTypeId;

    @Schema(description = "评价对象ID（关联eval_object.object_id）",example = "obj001-1111-1111-1111-111111111111")
    @ExcelProperty("评价对象ID（关联eval_object.object_id）")
    private String objectId;

    @Schema(description = "总对象数", example = "22507")
    @ExcelProperty("总对象数")
    private Integer totalCount;

    @Schema(description = "已完成对象数", example = "10401")
    @ExcelProperty("已完成对象数")
    private Integer completedCount;

    @Schema(description = "完成率（如100.00）")
    @ExcelProperty("完成率（如100.00）")
    private BigDecimal completionRate;

    @Schema(description = "任务状态ID（关联sys_task_status.status_id）", example = "27438")
    @ExcelProperty("任务状态ID（关联sys_task_status.status_id）")
    private Integer statusId;

    @Schema(description = "创建人ID（关联sys_user.user_id）")
    @ExcelProperty("创建人ID（关联sys_user.user_id）")
    private String createBy;

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

    @Schema(description = "评价对象范围")
    @ExcelProperty("评价对象范围")

    private String objectScope;

    @Schema(description = "原结束时间")
    @ExcelProperty("原结束时间")
    private LocalDateTime originalEndTime;

    @Schema(description = "未完成对象数")
    @ExcelProperty("未完成对象数")

    private Integer uncompletedObject;

    @Schema(description = "取消原因")
    @ExcelProperty("取消原因")

    private String cancelReason;

    @Schema(description = "取消时间")
    @ExcelProperty("取消时间")
    private LocalDateTime cancelTime;

    @Schema(description = "取消操作人，关联sys_user.user_id")
    @ExcelProperty("取消操作人，关联sys_user.user_id")
    private String cancelBy;

    // ========== 新增关联表名称字段（核心补充） ==========
    @Schema(description = "关联模板名称", example = "年度员工评价模板")
    @ExcelProperty("关联模板名称")
    private String templateName; // 关联eval_task_template.name

    @Schema(description = "评价对象范围名称", example = "全公司")
    @ExcelProperty("评价对象范围名称")
    private String scopeName; // 关联sys_scope.name

    @Schema(description = "自定义对象名称列表（多选）", example = "[\"张三\",\"李四\"]")
    @ExcelProperty("自定义对象名称")
    private List<String> objectName; // 关联eval_object.name（多选）

    @Schema(description = "数据采集方式名称", example = "手动采集")
    @ExcelProperty("数据采集方式名称")
    private String collectTypeName; // 关联sys_collect_type.name

    @Schema(description = "任务状态名称", example = "已完成")
    @ExcelProperty("任务状态名称")
    private String statusName; // 关联sys_task_status.name

    @Schema(description = "创建人名称", example = "管理员")
    @ExcelProperty("创建人名称")
    private String createUserName; // 关联sys_user.user_name

}
