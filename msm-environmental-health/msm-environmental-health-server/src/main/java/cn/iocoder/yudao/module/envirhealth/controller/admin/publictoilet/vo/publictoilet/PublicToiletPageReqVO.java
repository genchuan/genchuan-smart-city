package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.publictoilet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 公厕分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PublicToiletPageReqVO extends PageParam {

    @Schema(description = "公厕主键（UUID）", example = "495")
    private String toiletId;

    @Schema(description = "公厕名称", example = "李四")
    private String name;

    @Schema(description = "公厕位置")
    private String location;

    @Schema(description = "关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "开放时段")
    private String openHours;

    @Schema(description = "蹲位数量", example = "16057")
    private Integer stallCount;

    @Schema(description = "关联sys_operation_status.id", example = "6154")
    private String operationStatusId;

    @Schema(description = "关联sys_user.id", example = "27436")
    private String managerId;

    @Schema(description = "保洁达标率")
    private BigDecimal cleaningRate;

    @Schema(description = "投诉办结率")
    private BigDecimal complaintRate;

    @Schema(description = "耗材库存预警数", example = "5094")
    private Integer warningCount;

    @Schema(description = "设施完好率")
    private BigDecimal facilityRate;

    @Schema(description = "保洁频次")
    private String cleaningFrequency;

    @Schema(description = "保洁时段")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private String[] cleaningTime;

    @Schema(description = "保洁内容")
    private String cleaningContent;

    @Schema(description = "保洁标准")
    private String cleaningStandard;

    @Schema(description = "保洁人员IDs，JSON")
    private String cleanerIds;

    @Schema(description = "耗材库存")
    private String consumableStock;

    @Schema(description = "预警阈值")
    private Integer consumableThreshold;

    @Schema(description = "缺口数量")
    private Integer consumableGap;

    @Schema(description = "是否有缺口（true：有缺口，false：无缺口）")
    private Boolean hasConsumableGap;

    @Schema(description = "上次补充时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastSupplyTime;

    @Schema(description = "补充周期")
    private String supplyCycle;

    @JsonIgnore
    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @JsonIgnore
    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @JsonIgnore
    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @JsonIgnore
    @Schema(description = "通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间范围")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

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