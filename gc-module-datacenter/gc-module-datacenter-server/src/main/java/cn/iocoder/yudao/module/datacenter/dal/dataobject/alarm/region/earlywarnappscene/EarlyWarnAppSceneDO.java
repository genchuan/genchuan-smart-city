package cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnappscene;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 按应用场景预警告警统计 DO
 *
 * @author zcq
 */
@TableName("stat_early_warn_app_scene")
@KeySequence("stat_early_warn_app_scene_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarlyWarnAppSceneDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 统计ID
     */
    private String warnAppSceneStatId;
    /**
     * 统计周期
     */
    private String statCycle;
    /**
     * 场景大类ID
     */
    private String sceneMajorId;
    /**
     * 场景大类名称
     */
    private String sceneMajorName;
    /**
     * 场景小类ID
     */
    private String sceneMinorId;
    /**
     * 场景小类名称
     */
    private String sceneMinorName;
    /**
     * 预警总数
     */
    private Integer totalWarnCount;
    /**
     * 主要预警类型
     */
    private String mainWarnType;
    /**
     * 已办结数
     */
    private Integer completedCount;
    /**
     * 处置成功率
     */
    private BigDecimal successRate;
    /**
     * 涉及网格数
     */
    private Integer gridCount;
    /**
     * 统计人
     */
    private String statUserId;
    /**
     * 统计时间
     */
    private LocalDateTime statTime;
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