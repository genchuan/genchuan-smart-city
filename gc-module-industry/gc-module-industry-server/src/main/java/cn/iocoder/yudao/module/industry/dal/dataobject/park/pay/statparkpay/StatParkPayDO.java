package cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.statparkpay;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 缴费统计 DO
 *
 * @author lxs
 */
@TableName("stat_park_pay")
@KeySequence("stat_park_pay_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatParkPayDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 统计记录唯一标识码
     */
    private String statCode;
    /**
     * 数据统计周期（如日/周/月）
     */
    private String statCycle;
    /**
     * 统计时间点
     */
    private LocalDateTime statTime;

    private String statCycleName;


    private LocalDateTime statStartTime;


    private LocalDateTime statEndTime;
    /**
     * 所属区域名称
     */
    private String regionName;
    /**
     * GB/T 2260 标准12位地区码
     */
    private String regionFullCode;
    /**
     * 统计周期内缴费总订单数
     */
    private Integer totalOrderCount;
    /**
     * 统计周期内缴费总金额
     */
    private BigDecimal totalPayAmount;
    /**
     * 统计周期内缴费成功占比
     */
    private BigDecimal paySuccessRate;
    /**
     * 微信支付占比
     */
    private BigDecimal wechatRatio;
    /**
     * 支付宝支付占比
     */
    private BigDecimal alipayRatio;
    /**
     * 银行卡支付占比
     */
    private BigDecimal cardRatio;
    /**
     * 各区域缴费占比（数据库JSON格式）
     */
    private String regionPayRatio;
    /**
     * 分类扩展字段1，预留
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留
     */
    private String extCommon2;


    /**
     * 各区域缴费占比（对象结构，不落库）
     */
    @TableField(exist = false)
    private List<RegionPayRatioItem> regionPayRatioList;
    @Data
    public static class RegionPayRatioItem {

        /** 区域名称 */
        private String regionName;

        /** 区域编码 */
        private String regionFullCode;

        /** 缴费金额 */
        private BigDecimal payAmount;

        /** 占比 */
        private BigDecimal ratio;
    }

}
