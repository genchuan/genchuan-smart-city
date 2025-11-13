package cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.traceability.risksourcearch;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 风险源档案 DO
 *
 * @author zcq
 */
@TableName("biz_risk_source_arch")
@KeySequence("biz_risk_source_arch_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiskSourceArchDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 风险源ID
     */
    private String riskSourceId;
    /**
     * 风险源编号
     */
    private String riskSourceNo;
    /**
     * 风险源名称
     */
    private String riskSourceName;
    /**
     * 风险源类型
     */
    private String riskSourceType;
    /**
     * 风险等级
     */
    private String riskLevel;
    /**
     * 所在行政区划代码
     */
    private String regionCode;
    /**
     * 所在行政区划名称
     */
    private String regionName;
    /**
     * 所在位置描述
     */
    private String locationDesc;
    /**
     * 关联资产ID
     */
    private String assetId;
    /**
     * 关联资产名称
     */
    private String assetName;
    /**
     * 责任部门ID
     */
    private String deptId;
    /**
     * 责任部门名称
     */
    private String deptName;
    /**
     * 历史预警次数
     */
    private Integer historyWarnCount;
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