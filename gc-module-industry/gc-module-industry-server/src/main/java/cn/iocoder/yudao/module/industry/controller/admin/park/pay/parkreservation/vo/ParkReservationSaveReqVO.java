package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkreservation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 停车预约服务新增/修改 Request VO")
@Data
public class ParkReservationSaveReqVO {

    @Schema(description = "主键，唯一标识", example = "17193")
    private Long id;

    @Schema(description = "预约记录唯一标识", example = "10121")
    private Long reservationId;

    @Schema(description = "预约订单编号")
    private String reservationNo;

    @Schema(description = "用户唯一标识", example = "21885")
    private Long userId;

    @Schema(description = "用户姓名", example = "王五")
    private String userName;

    @Schema(description = "用户手机号")
    private String userPhone;

    @Schema(description = "停车场唯一标识", example = "1")
    private Long parkLotId;

    @Schema(description = "停车场名称", example = "芋艿")
    private String parkLotName;

    @Schema(description = "泊位唯一标识", example = "15414")
    private Long berthId;

    @Schema(description = "泊位编号")
    private String berthNo;

    @Schema(description = "预约开始时间")
    private LocalDateTime reserveStartTime;

    @Schema(description = "预约结束时间")
    private LocalDateTime reserveEndTime;

    @Schema(description = "预约状态", example = "1")
    private String reserveStatus;

    @Schema(description = "核验方式")
    private String verifyMethod;

    @Schema(description = "支付金额")
    private BigDecimal payAmount;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "创建人")
    private String createUser;

    @Schema(description = "行政区划名称", example = "芋艿")
    private String regionName;

    @Schema(description = "网格名称", example = "李四")
    private String gridName;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}
