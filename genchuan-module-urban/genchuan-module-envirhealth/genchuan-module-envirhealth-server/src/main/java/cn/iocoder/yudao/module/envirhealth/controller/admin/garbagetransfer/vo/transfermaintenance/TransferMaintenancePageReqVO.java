package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transfermaintenance;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "环境卫生管理 - 设备维护分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TransferMaintenancePageReqVO extends PageParam {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "维护主键（UUID）", example = "560")
    private String maintenanceId;

    @Schema(description = "关联garbage_transfer.transfer_id", example = "19812")
    private String transferId;

    @Schema(description = "关联sys_equipment.id", example = "17093")
    private String equipmentId;

    @Schema(description = "维护周期")
    private String maintenanceCycle;

    @Schema(description = "上次维护时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastMaintenanceTime;

    @Schema(description = "维护内容")
    private String maintenanceContent;

    @Schema(description = "关联sys_user.id")
    private String handleBy;

    @Schema(description = "维护状态：待维护/维护中/已完成", example = "1")
    private String maintenanceStatus;

    @Schema(description = "预计完成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] expectedCompleteTime;

    @Schema(description = "超时提醒：是/否")
    private String abnormalIsTimeout;

    @Schema(description = "维护时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] maintenanceTime;

    @Schema(description = "更换配件")
    private String replaceParts;

    @Schema(description = "维护费用（单位：元）")
    private BigDecimal maintenanceCost;

    @Schema(description = "维护照片URL，JSON")
    private String maintenancePhoto;

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