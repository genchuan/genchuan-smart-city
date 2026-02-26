package cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.river;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 河道分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RiverPageReqVO extends PageParam {

    @Schema(description = "主键（UUID）", example = "30212")
    private String riverId;

    @Schema(description = "河道名称", example = "赵六")
    private String name;

    @Schema(description = "责任河段")
    private String responsibilitySection;

    @Schema(description = "关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "河道长度，单位：公里")
    private BigDecimal length;

    @Schema(description = "关联sys_user.id", example = "24833")
    private String managerId;

    @Schema(description = "关联sys_operation_status.id", example = "8849")
    private String operationStatusId;

    @Schema(description = "保洁覆盖率")
    private BigDecimal cleaningCoverage;

    @Schema(description = "水质达标率")
    private BigDecimal waterQualityRate;

    @Schema(description = "垃圾打捞总量（单位：吨）")
    private BigDecimal wasteFishingVolume;

    @Schema(description = "问题办结率")
    private BigDecimal problemCompleteRate;

    @Schema(description = "关联sys_cleaning_type.id", example = "28471")
    private String cleaningTypeId;

    @Schema(description = "保洁频次")
    private String waterCleaningFrequency;

    @Schema(description = "保洁时段")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private String[] cleaningTime;

    @Schema(description = "负责人员IDs，JSON")
    private String staffIds;

    @Schema(description = "保洁工具IDs，JSON")
    private String toolIds;

    @Schema(description = "垃圾打捞预估量（单位：吨）")
    private BigDecimal wasteFishingEstimate;

    @Schema(description = "关联sys_monitor_type.id", example = "3153")
    private String monitorTypeId;

    @Schema(description = "监测周期")
    private String waterQualityCycle;

    @Schema(description = "监测指标，JSON")
    private String monitorIndicators;

    @Schema(description = "关联sys_user.id")
    private String monitorBy;

    @Schema(description = "计划监测时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] planMonitorTime;

    @Schema(description = "关联sys_monitor_status.id", example = "8944")
    private String monitorStatusId;

    @Schema(description = "上次监测时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastMonitorTime;

    @Schema(description = "下次监测提醒时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] nextMonitorRemindTime;

    @Schema(description = "监测数据达标率")
    private BigDecimal monitorDataQualifiedRate;

    @Schema(description = "预警次数", example = "28796")
    private Integer warningCount;

    @Schema(description = "关联sys_problem_type.id", example = "4445")
    private String problemTypeId;

    @Schema(description = "问题位置")
    private String problemLocation;

    @Schema(description = "问题描述")
    private String problemDesc;

    @Schema(description = "关联sys_user.id")
    private String reportBy;

    @Schema(description = "上报时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] reportTime;

    @Schema(description = "现场照片/视频URL", example = "https://www.iocoder.cn")
    private String problemMediaUrl;

    @Schema(description = "关联sys_dept.id", example = "16969")
    private String deptId;

    @Schema(description = "关联sys_user.id")
    private String handleBy;

    @Schema(description = "派单时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] dispatchTime;

    @Schema(description = "关联sys_handle_status.id", example = "12805")
    private String handleStatusId;

    @Schema(description = "超时提醒：是/否")
    private String isTimeout;

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

    @Schema(hidden = true)
    private Integer offset;

    @Schema(hidden = true)
    private Integer limit;

    /**
     * 设置分页偏移量和每页大小
     */
    public void setOffset(Integer pageNo, Integer pageSize) {
        if (pageNo != null && pageSize != null && pageNo > 0) {
            this.offset = (pageNo - 1) * pageSize;
            this.limit = pageSize;
        }
    }

    /**
     * 获取分页起始位置
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * 获取分页大小
     */
    public Integer getLimit() {
        return limit != null ? limit : getPageSize();
    }
}