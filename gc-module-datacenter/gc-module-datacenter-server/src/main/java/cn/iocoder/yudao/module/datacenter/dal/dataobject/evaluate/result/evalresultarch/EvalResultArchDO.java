package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultarch;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 结果存档管理 DO
 *
 * @author zcq
 */
@TableName("sys_eval_result_arch")
@KeySequence("sys_eval_result_arch_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalResultArchDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 存档ID
     */
    private String resultArchId;
    /**
     * 存档编号
     */
    private String archCode;
    /**
     * 关联任务ID
     */
    private String evalTaskId;
    /**
     * 关联任务名称
     */
    private String evalTaskName;
    /**
     * 评价对象ID
     */
    private String evalObjectId;
    /**
     * 评价对象名称
     */
    private String evalObjectName;
    /**
     * 最终评价等级
     */
    private String finalEvalLevel;
    /**
     * 最终评价得分
     */
    private BigDecimal finalEvalScore;
    /**
     * 存档原因
     */
    private String archReason;
    /**
     * 存档时间
     */
    private LocalDateTime archTime;
    /**
     * 存档附件
     */
    private String archAttachs;
    /**
     * 创建人(业务)
     */
    private String createUserBiz;
    /**
     * 创建时间(业务)
     */
    private LocalDateTime createTimeBiz;
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