package cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.settlementbill;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 结算单 DO
 *
 * @author 亘川智城
 */
@TableName("settlement_bill")
@KeySequence("settlement_bill_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettlementBillDO extends BaseDO {

    /**
     * [主键ID] 结算单唯一标识
     */
    @TableId
    private Long id;
    /**
     * [结算单编号] 结算单唯一编号
     */
    private String billCode;
    /**
     * [合作方] 合作方名称
     */
    private String cooperator;
    /**
     * [结算周期] 结算周期描述
     */
    private String settlementCycle;
    /**
     * [结算金额] 总结算金额
     */
    private BigDecimal settlementAmount;
    /**
     * [分账金额] 分账结算金额
     */
    private BigDecimal sharingAmount;
    /**
     * [结算状态] 如:待审核/审核通过/结算中/已完成/已驳回
     */
    private String billStatus;
    /**
     * [审核人员] 审核人姓名/账号
     */
    private String auditUser;
    /**
     * [审核时间] 审核操作时间
     */
    private LocalDateTime auditTime;
    /**
     * [审核备注] 审核补充说明
     */
    private String auditRemark;
    /**
     * [结算时间] 实际结算时间
     */
    private LocalDateTime settlementTime;
    /**
     * [结算渠道] 结算支付渠道
     */
    private String settlementChannel;
    /**
     * [备注] 结算单补充说明
     */
    private String remark;
    /**
     * [备用字段1] 备用扩展字段
     */
    private String reserve1;
    /**
     * [备用字段2] 备用扩展字段
     */
    private String reserve2;


}
