package cn.iocoder.yudao.module.evaluate.controller.admin.tasktemplate.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 评价任务模板分页 Request VO")
@Data
public class TaskTemplatePageReqVO extends PageParam {

    @Schema(description = "评价任务模板ID（UUID）", example = "29228")
    private String templateId;

    @Schema(description = "模板名称", example = "赵六")
    private String name;

    @Schema(description = "模板编码")
    private String code;

    @Schema(description = "适用对象类型ID（关联sys_object_type.type_id）", example = "8538")
    private String objectTypeId;

    @Schema(description = "关联指标体系ID（关联eval_index_system.system_id）", example = "20800")
    private String systemId;

    @Schema(description = "评价主体ID（关联eval_subject.subject_id）", example = "30157")
    private String subjectId;

    @Schema(description = "任务周期ID（关联sys_cycle_type.type_id）", example = "13209")
    private String cycleTypeId;

    @Schema(description = "描述信息")
    private String description;

    @Schema(description = "使用次数", example = "32234")
    private Integer useCount;

    @Schema(description = "最近使用时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastUseTime;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "7893")
    private Integer statusId;

    @Schema(description = "创建人ID（关联sys_user.user_id）")
    private String createBy;

    @Schema(description = "更新人ID（关联sys_user.user_id）")
    private String updateBy;

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

}