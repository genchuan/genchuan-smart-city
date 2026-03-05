package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 转运作业分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TransferOperationPageReqVO extends PageParam {

    @Schema(description = "作业主键（UUID）", example = "21564")
    private String operationId;

    @Schema(description = "关联sys_vehicle.id", example = "18000")
    private String vehicleId;

    @Schema(description = "关联sys_garbage_type.id", example = "25851")
    private String garbageTypeId;

    @Schema(description = "进站时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] entryTime;

    @Schema(description = "垃圾重量（单位：吨）")
    private BigDecimal garbageWeight;

    @Schema(description = "关联garbage_collection.collection_id", example = "25444")
    private String planId;

    @Schema(description = "核心设备状态，JSON", example = "1")
    private String equipmentStatus;

    @Schema(description = "作业进度")
    private String progress;

    @Schema(description = "转运去向")
    private String destination;

    @Schema(description = "异常标记：是/否")
    private String abnormalIsAbnormal;

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