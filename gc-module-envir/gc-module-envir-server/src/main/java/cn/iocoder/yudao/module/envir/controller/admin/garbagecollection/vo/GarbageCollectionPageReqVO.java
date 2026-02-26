package cn.iocoder.yudao.module.envir.controller.admin.garbagecollection.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 收运计划分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GarbageCollectionPageReqVO extends PageParam {

    @Schema(description = "业务主键（UUID）", example = "8139")
    private String garbageCollectionId;

    @Schema(description = "收运计划单编号")
    private String planNo;

    @Schema(description = "收运品类（关联sys_garbage_type.id）", example = "14268")
    private String garbageTypeId;

    @Schema(description = "收运区域（关联sys_area.area_code）")
    private String areaCode;

    @Schema(description = "收运点位ID，多个用逗号分隔")
    private String pointIds;

    @Schema(description = "收运频次")
    private String frequency;

    @Schema(description = "收运时段")
    private String timePeriod;

    @Schema(description = "负责车辆（关联sys_vehicle.sys_vehicle_id）", example = "24748")
    private String vehicleId;

    @Schema(description = "负责人员，多个用逗号分隔")
    private String staffIds;

    @Schema(description = "计划状态（关联sys_plan_status.sys_plan_status_id）", example = "20227")
    private String planStatusId;

    @Schema(description = "业务创建人（关联sys_user.id）")
    private String abnormalCreateBy;

    @Schema(description = "业务创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] abnormalCreateTime;

    @Schema(description = "业务更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] abnormalUpdateTime;

    @Schema(description = "完成率")
    private BigDecimal completionRate;

    @Schema(description = "异常记录数", example = "23735")
    private Integer abnormalCount;

    @Schema(description = "异常处置明细ID，多个用逗号分隔")
    private String abnormalDetailIds;

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
     * 获取每页大小
     */
    public Integer getLimit() {
        return limit != null ? limit : getPageSize();
    }
}