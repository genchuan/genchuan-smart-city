package cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.disposal.earlywarnarch;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 预警告警处置归档 DO
 *
 * @author zcq
 */
@TableName("biz_early_warn_arch")
@KeySequence("biz_early_warn_arch_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarlyWarnArchDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 归档ID
     */
    private String archId;
    /**
     * 归档编号
     */
    private String archNo;
    /**
     * 预警ID
     */
    private String earlyWarnId;
    /**
     * 预警名称
     */
    private String earlyWarnName;
    /**
     * 预警类型
     */
    private String warnType;
    /**
     * 预警等级
     */
    private String warnLevel;
    /**
     * 所在区域
     */
    private String regionName;
    /**
     * 处置人
     */
    private String handleUserName;
    /**
     * 综合评估得分
     */
    private BigDecimal comprehensiveScore;
    /**
     * 解除状态
     */
    private String releaseStatus;
    /**
     * 归档时间
     */
    private LocalDateTime archTime;
    /**
     * 归档人ID
     */
    private String archUserId;
    /**
     * 归档人姓名
     */
    private String archUserName;
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