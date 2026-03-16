package cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.publicinstitution;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 公共机构分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PublicInstitutionPageReqVO extends PageParam {

    @Schema(description = "机构主键（UUID）", example = "28678")
    private String institutionId;

    @Schema(description = "机构名称", example = "李四")
    private String name;

    @Schema(description = "关联sys_institution_type.id", example = "21137")
    private String institutionTypeId;

    @Schema(description = "机构地址")
    private String address;

    @Schema(description = "关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "关联sys_user.id", example = "16838")
    private String managerId;

    @Schema(description = "关联sys_operation_status.id", example = "11750")
    private String operationStatusId;

    @Schema(description = "保洁达标率")
    private BigDecimal cleaningRate;

    @Schema(description = "问题办结率")
    private BigDecimal problemRate;

    @Schema(description = "垃圾清运量（单位：吨）")
    private BigDecimal wasteVolume;

    @Schema(description = "核查通过率")
    private BigDecimal inspectionPassRate;

    @Schema(description = "保洁标准")
    private String cleaningStandard;

    @Schema(description = "保洁频次")
    private String cleaningFrequency;

    @Schema(description = "保洁时段")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private String[] cleaningTime;

    @Schema(description = "保洁人员IDs，JSON")
    private String cleanerIds;

    @Schema(description = "责任区域")
    private String responsibilityArea;

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