package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspect.evalinspectplan.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 考察计划管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvalInspectPlanPageReqVO extends PageParam {

    @Schema(description = "计划ID")
    private String inspectPlanId;

    @Schema(description = "计划名称")
    private String inspectPlanName;

    @Schema(description = "计划编码")
    private String inspectPlanCode;

    @Schema(description = "关联任务ID")
    private String evalTaskId;

    @Schema(description = "关联任务名称")
    private String evalTaskName;

    @Schema(description = "考察对象ID")
    private String evalObjectId;

    @Schema(description = "考察对象名称")
    private String evalObjectName;

    @Schema(description = "考察人员ID")
    private String inspectorId;

    @Schema(description = "考察人员姓名")
    private String inspectorName;

    @Schema(description = "考察时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] inspectTime;

    @Schema(description = "考察方式")
    private String inspectMethod;

    @Schema(description = "考察内容")
    private String inspectContent;

    @Schema(description = "计划状态")
    private String planStatus;

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