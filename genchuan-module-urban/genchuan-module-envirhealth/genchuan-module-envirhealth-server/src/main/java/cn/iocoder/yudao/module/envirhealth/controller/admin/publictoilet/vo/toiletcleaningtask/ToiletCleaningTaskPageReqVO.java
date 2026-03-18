package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcleaningtask;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "环境卫生管理 - 公厕保洁任务分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ToiletCleaningTaskPageReqVO extends PageParam {

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "公厕ID，关联public_toilet.id", example = "6468")
    private String toiletId;

    @Schema(description = "任务编号")
    private String taskNo;

    @Schema(description = "保洁频次，如每天2次")
    private String cleaningFrequency;

    @Schema(description = "保洁时段")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] cleaningTime;

    @Schema(description = "保洁内容，如地面清洁/便池清洁/垃圾清理")
    private String cleaningContent;

    @Schema(description = "保洁标准")
    private String cleaningStandard;

    @Schema(description = "保洁人员IDs，JSON数组格式")
    private String cleanerIds;

    @Schema(description = "计划状态", example = "[1,2,3]")
    private String[] planStatusId;

    @Schema(description = "完成率，%")
    private BigDecimal completionRate;

    @Schema(description = "是否异常：0-正常，1-异常")
    private Integer isAbnormal;

    @Schema(description = "异常描述")
    private String abnormalDesc;

    @Schema(description = "佐证材料URL，JSON数组格式")
    private String proofUrls;

    // ============ 新增字段 ============
    @Schema(description = "完成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] completeTime;

    @Schema(description = "处置结果")
    private String handleResult;

    @Schema(description = "任务耗时（分钟）")
    private Integer[] handleDuration;  // 支持范围查询

    @Schema(description = "满意度")
    private String satisfaction;

    @Schema(description = "统计周期")
    private String statPeriod;

    @Schema(description = "复盘意见", example = "清扫十分干净")
    private String reviewDesc;

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