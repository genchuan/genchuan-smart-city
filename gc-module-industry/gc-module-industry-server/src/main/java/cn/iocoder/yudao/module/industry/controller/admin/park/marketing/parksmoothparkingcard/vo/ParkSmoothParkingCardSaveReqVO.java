package cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parksmoothparkingcard.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 畅停卡新增/修改 Request VO")
@Data
public class ParkSmoothParkingCardSaveReqVO {

    @Schema(description = "[主键ID] 畅停卡唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "4747")
    private Long id;

    @Schema(description = "[卡码] 唯一卡码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[卡码] 唯一卡码不能为空")
    private String cardCode;

    @Schema(description = "[卡种类型] 如:日卡/周卡/月卡/季卡/年卡/通用卡", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "[卡种类型] 如:日卡/周卡/月卡/季卡/年卡/通用卡不能为空")
    private String cardType;

    @Schema(description = "[卡名称] 畅停卡名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "[卡名称] 畅停卡名称不能为空")
    private String cardName;

    @Schema(description = "[适用范围类型] 如:全局/区域/车场", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[适用范围类型] 如:全局/区域/车场不能为空")
    private String applyScopeType;

    @Schema(description = "[适用范围值] 车场ID或12位行政区全码，英文逗号分隔")
    private String applyScopeValue;

    @Schema(description = "[有效天数] 卡片有效天数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[有效天数] 卡片有效天数不能为空")
    private Integer validDays;

    @Schema(description = "[原价] 畅停卡原价", requiredMode = Schema.RequiredMode.REQUIRED, example = "27581")
    @NotNull(message = "[原价] 畅停卡原价不能为空")
    private BigDecimal originalPrice;

    @Schema(description = "[售价] 畅停卡实际销售价格", requiredMode = Schema.RequiredMode.REQUIRED, example = "4394")
    @NotNull(message = "[售价] 畅停卡实际销售价格不能为空")
    private BigDecimal salePrice;

    @Schema(description = "[状态] 如:未激活/已激活/已过期/已注销", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "[状态] 如:未激活/已激活/已过期/已注销不能为空")
    private String status;

    @Schema(description = "[持卡人ID] 持卡人用户ID", example = "14828")
    private Long userId;

    @Schema(description = "[激活时间] 卡片激活时间")
    private LocalDateTime activateTime;

    @Schema(description = "[过期时间] 卡片到期失效时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[过期时间] 卡片到期失效时间不能为空")
    private LocalDateTime expireTime;

    @Schema(description = "[备注] 畅停卡相关备注说明", example = "你说的对")
    private String remark;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}
