package cn.iocoder.yudao.module.envir.controller.admin.roadcleaning.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 道路清扫计划分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoadCleaningPageReqVO extends PageParam {

    @Schema(description = "业务主键（UUID）", example = "6462")
    private String roadCleaningId;

    @Schema(description = "清扫计划编号")
    private String planNo;

    @Schema(description = "清扫路段（关联sys_road.sys_road_id）", example = "4423")
    private String roadId;

    @Schema(description = "责任区域（关联sys_area.area_code）")
    private String areaCode;

    @Schema(description = "清扫频次")
    private String frequency;

    @Schema(description = "清扫时段")
    private String timePeriod;

    @Schema(description = "负责人员（关联sys_user.id，多个用逗号分隔）")
    private String staffIds;

    @Schema(description = "清扫工具（关联sys_tool.sys_tool_id，多个用逗号分隔）")
    private String toolIds;

    @Schema(description = "计划状态（关联sys_plan_status.sys_plan_status_id）", example = "4765")
    private String planStatusId;

    @Schema(description = "业务创建人（关联sys_user.id）")
    private String abnormalCreateBy;

    @Schema(description = "业务创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] abnormalCreateTime;

    @Schema(description = "业务更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] abnormalUpdateTime;

    @Schema(description = "质量达标率")
    private BigDecimal qualityRate;

    @Schema(description = "问题处置数", example = "26313")
    private Integer problemCount;

    @Schema(description = "考勤全勤率")
    private BigDecimal attendanceRate;

    @Schema(description = "质量核查对比照片URL（多个用逗号分隔）", example = "https://www.iocoder.cn")
    private String checkPhotoUrl;

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