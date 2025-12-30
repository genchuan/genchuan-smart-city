package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkreservation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 停车预约服务 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkReservationRespVO {

    @Schema(description = "主键，唯一标识", example = "17193")
    @ExcelProperty("主键，唯一标识")
    private Long id;

    @Schema(description = "预约记录唯一标识", example = "10121")
    @ExcelProperty("预约记录唯一标识")
    private Long reservationId;

    @Schema(description = "预约订单编号")
    @ExcelProperty("预约订单编号")
    private String reservationNo;

    @Schema(description = "用户唯一标识", example = "21885")
    @ExcelProperty("用户唯一标识")
    private Long userId;

    @Schema(description = "用户姓名", example = "王五")
    @ExcelProperty("用户姓名")
    private String userName;

    @Schema(description = "用户手机号")
    @ExcelProperty("用户手机号")
    private String userPhone;

    @Schema(description = "停车场唯一标识", example = "1")
    @ExcelProperty("停车场唯一标识")
    private Long parkLotId;

    @Schema(description = "停车场名称", example = "芋艿")
    @ExcelProperty("停车场名称")
    private String parkLotName;

    @Schema(description = "泊位唯一标识", example = "15414")
    @ExcelProperty("泊位唯一标识")
    private Long berthId;

    @Schema(description = "泊位编号")
    @ExcelProperty("泊位编号")
    private String berthNo;

    @Schema(description = "预约开始时间")
    @ExcelProperty("预约开始时间")
    private LocalDateTime reserveStartTime;

    @Schema(description = "预约结束时间")
    @ExcelProperty("预约结束时间")
    private LocalDateTime reserveEndTime;

    @Schema(description = "预约状态", example = "1")
    @ExcelProperty("预约状态")
    private String reserveStatus;

    @Schema(description = "核验方式")
    @ExcelProperty("核验方式")
    private String verifyMethod;

    @Schema(description = "支付金额")
    @ExcelProperty("支付金额")
    private BigDecimal payAmount;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String createUser;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "行政区划名称", example = "芋艿")
    @ExcelProperty("行政区划名称")
    private String regionName;

    @Schema(description = "网格名称", example = "李四")
    @ExcelProperty("网格名称")
    private String gridName;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

}
