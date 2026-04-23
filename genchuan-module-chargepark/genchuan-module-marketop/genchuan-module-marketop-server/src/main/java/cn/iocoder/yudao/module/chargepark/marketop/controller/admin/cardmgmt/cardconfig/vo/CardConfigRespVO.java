package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 卡种配置 Response VO")
@Data
public class CardConfigRespVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "卡种名称")
    private String name;

    @Schema(description = "卡种类型")
    private String type;

    @Schema(description = "适用范围")
    private String scope;

    @Schema(description = "价格")
    private BigDecimal price;

    @Schema(description = "状态（0-未生效，1-已生效）")
    private String status;

    @Schema(description = "审核人")
    private Long auditorId;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "销量")
    private Integer saleCount;

    @Schema(description = "生效时间")
    private LocalDateTime effectTime;

    @Schema(description = "卡种描述")
    private String description;

    @Schema(description = "有效天数")
    private Integer validDays;

    @Schema(description = "销量(计算字段)")
    private Integer salesCount;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
