package cn.iocoder.yudao.module.envirhealth.controller.admin.task.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 任务分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaskPageReqVO extends PageParam {

    @Schema(description = "主键（UUID）", example = "32463")
    private String taskId;

    @Schema(description = "关联sys_task_type.sys_task_type_id", example = "3707")
    private String taskTypeId;

    @Schema(description = "关联public_toilet.id（公厕相关任务）", example = "21219")
    private String toiletId;

    @Schema(description = "关联garbage_transfer.id（转运站相关任务）", example = "29108")
    private String transferId;

    @Schema(description = "关联public_institution.id（公共机构相关任务）", example = "30423")
    private String institutionId;

    @Schema(description = "关联commercial_street.id（商业街相关任务）", example = "7066")
    private String streetId;

    @Schema(description = "关联park.id（公园相关任务）", example = "20277")
    private String parkId;

    @Schema(description = "关联urban_village.id（城中村相关任务）", example = "5313")
    private String villageId;

    @Schema(description = "关联market.id（集贸市场相关任务）", example = "31868")
    private String marketId;

    @Schema(description = "关联river.id（河道相关任务）", example = "13896")
    private String riverId;

    @Schema(description = "关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "关联sys_user.id（处置人员）")
    private String handleBy;

    @Schema(description = "处置结果")
    private String handleResult;

    @Schema(description = "佐证材料URL（照片/文档链接，多个用逗号分隔）", example = "https://www.iocoder.cn")
    private String proofUrl;

    @Schema(description = "完成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] completeTime;

    @Schema(description = "任务耗时（单位：分钟）")
    private BigDecimal handleDuration;

    @Schema(description = "统计周期（可选值：日/周/月）")
    private String statPeriod;

    @Schema(description = "保洁达标率（仅保洁类任务）")
    private BigDecimal cleaningQualifiedRate;

    @Schema(description = "问题办结率（仅问题处置类任务）")
    private BigDecimal problemCompleteRate;

    @Schema(description = "核查通过率（仅核查类任务）")
    private BigDecimal inspectionPassRate;

    @Schema(description = "进站总量（仅转运作业类任务，单位：吨）")
    private BigDecimal totalEntryVolume;

    @Schema(description = "设备完好率（仅维护类任务）")
    private BigDecimal equipmentIntactRate;

    @Schema(description = "环境达标率（仅预警类任务）")
    private BigDecimal environmentQualifiedRate;

    @Schema(description = "满意度（仅投诉类任务，可选值：满意/基本满意/不满意）")
    private String satisfaction;

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