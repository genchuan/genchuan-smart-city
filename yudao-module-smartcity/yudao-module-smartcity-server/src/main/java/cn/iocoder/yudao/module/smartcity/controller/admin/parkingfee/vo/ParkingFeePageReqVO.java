package cn.iocoder.yudao.module.smartcity.controller.admin.parkingfee.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 停车收费管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkingFeePageReqVO extends PageParam {

    @Schema(description = "车牌号")
    private String licensePlateNumber;

    @Schema(description = "入场时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] admissionTime;

    @Schema(description = "出场时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] exitTime;

    @Schema(description = "停车时长")
    private String parkingDuration;

    @Schema(description = "收费金额")
    private String feeAmount;

    @Schema(description = "缴费方式")
    private String paymentMethod;

    @Schema(description = "实收金额")
    private String actualReceivedAmount;

    @Schema(description = "车位编号")
    private String parkingSpaceNumber;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}