package cn.iocoder.yudao.module.waterdetection.controller.admin.watersampletestsummary.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 外检统计水质检测结果汇总 Response VO")
@Data
@ExcelIgnoreUnannotated
public class WaterSampleTestSummaryRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
//    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "委托单位", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("委托单位")
    private String clientName;

    @Schema(description = "收样日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("收样日期")
    private String receiveDate;

    @Schema(description = "样品编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("样品编号")
    private String sampleNo;

    @Schema(description = "样品名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("样品名称")
    private String sampleName;

    @Schema(description = "采样地点", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("采样地点")
    private String samplingLocation;

    @Schema(description = "经度")
    @ExcelProperty("经度")
    private String longitude;

    @Schema(description = "纬度")
    @ExcelProperty("纬度")
    private String latitude;

    @Schema(description = "pH值")
    @ExcelProperty("pH值")
    private String phValue;

    @Schema(description = "氨（以N计）(mg/L)")
    @ExcelProperty("氨（以N计）(mg/L)")
    private String ammoniaN;

    @Schema(description = "臭和味")
    @ExcelProperty("臭和味")
    private String odourTaste;

    @Schema(description = "大肠埃希氏菌(CFU/100mL)")
    @ExcelProperty("大肠埃希氏菌(CFU/100mL)")
    private String escherichiaColi;

    @Schema(description = "二氯一溴甲烷(mg/L)")
    @ExcelProperty("二氯一溴甲烷(mg/L)")
    private String dichlorobromomethane;

    @Schema(description = "二氯乙酸(mg/L)")
    @ExcelProperty("二氯乙酸(mg/L)")
    private String dichloroaceticAcid;

    @Schema(description = "二氧化氯(mg/L)")
    @ExcelProperty("二氧化氯(mg/L)")
    private String chlorineDioxide;

    @Schema(description = "氟化物(mg/L)")
    @ExcelProperty("氟化物(mg/L)")
    private String fluoride;

    @Schema(description = "高锰酸盐指数(以O2计)(mg/L)")
    @ExcelProperty("高锰酸盐指数(以O2计)(mg/L)")
    private String permanganateIndex;

    @Schema(description = "镉(mg/L)")
    @ExcelProperty("镉(mg/L)")
    private String cadmium;

    @Schema(description = "铬(六价)(mg/L)")
    @ExcelProperty("铬(六价)(mg/L)")
    private String chromium;

    @Schema(description = "汞(mg/L)")
    @ExcelProperty("汞(mg/L)")
    private String mercury;

    @Schema(description = "浑浊度(NTU)")
    @ExcelProperty("浑浊度(NTU)")
    private String turbidity;

    @Schema(description = "菌落总数(CFU/mL)")
    @ExcelProperty("菌落总数(CFU/mL)")
    private String totalBacteriaCount;

    @Schema(description = "硫酸盐(mg/L)")
    @ExcelProperty("硫酸盐(mg/L)")
    private String sulfate;

    @Schema(description = "铝(mg/L)")
    @ExcelProperty("铝(mg/L)")
    private String aluminum;

    @Schema(description = "氯化物(mg/L)")
    @ExcelProperty("氯化物(mg/L)")
    private String chloride;

    @Schema(description = "氯酸盐(mg/L)")
    @ExcelProperty("氯酸盐(mg/L)")
    private String chlorate;

    @Schema(description = "锰(mg/L)")
    @ExcelProperty("锰(mg/L)")
    private String manganese;

    @Schema(description = "铅(mg/L)")
    @ExcelProperty("铅(mg/L)")
    private String lead;

    @Schema(description = "氰化物(mg/L)")
    @ExcelProperty("氰化物(mg/L)")
    private String cyanide;

    @Schema(description = "溶解性总固体(mg/L)")
    @ExcelProperty("溶解性总固体(mg/L)")
    private String dissolvedSolids;

    @Schema(description = "肉眼可见物")
    @ExcelProperty("肉眼可见物")
    private String visibleObject;

    @Schema(description = "三卤甲烷")
    @ExcelProperty("三卤甲烷")
    private String trihalomethanes;

    @Schema(description = "三氯甲烷(mg/L)")
    @ExcelProperty("三氯甲烷(mg/L)")
    private String chloroform;

    @Schema(description = "三氯乙酸(mg/L)")
    @ExcelProperty("三氯乙酸(mg/L)")
    private String trichloroaceticAcid;

    @Schema(description = "三溴甲烷(mg/L)")
    @ExcelProperty("三溴甲烷(mg/L)")
    private String bromoform;

    @Schema(description = "色度(度)")
    @ExcelProperty("色度(度)")
    private String colorDegree;

    @Schema(description = "砷(mg/L)")
    @ExcelProperty("砷(mg/L)")
    private String arsenic;

    @Schema(description = "铁(mg/L)")
    @ExcelProperty("铁(mg/L)")
    private String iron;

    @Schema(description = "铜(mg/L)")
    @ExcelProperty("铜(mg/L)")
    private String copper;

    @Schema(description = "硝酸盐（以N计）(mg/L)")
    @ExcelProperty("硝酸盐（以N计）(mg/L)")
    private String nitrateN;

    @Schema(description = "锌(mg/L)")
    @ExcelProperty("锌(mg/L)")
    private String zinc;

    @Schema(description = "亚氯酸盐(mg/L)")
    @ExcelProperty("亚氯酸盐(mg/L)")
    private String chlorite;

    @Schema(description = "一氯二溴甲烷(mg/L)")
    @ExcelProperty("一氯二溴甲烷(mg/L)")
    private String dibromochloromethane;

    @Schema(description = "总α放射性(Bq/L)")
    @ExcelProperty("总α放射性(Bq/L)")
    private String totalAlphaRadioactivity;

    @Schema(description = "总β放射性(Bq/L)")
    @ExcelProperty("总β放射性(Bq/L)")
    private String totalBetaRadioactivity;

    @Schema(description = "总大肠菌群(CFU/100mL)")
    @ExcelProperty("总大肠菌群(CFU/100mL)")
    private String totalColiform;

    @Schema(description = "总硬度（以CaCO3计）(mg/L)")
    @ExcelProperty("总硬度（以CaCO3计）(mg/L)")
    private String totalHardness;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
//    @ExcelProperty("创建时间")
    private LocalDateTime createTime;


}