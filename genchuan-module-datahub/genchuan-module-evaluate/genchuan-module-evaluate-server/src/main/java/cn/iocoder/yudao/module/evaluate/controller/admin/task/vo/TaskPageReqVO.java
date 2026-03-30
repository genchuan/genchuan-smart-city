package cn.iocoder.yudao.module.evaluate.controller.admin.task.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 评价任务分页 Request VO")
@Data
public class TaskPageReqVO extends PageParam {

    @Schema(description = "评价任务ID（UUID）", example = "31646")
    private String taskId;

    @Schema(description = "任务名称", example = "张三")
    private String name;

    @Schema(description = "任务编码")
    private String code;

    @Schema(description = "关联模板ID（关联eval_task_template.template_id）", example = "13278")
    private String templateId;

    @Schema(description = "评价对象范围ID（关联sys_scope.scope_id）", example = "19652")
    private String scopeId;

    @Schema(description = "任务开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] startTime;

    @Schema(description = "任务结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] endTime;

    @Schema(description = "数据采集方式ID（关联sys_collect_type.type_id）", example = "29401")
    private String collectTypeId;

    @Schema(description = "评价对象ID（关联eval_object.object_id）",example = "obj001-1111-1111-1111-111111111111")
    private String objectId;

    @Schema(description = "总对象数", example = "22507")
    private Integer totalCount;

    @Schema(description = "已完成对象数", example = "10401")
    private Integer completedCount;

    @Schema(description = "完成率（如100.00）")
    private BigDecimal completionRate;

    @Schema(description = "任务状态ID（关联sys_task_status.status_id）", example = "27438")
    private Integer statusId;

    @Schema(description = "创建人ID（关联sys_user.user_id）")
    private String createBy;

    @Schema(description = "创建时间（业务字段）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bizCreateTime;

    @Schema(description = "更新时间（业务字段）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bizUpdateTime;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "评价对象范围")
    private String objectScope;

    @Schema(description = "原结束时间")
    private LocalDateTime[] originalEndTime;

    @Schema(description = "未完成对象数")
    private Integer uncompletedObject;

    @Schema(description = "取消原因")
    private String cancelReason;

    @Schema(description = "取消时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] cancelTime;

    @Schema(description = "取消操作人，关联sys_user.user_id")
    private String cancelBy;
}