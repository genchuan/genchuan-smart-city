package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.publictoilet;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "环境卫生管理 - 公厕分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PublicToiletPageReqVO extends PageParam {

    @Schema(description = "公厕编码", example = "uuid-toilet-001")
    private String toiletId;

    @Schema(description = "公厕名称", example = "中山公园")
    private String name;

    @Schema(description = "公厕位置")
    private String location;

    @Schema(description = "区域编码", example = "1001")
    private String areaCode;

    @Schema(description = "开放时段", example = "06:00-22:00")
    private String openHours;

    @Schema(description = "蹲位数量范围", example = "[5, 10]")
    private Integer[] stallCount;

    @Schema(description = "运营状态ID", example = "uuid-op-status-001")
    private String operationStatusId;

    @Schema(description = "负责人ID", example = "uuid-user-001")
    private String managerId;

    @Schema(description = "保洁达标率范围", example = "[90.00, 100.00]")
    private BigDecimal[] cleaningRate;

    @Schema(description = "投诉办结率范围", example = "[95.00, 100.00]")
    private BigDecimal[] complaintRate;

    @Schema(description = "设施完好率范围", example = "[85.00, 100.00]")
    private BigDecimal[] facilityRate;

    @Schema(description = "耗材库存预警数最小值", example = "1")
    private Integer warningCountMin;

    @Schema(description = "创建时间范围")
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