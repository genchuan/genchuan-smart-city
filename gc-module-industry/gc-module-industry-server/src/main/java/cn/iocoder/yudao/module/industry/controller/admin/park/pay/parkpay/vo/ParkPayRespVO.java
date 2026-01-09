package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkpay.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 停车缴费服务 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkPayRespVO {

    @Schema(description = "主键ID，唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "29631")
    @ExcelProperty("主键ID，唯一标识")
    private Long id;

    @Schema(description = "缴费记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "19728")
    @ExcelProperty("缴费记录唯一标识")
    private String payCode;

    @Schema(description = "关联订单编号")
    @ExcelProperty("关联订单编号")
    private String woNo;

    @Schema(description = "关联预约记录ID", example = "4022")
    @ExcelProperty("关联预约记录ID")
    private Long reservationId;

    @Schema(description = "支付订单编号")
    @ExcelProperty("支付订单编号")
    private String payWoNo;

    @Schema(description = "停车场唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("停车场唯一标识")
    private Long parkLotId;

    @Schema(description = "停车场名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("停车场名称")
    private String parkLotName;

    @Schema(description = "泊位唯一标识", example = "14927")
    @ExcelProperty("泊位唯一标识")
    private Long berthId;

    @Schema(description = "泊位编号")
    @ExcelProperty("泊位编号")
    private String berthNo;

    @Schema(description = "车辆车牌号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车辆车牌号")
    private String plateNum;

    @Schema(description = "车辆入场时间")
    @ExcelProperty("车辆入场时间")
    private LocalDateTime entryTime;

    @Schema(description = "车辆出场时间")
    @ExcelProperty("车辆出场时间")
    private LocalDateTime exitTime;

    @Schema(description = "停车持续时间（分钟）")
    @ExcelProperty("停车持续时间（分钟）")
    private Integer parkEndure;

    @Schema(description = "支付完成时间")
    @ExcelProperty("支付完成时间")
    private LocalDateTime payTime;

    @Schema(description = "应收金额")
    @ExcelProperty("应收金额")
    private BigDecimal receivableAmount;

    @Schema(description = "优惠金额")
    @ExcelProperty("优惠金额")
    private BigDecimal discountAmount;

    @Schema(description = "实际支付金额")
    @ExcelProperty("实际支付金额")
    private BigDecimal actualPayAmount;

    @Schema(description = "支付方式")
    @ExcelProperty("支付方式")
    private String payMethod;

    @Schema(description = "支付状态", example = "1")
    @ExcelProperty("支付状态")
    private String payStatus;

    @Schema(description = "放行状态", example = "1")
    @ExcelProperty("放行状态")
    private String releaseStatus;

    @Schema(description = "优惠活动名称", example = "张三")
    @ExcelProperty("优惠活动名称")
    private String activityName;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String createUser;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "行政区划名称", example = "芋艿")
    @ExcelProperty("行政区划名称")
    private String regionName;

    @Schema(description = "网格名称", example = "张三")
    @ExcelProperty("网格名称")
    private String gridName;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

}
