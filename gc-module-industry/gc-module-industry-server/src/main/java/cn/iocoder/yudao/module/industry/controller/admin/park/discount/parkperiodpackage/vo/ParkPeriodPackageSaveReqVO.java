package cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkperiodpackage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 期卡套餐新增/修改 Request VO")
@Data
public class ParkPeriodPackageSaveReqVO {

    @Schema(description = "[主键ID] 期卡套餐唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "20313")
    private Long id;

    @Schema(description = "[套餐名称] 期卡套餐名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "[套餐名称] 期卡套餐名称不能为空")
    private String packageName;

    @Schema(description = "[套餐类型] 日卡 / 周卡 / 月卡 / 季卡 / 年卡 / 自定义", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[套餐类型] 日卡 / 周卡 / 月卡 / 季卡 / 年卡 / 自定义不能为空")
    private String packageType;

    @Schema(description = "[适用车场ID列表] JSON 格式字符串，存储车场ID集合", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[适用车场ID列表] JSON 格式字符串，存储车场ID集合不能为空")
    private String applyLotIds;

    @Schema(description = "[适用车位类型] 普通 / 新能源 / 专用", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[适用车位类型] 普通 / 新能源 / 专用不能为空")
    private String spaceType;

    @Schema(description = "[原价] 套餐原价", requiredMode = Schema.RequiredMode.REQUIRED, example = "11554")
    @NotNull(message = "[原价] 套餐原价不能为空")
    private BigDecimal originalPrice;

    @Schema(description = "[售价] 套餐实际销售价格", requiredMode = Schema.RequiredMode.REQUIRED, example = "19230")
    @NotNull(message = "[售价] 套餐实际销售价格不能为空")
    private BigDecimal salePrice;

    @Schema(description = "[有效天数] 套餐有效天数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[有效天数] 套餐有效天数不能为空")
    private Integer validDays;

    @Schema(description = "[状态] 上架 / 下架 / 暂停销售", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[状态] 上架 / 下架 / 暂停销售不能为空")
    private String status;

    @Schema(description = "[销售数量] 套餐累计销售数量", example = "7244")
    private Integer salesCount;

    @Schema(description = "[备注] 期卡套餐相关说明", example = "随便")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}
