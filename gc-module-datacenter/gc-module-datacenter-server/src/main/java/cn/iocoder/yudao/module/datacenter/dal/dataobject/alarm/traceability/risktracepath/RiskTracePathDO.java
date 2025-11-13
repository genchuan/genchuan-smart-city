package cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.traceability.risktracepath;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 风险溯源路径 DO
 *
 * @author zcq
 */
@TableName("biz_risk_trace_path")
@KeySequence("biz_risk_trace_path_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiskTracePathDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 路径ID
     */
    private String tracePathId;
    /**
     * 预警ID
     */
    private String earlyWarnId;
    /**
     * 预警名称
     */
    private String earlyWarnName;
    /**
     * 溯源时间
     */
    private LocalDateTime traceTime;
    /**
     * 路径节点1（直接现象）
     */
    private String node1Content;
    /**
     * 路径节点2（直接原因）
     */
    private String node2Content;
    /**
     * 路径节点3（根本原因）
     */
    private String node3Content;
    /**
     * 溯源结论
     */
    private String traceConclusion;
    /**
     * 分析人ID
     */
    private String analystUserId;
    /**
     * 分析人姓名
     */
    private String analystUserName;
    /**
     * 是否自动分析
     */
    private String isAutoAnalysis;
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