package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletconsumable;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 公厕耗材配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ToiletConsumablePageReqVO extends PageParam {

    @Schema(description = "公厕ID，关联public_toilet.id", example = "32743")
    private String toiletId;

    @Schema(description = "耗材ID，关联sys_consumable.id", example = "4894")
    private String consumableId;

    @Schema(description = "当前库存数量")
    private Integer consumableStock;

    @Schema(description = "预警阈值")
    private Integer consumableThreshold;

    @Schema(description = "预警状态：正常/预警/严重预警")
    private String consumableWarning;

    @Schema(description = "上次补充时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastSupplyTime;

    @Schema(description = "补充周期，单位：天")
    private Integer supplyCycle;

    @Schema(description = "缺口数量")
    private Integer consumableGap;

    @Schema(description = "负责人ID，关联sys_user.id，负责该耗材的管理和补充", example = "14418")
    private String managerId;

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