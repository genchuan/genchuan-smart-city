package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferalarm;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 转运站预警分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TransferAlarmPageReqVO extends PageParam {

    @Schema(description = "预警主键（UUID）", example = "3038")
    private String alarmId;

    @Schema(description = "关联garbage_transfer.transfer_id", example = "5796")
    private String transferId;

    @Schema(description = "关联sys_alarm_type.id", example = "643")
    private String alarmTypeId;

    @Schema(description = "发生时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] alarmTime;

    @Schema(description = "预警内容")
    private String alarmContent;

    @Schema(description = "关联设备/区域")
    private String relevantInfo;

    @Schema(description = "处置状态：待处置/处理中/已解除", example = "1")
    private String handleStatus;

    @Schema(description = "关联sys_user.id")
    private String handleBy;

    @Schema(description = "超时提醒：是/否")
    private String abnormalIsTimeout;

    @Schema(description = "处置进度")
    private String handleProgress;

    @Schema(description = "处置结果")
    private String handleResult;

    @Schema(description = "佐证材料URL，JSON")
    private String proofMaterial;

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