package cn.iocoder.yudao.module.waterdetection.controller.admin.watersupplyagreement.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 供水协议管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class WaterSupplyAgreementRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "协议编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("协议编号")
    private String agreementNo;

    @Schema(description = "供水单位", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("供水单位")
    private String supplierName;

    @Schema(description = "用水方", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("用水方")
    private String consumerName;

    @Schema(description = "供水范围")
    @ExcelProperty("供水范围")
    private String supplyScope;

    @Schema(description = "水价标准")
    @ExcelProperty("水价标准")
    private String waterPriceStandard;

    @Schema(description = "责任条款")
    @ExcelProperty("责任条款")
    private String responsibilityTerms;

    @Schema(description = "签订日期")
    @ExcelProperty("签订日期")
    private LocalDateTime signDate;

    @Schema(description = "有效期至")
    @ExcelProperty("有效期至")
    private LocalDateTime validDate;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}