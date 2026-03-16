package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "环境卫生管理 - 垃圾异常记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GarbageAbnormalPageReqVO extends PageParam {

    @Schema(description = "异常记录主键（UUID）", example = "11260")
    private String abnormalId;

    @Schema(description = "关联garbage_collection.collection_id", example = "12102")
    private String planId;

    @Schema(description = "关联sys_abnormal_type.id", example = "30798")
    private String abnormalTypeId;

    @Schema(description = "关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "关联sys_user.id")
    private String reportBy;

    @Schema(description = "上报时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] reportTime;

    @Schema(description = "优先级：高/中/低")
    private String priority;

    @Schema(description = "关联sys_user.id", example = "9865")
    private String handlerId;

    @Schema(description = "处置状态：待处置/处理中/已办结/退回", example = "1")
    private String handleStatus;

    @Schema(description = "超时提醒：是/否")
    private String isTimeout;

    @Schema(description = "异常描述")
    private String abnormalDesc;

    @Schema(description = "异常照片")
    private String abnormalPhotoUrl;

    @Schema(description = "整改说明")
    private String handleDesc;

    @Schema(description = "整改照片URL，JSON", example = "https://www.iocoder.cn")
    private String handlePhotoUrl;

    @Schema(description = "处置时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime handleTime;

    @Schema(description = "复核状态：待复核/通过/退回", example = "2")
    private String reviewStatus;

    @Schema(description = "关联sys_user.id")
    private String reviewBy;

    @Schema(description = "复核意见", example = "整改通过")
    private String reviewDesc;

    @Schema(description = "复核时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] reviewTime;

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