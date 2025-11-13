package cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.traceability.riskpropagatechain;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 风险传播链 DO
 *
 * @author zcq
 */
@TableName("biz_risk_propagate_chain")
@KeySequence("biz_risk_propagate_chain_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiskPropagateChainDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 传播链ID
     */
    private String propagateChainId;
    /**
     * 传播链编号
     */
    private String propagateChainNo;
    /**
     * 源头预警ID
     */
    private String sourceWarnId;
    /**
     * 源头预警名称
     */
    private String sourceWarnName;
    /**
     * 传播节点序号
     */
    private Integer nodeSeq;
    /**
     * 传播节点内容
     */
    private String nodeContent;
    /**
     * 节点类型
     */
    private String nodeType;
    /**
     * 发生时间
     */
    private LocalDateTime occurTime;
    /**
     * 影响范围
     */
    private String impactRange;
    /**
     * 关联设备ID
     */
    private String deviceId;
    /**
     * 关联设备名称
     */
    private String deviceName;
    /**
     * 记录人ID
     */
    private String recUserId;
    /**
     * 记录人姓名
     */
    private String recUserName;
    /**
     * 分类扩展字段1
     */
    private String extCat1;
    /**
     * 分类扩展字段2
     */
    private String extCat2;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;

}