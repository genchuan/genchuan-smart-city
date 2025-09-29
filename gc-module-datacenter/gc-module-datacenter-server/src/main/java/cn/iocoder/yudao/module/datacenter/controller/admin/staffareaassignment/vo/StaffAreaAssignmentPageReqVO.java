package cn.iocoder.yudao.module.datacenter.controller.admin.staffareaassignment.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 人员区域分配分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StaffAreaAssignmentPageReqVO extends PageParam {

    @Schema(description = "分配ID")
    private String assignmentId;

    @Schema(description = "人员ID")
    private String staffId;

    @Schema(description = "人员姓名")
    private String staffName;

    @Schema(description = "区域ID")
    private String areaId;

    @Schema(description = "区域名称")
    private String areaName;

    @Schema(description = "分配类型")
    private String assignmentType;

    @Schema(description = "分配周期")
    private String assignmentCycle;

    @Schema(description = "生效时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] effectiveTime;

    @Schema(description = "失效时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] expiryTime;

    @Schema(description = "分配状态")
    private String assignmentStatus;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}