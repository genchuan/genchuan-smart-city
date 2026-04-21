package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 收费规则更新 Request VO")
@Data
public class FeeRuleUpdateReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "主键ID不能为空")
    private Long id;

    @Schema(description = "所属场站", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "所属场站不能为空")
    private Long stationId;

    @Schema(description = "费率类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "停车收费")
    @NotNull(message = "费率类型不能为空")
    private String rateType;

    @Schema(description = "免费时长(分钟)", example = "15")
    private Integer freeTime;

    @Schema(description = "计费单位", requiredMode = Schema.RequiredMode.REQUIRED, example = "小时")
    @NotNull(message = "计费单位不能为空")
    private String chargeUnit;

    @Schema(description = "首小时价格", example = "5.00")
    private BigDecimal firstHourPrice;

    @Schema(description = "后续阶梯价格", example = "1-2小时4元/小时，2小时后2元/小时")
    private String stepPrice;

    @Schema(description = "封顶价格", example = "30.00")
    private BigDecimal maxPrice;

    @Schema(description = "峰谷电价配置", example = "峰段上浮20%，谷段下浮20%")
    private String peakValleyConfig;

    @Schema(description = "会员优惠配置", example = "会员8折，业主7折")
    private String memberConfig;

    @Schema(description = "备注", example = "万达场站停车收费规则")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;
}
