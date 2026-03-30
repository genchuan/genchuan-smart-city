package cn.iocoder.yudao.module.waterdetection.controller.admin.watersampletestsummary.vo;


import cn.idev.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 水样信息 Excel 导入 VO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false) // 设置 chain = false，避免用户导入有问题
public class WaterSampleTestImportExcelVO {

    @ExcelProperty("委托单位")
    private String clientName;

    @ExcelProperty("收样日期")
    private String receiveDate;

    @ExcelProperty("样品编号")
    private String sampleNo;

    @ExcelProperty("样品名称")
    private String sampleName;

    @ExcelProperty("采样地点")
    private String samplingLocation;

    @ExcelProperty("经度")
    private String longitude;

    @ExcelProperty("纬度")
    private String latitude;

    @ExcelProperty("pH值")
    private String phValue;

    @ExcelProperty("氨（以N计）(mg/L)")
    private String ammoniaN;

    @ExcelProperty("臭和味")
    private String odourTaste;

    @ExcelProperty("大肠埃希氏菌(CFU/100mL)")
    private String escherichiaColi;

    @ExcelProperty("二氯一溴甲烷(mg/L)")
    private String dichlorobromomethane;

    @ExcelProperty("二氯乙酸(mg/L)")
    private String dichloroaceticAcid;

    @ExcelProperty("二氧化氯(mg/L)")
    private String chlorineDioxide;

    @ExcelProperty("氟化物(mg/L)")
    private String fluoride;

    @ExcelProperty("高锰酸盐指数(以O2计)(mg/L)")
    private String permanganateIndex;

    @ExcelProperty("镉(mg/L)")
    private String cadmium;

    @ExcelProperty("铬(六价)(mg/L)")
    private String chromium;

    @ExcelProperty("汞(mg/L)")
    private String mercury;

    @ExcelProperty("浑浊度(NTU)")
    private String turbidity;

    @ExcelProperty("菌落总数(CFU/mL)")
    private String totalBacteriaCount;

    @ExcelProperty("硫酸盐(mg/L)")
    private String sulfate;

    @ExcelProperty("铝(mg/L)")
    private String aluminum;

    @ExcelProperty("氯化物(mg/L)")
    private String chloride;

    @ExcelProperty("氯酸盐(mg/L)")
    private String chlorate;

    @ExcelProperty("锰(mg/L)")
    private String manganese;

    @ExcelProperty("铅(mg/L)")
    private String lead;

    @ExcelProperty("氰化物(mg/L)")
    private String cyanide;

    @ExcelProperty("溶解性总固体(mg/L)")
    private String dissolvedSolids;

    @ExcelProperty("肉眼可见物")
    private String visibleObject;

    @ExcelProperty("三卤甲烷")
    private String trihalomethanes;

    @ExcelProperty("三氯甲烷(mg/L)")
    private String chloroform;

    @ExcelProperty("三氯乙酸(mg/L)")
    private String trichloroaceticAcid;

    @ExcelProperty("三溴甲烷(mg/L)")
    private String bromoform;

    @ExcelProperty("色度(度)")
    private String colorDegree;

    @ExcelProperty("砷(mg/L)")
    private String arsenic;

    @ExcelProperty("铁(mg/L)")
    private String iron;

    @ExcelProperty("铜(mg/L)")
    private String copper;

    @ExcelProperty("硝酸盐（以N计）(mg/L)")
    private String nitrateN;

    @ExcelProperty("锌(mg/L)")
    private String zinc;

    @ExcelProperty("亚氯酸盐(mg/L)")
    private String chlorite;

    @ExcelProperty("一氯二溴甲烷(mg/L)")
    private String dibromochloromethane;

    @ExcelProperty("总α放射性(Bq/L)")
    private String totalAlphaRadioactivity;

    @ExcelProperty("总β放射性(Bq/L)")
    private String totalBetaRadioactivity;

    @ExcelProperty("总大肠菌群(CFU/100mL)")
    private String totalColiform;

    @ExcelProperty("总硬度（以CaCO3计）(mg/L)")
    private String totalHardness;

}
