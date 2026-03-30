package cn.iocoder.yudao.module.waterdetection.controller.admin.watersupplyagreement.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 供水协议管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WaterSupplyAgreementPageReqVO extends PageParam {

    @Schema(description = "协议编号")
    private String agreementNo;

    @Schema(description = "供水单位")
    private String supplierName;

    @Schema(description = "用水方")
    private String consumerName;

    @Schema(description = "供水范围")
    private String supplyScope;

    @Schema(description = "水价标准")
    private String waterPriceStandard;

    @Schema(description = "责任条款")
    private String responsibilityTerms;

    @Schema(description = "签订日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] signDate;

    @Schema(description = "有效期至")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] validDate;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}