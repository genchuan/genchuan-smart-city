package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkwo.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 停车订单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkWoPageReqVO extends PageParam {

    @Schema(description = "订单ID，唯一标识", example = "29075")
    private String woId;

    @Schema(description = "订单编号")
    private String woNo;

    @Schema(description = "停车场名称", example = "王五")
    private String parkName;

    @Schema(description = "停车时长（分钟）")
    private Integer parkEndure;

    @Schema(description = "应收金额")
    private BigDecimal receivableAmount;

    @Schema(description = "欠费原因说明", example = "不好")
    private String arrearsReason;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}
