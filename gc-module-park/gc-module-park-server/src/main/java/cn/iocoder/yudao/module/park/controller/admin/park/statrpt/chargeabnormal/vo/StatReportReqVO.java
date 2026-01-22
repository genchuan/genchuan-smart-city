package cn.iocoder.yudao.module.park.controller.admin.park.statrpt.chargeabnormal.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 收费异常统计查询 Request VO")
@Data
public class StatReportReqVO {

    @Schema(
            description = "[开始时间] 查询起始时间",
            example = "2026-01-01 00:00:00"
    )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @Schema(
            description = "[结束时间] 查询结束时间",
            example = "2026-01-31 23:59:59"
    )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @Schema(
            description = "[12位地区码] 国标地区全编码，如：110101001001",
            example = "110101001001"
    )
    private String regionFullCode;

    @Schema(
            description = "[异常类型] 如:金额错误/计费缺失/重复收费/其他",
            example = "金额错误"
    )
    private String abnormalType;

    @Schema(
            description = "[处置状态] 如:未处置/处理中/已处置",
            example = "未处置"
    )
    private String disposalStatus;

    @Schema(
            description = "[异常订单编号]",
            example = "ABN202601210001"
    )
    private String orderNo;

    @Schema(
            description = "[车牌号码]",
            example = "京A12345"
    )
    private String carNumber;

    @Schema(
            description = "[最小异常金额]",
            example = "10.00"
    )
    private BigDecimal minAmount;

    @Schema(
            description = "[最大异常金额]",
            example = "500.00"
    )
    private BigDecimal maxAmount;

    @Schema(
            description = "[统计分组字段]",
            example = "abnormal_type"
    )
    private String statGroupField;

    @Schema(
            description = "[分页参数-第几页(仅列表展示有效)]",
            example = "1"
    )
    private Integer pageNo;

    @Schema(
            description = "[分页参数-每页多少条(仅列表展示有效)]",
            example = "10"
    )
    private Integer pageSize;

    //[分页参数-每页多少条(仅列表展示有效)]
    @Schema(hidden = true)
    private Integer offset;

}
