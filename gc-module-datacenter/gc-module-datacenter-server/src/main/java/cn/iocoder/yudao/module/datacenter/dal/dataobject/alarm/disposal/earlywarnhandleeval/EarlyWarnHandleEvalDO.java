package cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.disposal.earlywarnhandleeval;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 预警告警处置评估 DO
 *
 * @author zhucongquan
 */
@TableName("biz_early_warn_handle_eval")
@KeySequence("biz_early_warn_handle_eval_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarlyWarnHandleEvalDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 评估ID
     */
    private String evalId;
    /**
     * 预警ID
     */
    private String earlyWarnId;
    /**
     * 派单ID
     */
    private String dispatchId;
    /**
     * 评估人ID
     */
    private String evalUserId;
    /**
     * 评估人姓名
     */
    private String evalUserName;
    /**
     * 问题解决度得分
     */
    private Integer problemSolveScore;
    /**
     * 响应及时性得分
     */
    private Integer timelinessScore;
    /**
     * 现场规范性得分
     */
    private Integer stdizationScore;
    /**
     * 综合得分
     */
    private BigDecimal comprehensiveScore;
    /**
     * 评估等级
     */
    private String evalLevel;
    /**
     * 评估时间
     */
    private LocalDateTime evalTime;
    /**
     * 评估备注
     */
    private String evalRemark;
    /**
     * 是否二次处置
     */
    private String isSecondHandle;
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