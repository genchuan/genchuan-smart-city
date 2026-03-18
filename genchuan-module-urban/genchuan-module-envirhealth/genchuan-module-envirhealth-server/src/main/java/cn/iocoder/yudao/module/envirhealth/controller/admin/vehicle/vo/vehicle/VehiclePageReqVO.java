package cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehicle;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "环境卫生管理 - 车辆分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VehiclePageReqVO extends PageParam {

    @Schema(description = "业务主键（UUID）", example = "21313")
    private String sysVehicleId;

    @Schema(description = "车牌号码")
    private String licensePlate;

    @Schema(description = "车辆类型（关联sys_vehicle_type.sys_vehicle_type_id）", example = "16606")
    private String vehicleTypeId;

    @Schema(description = "车辆型号")
    private String model;

    @Schema(description = "所属部门（关联sys_dept.sys_dept_id）", example = "30395")
    private String deptId;

    @Schema(description = "作业路线（关联sys_route.sys_route_id）", example = "5158")
    private String routeId;

    @Schema(description = "维护周期（单位：天）")
    private Integer maintenanceCycle;

    @Schema(description = "驾驶员（关联sys_user.id）", example = "15996")
    private String driverId;

    @Schema(description = "车辆状态（关联sys_vehicle_status.sys_vehicle_status_id）", example = "17840")
    private String vehicleStatusId;

    @Schema(description = "业务创建人（关联sys_user.id）")
    private String createBy;

    @Schema(description = "业务创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] abnormalCreateTime;

    @Schema(description = "业务更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] abnormalUpdateTime;

    @Schema(description = "累计作业时长")
    private BigDecimal totalWorkHours;

    @Schema(description = "最近维护时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastMaintenanceTime;

    @Schema(description = "违规告警次数", example = "13424")
    private Integer alarmCount;

    @Schema(description = "车辆照片URL", example = "https://www.iocoder.cn")
    private String vehiclePhotoUrl;

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