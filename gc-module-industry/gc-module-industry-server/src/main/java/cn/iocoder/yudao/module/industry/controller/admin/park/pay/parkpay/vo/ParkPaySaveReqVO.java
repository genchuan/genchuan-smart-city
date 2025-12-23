package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkpay.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 停车缴费服务新增/修改 Request VO")
@Data
public class ParkPaySaveReqVO {

    @Schema(description = "主键ID，唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "29631")
    private Long id;

    @Schema(description = "缴费记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "19728")
    @NotEmpty(message = "缴费记录唯一标识不能为空")
    private String payId;

    @Schema(description = "关联订单编号")
    private String woNo;

    @Schema(description = "关联预约记录ID", example = "4022")
    private String reservationId;

    @Schema(description = "支付订单编号")
    private String payWoNo;

    @Schema(description = "停车场唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "27720")
    @NotEmpty(message = "停车场唯一标识不能为空")
    private String parkLotId;

    @Schema(description = "停车场名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "停车场名称不能为空")
    private String parkLotName;

    @Schema(description = "泊位唯一标识", example = "14927")
    private String berthId;

    @Schema(description = "泊位编号")
    private String berthNo;

    @Schema(description = "车辆车牌号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车辆车牌号不能为空")
    private String plateNum;

    @Schema(description = "车辆入场时间")
    private LocalDateTime entryTime;

    @Schema(description = "车辆出场时间")
    private LocalDateTime exitTime;

    @Schema(description = "停车持续时间（分钟）")
    private Integer parkEndure;

    @Schema(description = "支付完成时间")
    private LocalDateTime payTime;

    @Schema(description = "应收金额")
    private BigDecimal receivableAmount;

    @Schema(description = "优惠金额")
    private BigDecimal discountAmount;

    @Schema(description = "实际支付金额")
    private BigDecimal actualPayAmount;

    @Schema(description = "支付方式")
    private String payMethod;

    @Schema(description = "支付状态", example = "1")
    private String payStatus;

    @Schema(description = "放行状态", example = "1")
    private String releaseStatus;

    @Schema(description = "优惠活动名称", example = "张三")
    private String activityName;

    @Schema(description = "创建人")
    private String createUser;

    @Schema(description = "行政区划名称", example = "芋艿")
    private String regionName;

    @Schema(description = "网格名称", example = "张三")
    private String gridName;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}
