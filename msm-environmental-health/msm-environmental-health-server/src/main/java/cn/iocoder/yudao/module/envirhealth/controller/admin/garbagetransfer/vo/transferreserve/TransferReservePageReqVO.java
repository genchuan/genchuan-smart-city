package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferreserve;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 进站预约分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TransferReservePageReqVO extends PageParam {

    @Schema(description = "预约主键（UUID）", example = "15220")
    private String reserveId;

    @Schema(description = "关联sys_vehicle.id", example = "1744")
    private String vehicleId;

    @Schema(description = "关联sys_garbage_type.id", example = "9608")
    private String garbageTypeId;

    @Schema(description = "预计进站时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] expectedTime;

    @Schema(description = "垃圾重量（单位：吨）")
    private BigDecimal garbageWeight;

    @Schema(description = "关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "预约状态：待排序/已排序/已进站", example = "1")
    private String reserveStatus;

    @Schema(description = "排序序号")
    private Integer sortNo;

    @Schema(description = "创建时间（业务字段）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] abnormalCreateTime;

    @Schema(description = "关联sys_user.id")
    private String handleBy;

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