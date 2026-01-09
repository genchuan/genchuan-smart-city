package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkwo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 停车订单新增/修改 Request VO")
@Data
public class ParkWoSaveReqVO {

    @Schema(description = "主键ID，唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "100")
    private Long id;

//    @Schema(description = "订单ID，唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "WO202512260001")
//    @NotEmpty(message = "订单ID，唯一标识不能为空")
//    private String woId;

    @Schema(description = "订单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "PARK-WO-20251226-0001")
    @NotEmpty(message = "订单编号不能为空")
    private String woNo;

    @Schema(description = "停车场名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "胜利路智慧停车场")
    @NotEmpty(message = "停车场名称不能为空")
    private String parkName;

    @Schema(description = "停车时长（分钟）", requiredMode = Schema.RequiredMode.REQUIRED, example = "95")
    @NotNull(message = "停车时长（分钟）不能为空")
    private Integer parkEndure;

    @Schema(description = "应收金额", requiredMode = Schema.RequiredMode.REQUIRED, example = "18.50")
    @NotNull(message = "应收金额不能为空")
    private BigDecimal receivableAmount;

    @Schema(description = "欠费原因说明", example = "车辆离场时网络异常，未完成支付")
    private String arrearsReason;

    @Schema(description = "分类扩展字段1", example = "临时停车")
    private String extCat1;

    @Schema(description = "分类扩展字段2", example = "小型车")
    private String extCat2;

    @Schema(description = "通用扩展字段1", example = "来源：地磁设备")
    private String extCommon1;

    @Schema(description = "通用扩展字段2", example = "备注：人工补录")
    private String extCommon2;

}

