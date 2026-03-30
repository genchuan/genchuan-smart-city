package cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersampletestsummary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 外检统计水质检测结果汇总 DO
 *
 * @author zhucongquan
 */
@TableName("gc_water_sample_test_summary")
@KeySequence("gc_water_sample_test_summary_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WaterSampleTestSummaryDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 委托单位
     */
    private String clientName;
    /**
     * 收样日期
     */
    private String receiveDate;
    /**
     * 样品编号
     */
    private String sampleNo;
    /**
     * 样品名称
     */
    private String sampleName;
    /**
     * 采样地点
     */
    private String samplingLocation;
    /**
     * pH值
     */
    private String phValue;
    /**
     * 氨(以N计)(mg/L)
     */
    private String ammoniaN;
    /**
     * 臭和味
     */
    private String odourTaste;
    /**
     * 大肠埃希氏菌(CFU/100mL)
     */
    private String escherichiaColi;
    /**
     * 二氯一溴甲烷(mg/L)
     */
    private String dichlorobromomethane;
    /**
     * 二氯乙酸(mg/L)
     */
    private String dichloroaceticAcid;
    /**
     * 二氧化氯(mg/L)
     */
    private String chlorineDioxide;
    /**
     * 氟化物(mg/L)
     */
    private String fluoride;
    /**
     * 高锰酸盐指数(以O2计)(mg/L)
     */
    private String permanganateIndex;
    /**
     * 镉(mg/L)
     */
    private String cadmium;
    /**
     * 铬(六价)(mg/L)
     */
    private String chromium;
    /**
     * 汞(mg/L)
     */
    private String mercury;
    /**
     * 浑浊度(NTU)
     */
    private String turbidity;
    /**
     * 菌落总数(CFU/mL)
     */
    private String totalBacteriaCount;
    /**
     * 硫酸盐(mg/L)
     */
    private String sulfate;
    /**
     * 铝(mg/L)
     */
    private String aluminum;
    /**
     * 氯化物(mg/L)
     */
    private String chloride;
    /**
     * 氯酸盐(mg/L)
     */
    private String chlorate;
    /**
     * 锰(mg/L)
     */
    private String manganese;
    /**
     * 铅(mg/L)
     */
    @TableField("`lead`")
    private String lead;
    /**
     * 氰化物(mg/L)
     */
    private String cyanide;
    /**
     * 溶解性总固体(mg/L)
     */
    private String dissolvedSolids;
    /**
     * 肉眼可见物
     */
    private String visibleObject;
    /**
     * 三卤甲烷
     */
    private String trihalomethanes;
    /**
     * 三氯甲烷(mg/L)
     */
    private String chloroform;
    /**
     * 三氯乙酸(mg/L)
     */
    private String trichloroaceticAcid;
    /**
     * 三溴甲烷(mg/L)
     */
    private String bromoform;
    /**
     * 色度(度)
     */
    private String colorDegree;
    /**
     * 砷(mg/L)
     */
    private String arsenic;
    /**
     * 铁(mg/L)
     */
    private String iron;
    /**
     * 铜(mg/L)
     */
    private String copper;
    /**
     * 硝酸盐(以N计)(mg/L)
     */
    private String nitrateN;
    /**
     * 锌(mg/L)
     */
    private String zinc;
    /**
     * 亚氯酸盐(mg/L)
     */
    private String chlorite;
    /**
     * 一氯二溴甲烷(mg/L)
     */
    private String dibromochloromethane;
    /**
     * 总α放射性(Bq/L)
     */
    private String totalAlphaRadioactivity;
    /**
     * 总β放射性(Bq/L)
     */
    private String totalBetaRadioactivity;
    /**
     * 总大肠菌群(CFU/100mL)
     */
    private String totalColiform;
    /**
     * 总硬度(以CaCO3计)(mg/L)
     */
    private String totalHardness;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

}