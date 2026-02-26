package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletfacilityrepair;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 公厕设施维修分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ToiletFacilityRepairPageReqVO extends PageParam {

    @Schema(description = "维修主键（UUID）", example = "10879")
    private String repairId;

    @Schema(description = "关联public_toilet.toilet_id", example = "11177")
    private String toiletId;

    @Schema(description = "关联sys_facility.id", example = "19156")
    private String facilityId;

    @Schema(description = "损坏情况")
    private String damageDesc;

    @Schema(description = "关联sys_user.id")
    private String reportBy;

    @Schema(description = "上报时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] reportTime;

    @Schema(description = "现场照片URL", example = "https://www.iocoder.cn")
    private String photoUrl;

    @Schema(description = "关联sys_user.id")
    private String repairBy;

    @Schema(description = "维修状态：待维修/维修中/已完成/不合格", example = "2")
    private String repairStatus;

    @Schema(description = "预计完成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] expectedCompleteTime;

    @Schema(description = "验收结果：合格/不合格")
    private String acceptResult;

    @Schema(description = "验收意见")
    private String acceptOpinion;

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