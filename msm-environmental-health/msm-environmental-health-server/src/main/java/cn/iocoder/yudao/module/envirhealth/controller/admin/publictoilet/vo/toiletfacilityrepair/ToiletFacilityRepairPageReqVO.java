package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletfacilityrepair;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "环境卫生管理模块 - 公厕设施维修分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ToiletFacilityRepairPageReqVO extends PageParam {

    @Schema(description = "维修单号")
    private String repairId;

    @Schema(description = "公厕ID")
    private String toiletId;

    @Schema(description = "公厕名称")
    private String toiletName;

    @Schema(description = "设施ID")
    private String facilityId;

    @Schema(description = "设施名称")
    private String facilityName;

    @Schema(description = "损坏描述")
    private String damageDesc;

    @Schema(description = "上报人ID")
    private String reportBy;

    @Schema(description = "上报人姓名")
    private String reportName;

    @Schema(description = "上报时间范围")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] reportTime;

    @Schema(description = "照片URL")
    private String photoUrl;

    @Schema(description = "维修人ID")
    private String repairBy;

    @Schema(description = "维修人姓名")
    private String repairName;

    @Schema(description = "维修状态")
    private String repairStatus;

    @Schema(description = "预计完成时间范围")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] expectedCompleteTime;

    @Schema(description = "实际完成时间范围")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] actualCompleteTime;

    @Schema(description = "验收结果")
    private String acceptResult;

    @Schema(description = "验收意见")
    private String acceptOpinion;

    @Schema(description = "创建时间范围")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间范围")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

    @JsonIgnore
    @Schema(hidden = true)
    private Integer offset;

    @JsonIgnore
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