package cn.iocoder.yudao.module.envirhealth.controller.admin.market.vo.market;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 集贸市场分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MarketPageReqVO extends PageParam {

    @Schema(description = "主键（UUID）", example = "11727")
    private String marketId;

    @Schema(description = "市场名称", example = "赵六")
    private String name;

    @Schema(description = "市场地址")
    private String address;

    @Schema(description = "关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "摊位数量", example = "11386")
    private Integer stallCount;

    @Schema(description = "关联sys_user.id", example = "11292")
    private String managerId;

    @Schema(description = "关联sys_operation_status.id", example = "20957")
    private String operationStatusId;

    @Schema(description = "卫生达标率")
    private BigDecimal hygieneRate;

    @Schema(description = "收运完成率")
    private BigDecimal wasteTransferRate;

    @Schema(description = "污水处置合格率")
    private BigDecimal sewageRate;

    @Schema(description = "未完成任务数", example = "23814")
    private Integer unfinishedTaskCount;

    @Schema(description = "保洁频次")
    private String cleaningFrequency;

    @Schema(description = "保洁时段，JSON")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private String[] cleaningTime;

    @Schema(description = "保洁区域")
    private String cleaningArea;

    @Schema(description = "负责人员IDs，JSON")
    private String staffIds;

    @Schema(description = "保洁标准")
    private String cleaningStandard;

    @Schema(description = "垃圾类型IDs，JSON")
    private String garbageTypeIds;

    @Schema(description = "收集容器数量", example = "25100")
    private Integer garbageContainerCount;

    @Schema(description = "收运间隔")
    private String wasteTransferInterval;

    @Schema(description = "收运时段")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private String[] wasteTransferTime;

    @Schema(description = "关联sys_vehicle.id", example = "27857")
    private String vehicleId;

    @Schema(description = "污水排放区域")
    private String sewageDischargeArea;

    @Schema(description = "污水处置方式")
    private String sewageDisposalWay;

    @Schema(description = "清理频次")
    private String sewageCleaningFrequency;

    @Schema(description = "问题描述")
    private String sewageProblemDesc;

    @Schema(description = "上次清理时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastSewageCleaningTime;

    @Schema(description = "下次清理时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] nextSewageCleaningTime;

    @Schema(description = "处置日志")
    private String sewageDisposalLog;

    @Schema(description = "核查时段")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private String[] hygieneCheckTime;

    @Schema(description = "关联sys_user.id")
    private String checkBy;

    @Schema(description = "核查日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] hygieneCheckDate;

    @Schema(description = "前期问题")
    private String previousProblem;

    @Schema(description = "达标项数", example = "19155")
    private Integer qualifiedItemCount;

    @Schema(description = "不达标项数", example = "20755")
    private Integer unqualifiedItemCount;

    @Schema(description = "整改要求")
    private String reformRequire;

    @Schema(description = "整改期限")
    private LocalDateTime reformDeadline;

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