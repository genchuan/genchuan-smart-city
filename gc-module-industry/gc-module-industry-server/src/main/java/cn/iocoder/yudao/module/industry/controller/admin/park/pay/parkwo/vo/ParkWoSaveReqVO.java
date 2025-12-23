package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkwo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 停车订单新增/修改 Request VO")
@Data
public class ParkWoSaveReqVO {

    @Schema(description = "主键ID，唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "22934")
    private Long id;

    @Schema(description = "订单ID，唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "29075")
    @NotEmpty(message = "订单ID，唯一标识不能为空")
    private String woId;

    @Schema(description = "订单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "订单编号不能为空")
    private String woNo;

    @Schema(description = "停车场名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "停车场名称不能为空")
    private String parkName;

    @Schema(description = "停车时长（分钟）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "停车时长（分钟）不能为空")
    private Integer parkEndure;

    @Schema(description = "应收金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "应收金额不能为空")
    private BigDecimal receivableAmount;

    @Schema(description = "欠费原因说明", example = "不好")
    private String arrearsReason;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}
