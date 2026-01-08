package cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parksettlement;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 分账结算表 DO
 *
 * @author lxs
 */
@TableName("park_settlement")
@KeySequence("park_settlement_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkSettlementDO extends BaseDO {

    /**
     * 主键ID，自增
     */
    @TableId
    private Long id;
    /**
     * 商户ID，商户唯一标识
     */
    private Long merchantId;
    /**
     * 结算总金额，本次结算的总金额
     */
    private BigDecimal totalAmount;
    /**
     * 分账比例配置，JSON格式varchar
     */
    private String splitRatio;
    /**
     * 平台分成金额，平台应分得的金额
     */
    private BigDecimal platformAmount;
    /**
     * 商户分成金额，商户应分得的金额
     */
    private BigDecimal merchantAmount;
    /**
     * 税费金额，本次结算产生的税费金额
     */
    private BigDecimal taxAmount;
    /**
     * 结算状态：待生成 / 待审核 / 已结算 / 已驳回
     */
    private String settlementStatus;
    /**
     * 生成时间，结算数据生成时间
     */
    private LocalDateTime generateTime;
    /**
     * 审核时间，结算审核时间
     */
    private LocalDateTime auditTime;
    /**
     * 审核人，结算审核人
     */
    private String auditBy;
    /**
     * 转账时间，结算转账完成时间
     */
    private LocalDateTime transferTime;
    /**
     * 统计唯一标识码
     */
    private String statCode;
    /**
     * 统计周期标识
     */
    private String statCycle;
    /**
     * 统计周期名称，如：2025年9月
     */
    private String statCycleName;
    /**
     * 统计区间起始时间
     */
    private LocalDateTime statStartTime;
    /**
     * 统计区间截止时间
     */
    private LocalDateTime statEndTime;
    /**
     * 统计时间，实际统计时间
     */
    private LocalDateTime statTime;
    /**
     * 备注，业务备注说明
     */
    private String remark;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    private String extCommon4;

}
