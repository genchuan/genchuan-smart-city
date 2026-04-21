package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 兑换类目 Response VO")
@Data
public class ExchangeCategoryRespVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "类目名称")
    private String name;

    @Schema(description = "所需积分")
    private Integer point;

    @Schema(description = "库存")
    private Integer stock;

    @Schema(description = "类目状态（未生效/已生效/已禁用）")
    private String status;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "商品数")
    private Integer productCount;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
