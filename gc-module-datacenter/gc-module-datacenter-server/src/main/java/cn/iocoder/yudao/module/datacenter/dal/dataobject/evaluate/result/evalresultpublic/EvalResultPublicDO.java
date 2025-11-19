package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultpublic;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 结果公示 DO
 *
 * @author zcq
 */
@TableName("sys_eval_result_public")
@KeySequence("sys_eval_result_public_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalResultPublicDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 公示ID
     */
    private String resultPublicId;
    /**
     * 关联审核ID
     */
    private String resultAuditId;
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
     * 评价等级
     */
    private String evalLevel;
    /**
     * 评价得分
     */
    private BigDecimal evalScore;
    /**
     * 公示范围
     */
    private String publicScope;
    /**
     * 公示开始时间
     */
    private LocalDateTime publicStartTime;
    /**
     * 公示结束时间
     */
    private LocalDateTime publicEndTime;
    /**
     * 公示状态
     */
    private String publicStatus;
    /**
     * 公示链接
     */
    private String publicUrl;
    /**
     * 异议数量
     */
    private Integer objectCount;
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