package cn.iocoder.yudao.module.waterdetection.controller.admin.watersupplyagreement.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 供水协议管理新增/修改 Request VO")
@Data
public class WaterSupplyAgreementSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "协议编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "协议编号不能为空")
    private String agreementNo;

    @Schema(description = "供水单位", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "供水单位不能为空")
    private String supplierName;

    @Schema(description = "用水方", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "用水方不能为空")
    private String consumerName;

    @Schema(description = "供水范围")
    private String supplyScope;

    @Schema(description = "水价标准")
    private String waterPriceStandard;

    @Schema(description = "责任条款")
    private String responsibilityTerms;

    @Schema(description = "签订日期")
    private LocalDateTime signDate;

    @Schema(description = "有效期至")
    private LocalDateTime validDate;

}