package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 卡种配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CardConfigPageReqVO extends PageParam {

    @Schema(description = "卡种名称")
    private String name;

    @Schema(description = "卡种类型")
    private String type;

    @Schema(description = "卡种类型")
    private String cardType;

    @Schema(description = "适用范围")
    private String scope;

    @Schema(description = "状态（0-未生效，1-已生效）")
    private String status;

    @Schema(description = "审核人")
    private Long auditorId;

    @Schema(description = "卡种描述")
    private String description;

    @Schema(description = "审核开始时间")
    private Long auditStartTime;

    @Schema(description = "审核结束时间")
    private Long auditEndTime;

    @Schema(description = "生效开始时间")
    private Long effectStartTime;

    @Schema(description = "生效结束时间")
    private Long effectEndTime;

    @Schema(description = "最低价格")
    private BigDecimal minPrice;

    @Schema(description = "最高价格")
    private BigDecimal maxPrice;

    @Schema(description = "最小有效天数")
    private Integer minValidDays;

    @Schema(description = "最大有效天数")
    private Integer maxValidDays;

}
