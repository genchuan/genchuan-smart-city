package cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkrechargepackage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 充值套餐新增/修改 Request VO")
@Data
public class ParkRechargePackageSaveReqVO {

    @Schema(description = "[主键ID] 充值套餐唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "25504")
    private Long id;

    @Schema(description = "[套餐名称] 充值套餐名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "[套餐名称] 充值套餐名称不能为空")
    private String packageName;

    @Schema(description = "[充值金额] 实际充值金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[充值金额] 实际充值金额不能为空")
    private BigDecimal rechargeAmount;

    @Schema(description = "[赠送金额] 充值赠送金额")
    private BigDecimal giveAmount;

    @Schema(description = "[赠送时间] 赠送时间，单位分钟")
    private Integer giveTime;

    @Schema(description = "[状态] 如:上架/下架", example = "2")
    private String status;

    @Schema(description = "[销售数量] 套餐销售数量", example = "29325")
    private Integer salesCount;

    @Schema(description = "[备注] 充值套餐相关备注说明", example = "你说的对")
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
