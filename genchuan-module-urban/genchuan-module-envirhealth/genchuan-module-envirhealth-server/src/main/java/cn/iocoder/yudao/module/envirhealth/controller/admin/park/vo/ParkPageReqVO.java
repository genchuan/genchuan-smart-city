package cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "环境卫生管理 - 公园分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkPageReqVO extends PageParam {

    @Schema(description = "主键（UUID）", example = "25807")
    private String parkId;

    @Schema(description = "公园名称", example = "李四")
    private String name;

    @Schema(description = "公园地址")
    private String address;

    @Schema(description = "关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "保洁频次")
    private String cleaningFrequency;

    @Schema(description = "绿化养护周期")
    private String greenMaintenanceCycle;

    @Schema(description = "关联sys_user.id", example = "7468")
    private String managerId;

    @Schema(description = "关联sys_operation_status.id", example = "28276")
    private String operationStatusId;

    @Schema(description = "保洁达标率")
    private BigDecimal cleaningRate;

    @Schema(description = "绿化存活率")
    private BigDecimal greenSurvivalRate;

    @Schema(description = "设施完好率")
    private BigDecimal facilityRate;

    @Schema(description = "环境达标率")
    private BigDecimal environmentRate;

    @Schema(description = "垃圾清运完成率")
    private BigDecimal wasteTransferCompleteRate;

    @Schema(description = "保洁区域")
    private String cleaningArea;

    @Schema(description = "保洁标准")
    private String cleaningStandard;

    @Schema(description = "负责人员IDs，JSON")
    private String staffIds;

    @Schema(description = "绿化品类IDs，JSON")
    private String greenTypeIds;

    @Schema(description = "养护区域")
    private String greenArea;

    @Schema(description = "养护内容")
    private String greenMaintenanceContent;

    @Schema(description = "养护人员IDs，JSON")
    private String greenStaffIds;

    @Schema(description = "垃圾收集点位")
    private Integer wasteCollectionPoints;

    @Schema(description = "垃圾清运频次")
    private String wasteTransferFrequency;

    @Schema(description = "清运时段")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private String[] wasteTransferTime;

    @Schema(description = "关联sys_vehicle.id", example = "11391")
    private String vehicleId;

    @Schema(description = "垃圾清运量（单位：吨）")
    private BigDecimal wasteVolume;

    @Schema(description = "设施类型IDs，JSON")
    private String facilityIds;

    @Schema(description = "设施位置")
    private String facilityLocation;

    @Schema(description = "损坏描述")
    private String facilityDamageDesc;

    @Schema(description = "关联sys_user.id")
    private String reportBy;

    @Schema(description = "上报时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] reportTime;

    @Schema(description = "上报照片URL，JSON", example = "[\"https://example.com/image1.jpg\", \"https://example.com/image2.jpg\", \"https://example.com/image3.jpg\"]")
    private String facilityPhotoUrl;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "计划状态ID（关联sys_plan_status.id）", example = "10001")
    private String planStatusId;

    @Schema(description = "任务类型ID（关联sys_task_type.id，对应sys_task_type.name）")
    private String taskTypeId;

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