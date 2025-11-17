package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.assign.inspecttaskallocaterec.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 巡查巡检任务分配记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InspectTaskAllocateRecPageReqVO extends PageParam {

    @Schema(description = "分配ID")
    private String allocateId;

    @Schema(description = "任务ID")
    private String taskId;

    @Schema(description = "任务编码")
    private String taskCode;

    @Schema(description = "原负责人员ID")
    private String oldPersonId;

    @Schema(description = "原负责人员姓名")
    private String oldPersonName;

    @Schema(description = "新负责人员ID")
    private String newPersonId;

    @Schema(description = "新负责人员姓名")
    private String newPersonName;

    @Schema(description = "分配原因")
    private String allocateReason;

    @Schema(description = "分配时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] allocateTime;

    @Schema(description = "分配人")
    private String allocateUser;

    @Schema(description = "任务状态变更")
    private String taskStatusChange;

    @Schema(description = "任务类型")
    private String taskType;

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