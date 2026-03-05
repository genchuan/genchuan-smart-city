package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "环境卫生管理 - 收运计划分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GarbageCollectionPageReqVO extends PageParam {

    @Schema(description = "收运计划主键（UUID）", example = "8255")
    private String collectionId;

    @Schema(description = "收运计划单编号")
    private String planNo;

    @Schema(description = "关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "关联sys_garbage_type.id", example = "16464")
    private String garbageTypeId;

    @Schema(description = "收运频次")
    private String frequency;

    @Schema(description = "收运时段")
    private String timePeriod;

    @Schema(description = "关联sys_vehicle.id", example = "28972")
    private String vehicleId;

    @Schema(description = "负责人员IDs，JSON")
    private String staffIds;

    @Schema(description = "收运点位IDs，JSON")
    private String pointIds;

    @Schema(description = "关联sys_plan_status.id", example = "26053")
    private String planStatusId;

    @Schema(description = "完成率")
    private BigDecimal completionRate;

    @Schema(description = "异常记录数", example = "21604")
    private Integer abnormalCount;

    @Schema(description = "收运计划创建时间（原create_time）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] abnormalCreateTime;

    @Schema(description = "收运计划更新时间（原update_time）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] abnormalUpdateTime;

    @Schema(description = "关联sys_user.id")
    private String createBy;

    @Schema(description = "系统创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "当前进度（按点位完成率计算）", example = "85.50")
    private BigDecimal progress;

    @Schema(description = "已收运量（实时上报累计）", example = "22.80")
    private BigDecimal collectedVolume;

    @Schema(description = "打卡状态：到岗/离岗", example = "到岗")
    private String checkinStatus;

    @Schema(description = "轨迹覆盖情况（系统自动校验）", example = "95%")
    private String trackCoverage;

    @Schema(description = "最新上报时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastReportTime;

    @Schema(description = "是否异常（系统自动标记）", example = "true")
    private Boolean isAbnormal;

    @Schema(description = "完成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] completeTime;

    @Schema(description = "总收运量", example = "100.50")
    private BigDecimal totalVolume;

    @Schema(description = "异常处置结果：无/已办结/部分办结", example = "已办结")
    private String abnormalResult;

    @Schema(description = "异常办结率（自动计算）", example = "98.00")
    private BigDecimal abnormalCompleteRate;

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