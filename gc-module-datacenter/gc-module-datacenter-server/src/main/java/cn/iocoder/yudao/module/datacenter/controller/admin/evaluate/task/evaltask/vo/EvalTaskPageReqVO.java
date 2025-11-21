package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.task.evaltask.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 任务管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvalTaskPageReqVO extends PageParam {

    @Schema(description = "任务ID")
    private String evalTaskId;

    @Schema(description = "任务名称")
    private String evalTaskName;

    @Schema(description = "任务编码")
    private String evalTaskCode;

    @Schema(description = "关联模板ID")
    private String taskTemplateId;

    @Schema(description = "关联模板名称")
    private String taskTemplateName;

    @Schema(description = "评价对象范围")
    private String objectScope;

    @Schema(description = "关联对象IDs")
    private String relateObjectIds;

    @Schema(description = "关联对象Names")
    private String relateObjectNames;

    @Schema(description = "任务开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] taskStartTime;

    @Schema(description = "任务结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] taskEndTime;

    @Schema(description = "任务状态")
    private String taskStatus;

    @Schema(description = "数据采集方式")
    private String dataCollectMethod;

    @Schema(description = "任务描述")
    private String taskDesc;

    @Schema(description = "创建人(业务)")
    private String createUserBiz;

    @Schema(description = "创建时间(业务)")
    private LocalDateTime createTimeBiz;

    @Schema(description = "更新人(业务)")
    private String updateUserBiz;

    @Schema(description = "更新时间(业务)")
    private LocalDateTime updateTimeBiz;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}