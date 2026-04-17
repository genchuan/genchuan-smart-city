package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.packageconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 券包配置 Response VO")
@Data
public class PackageConfigRespVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "券包名称")
    private String name;

    @Schema(description = "券包类型")
    private String type;

    @Schema(description = "包含优惠券ID列表")
    private String couponIds;

    @Schema(description = "价格")
    private BigDecimal price;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "审核人")
    private Long auditorId;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "销量")
    private Integer saleCount;

    @Schema(description = "生效时间")
    private LocalDateTime effectTime;

    @Schema(description = "券包描述")
    private String description;

    @Schema(description = "适用范围")
    private String scope;

    @Schema(description = "销量(计算字段)")
    private Integer salesCount;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
