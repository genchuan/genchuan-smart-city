package cn.iocoder.yudao.module.park.controller.admin.park.statrpt.chargeabnormal.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 收费异常统计查询 Request VO")
@Data
public class StatReportReqVO {

    @Schema(description = "[开始时间] 查询起始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @Schema(description = "[结束时间] 查询结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @Schema(description = "[12位地区码] 国标地区全编码，如：110101001001")
    private String regionFullCode;

    @Schema(description = "[异常类型] 如:金额错误/计费缺失/重复收费/其他")
    private String abnormalType;

    @Schema(description = "[处置状态] 如:未处置/处理中/已处置")
    private String disposalStatus;

    @Schema(description = "[异常订单编号]")
    private String orderNo;

    @Schema(description = "[车牌号码]")
    private String carNumber;

    @Schema(description = "[最小异常金额]")
    private BigDecimal minAmount;

    @Schema(description = "[最大异常金额]")
    private BigDecimal maxAmount;

}
