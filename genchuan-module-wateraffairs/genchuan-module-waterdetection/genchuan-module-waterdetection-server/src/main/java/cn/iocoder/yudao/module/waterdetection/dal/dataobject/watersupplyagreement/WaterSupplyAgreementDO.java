package cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersupplyagreement;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 供水协议管理 DO
 *
 * @author zcq
 */
@TableName("gc_water_supply_agreement")
@KeySequence("gc_water_supply_agreement_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WaterSupplyAgreementDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 协议编号
     */
    private String agreementNo;
    /**
     * 供水单位
     */
    private String supplierName;
    /**
     * 用水方
     */
    private String consumerName;
    /**
     * 供水范围
     */
    private String supplyScope;
    /**
     * 水价标准
     */
    private String waterPriceStandard;
    /**
     * 责任条款
     */
    private String responsibilityTerms;
    /**
     * 签订日期
     */
    private LocalDateTime signDate;
    /**
     * 有效期至
     */
    private LocalDateTime validDate;

}