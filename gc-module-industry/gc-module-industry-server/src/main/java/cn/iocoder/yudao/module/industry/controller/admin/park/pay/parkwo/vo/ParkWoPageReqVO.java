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
//    // TODO GAI
//    @Schema(description = "订单唯一标识", example = "11")
//    private Integer woId;

    @Schema(description = "订单编号", example = "NO20251226001")
    private String woNo;

    @Schema(description = "停车场名称", example = "学校停车场")
    private String parkName;

    @Schema(description = "停车时长（分钟）", example = "120")
    private Integer parkEndure;

    @Schema(description = "应收金额", example = "50.00")
    private BigDecimal receivableAmount;

    @Schema(description = "欠费原因说明", example = "忘记缴纳")
    private String arrearsReason;

    @Schema(description = "创建时间", example = "[\"2025-12-26T00:00:00\",\"2025-12-26T23:59:59\"]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "分类扩展字段1", example = "C类")
    private String extCat1;

    @Schema(description = "分类扩展字段2", example = "停车")
    private String extCat2;

    @Schema(description = "通用扩展字段1", example = "备注信息")
    private String extCommon1;

    @Schema(description = "通用扩展字段2", example = "备用信息")
    private String extCommon2;

}
