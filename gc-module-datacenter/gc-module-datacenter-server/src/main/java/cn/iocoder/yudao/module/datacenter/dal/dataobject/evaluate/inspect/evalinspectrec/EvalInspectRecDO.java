package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.inspect.evalinspectrec;

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
 * 考察记录数据 DO
 *
 * @author zcq
 */
@TableName("sys_eval_inspect_rec")
@KeySequence("sys_eval_inspect_rec_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalInspectRecDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 记录ID
     */
    private String inspectRecId;
    /**
     * 关联计划ID
     */
    private String inspectPlanId;
    /**
     * 关联计划名称
     */
    private String inspectPlanName;
    /**
     * 考察对象ID
     */
    private String evalObjectId;
    /**
     * 考察对象名称
     */
    private String evalObjectName;
    /**
     * 关联指标项ID
     */
    private String idxItemId;
    /**
     * 关联指标项名称
     */
    private String idxItemName;
    /**
     * 考察得分
     */
    private BigDecimal inspectScore;
    /**
     * 考察等级
     */
    private String inspectLevel;
    /**
     * 问题描述
     */
    private String problemDesc;
    /**
     * 现场照片
     */
    private String onSitePhotos;
    /**
     * 考察时间
     */
    private LocalDateTime inspectTime;
    /**
     * 考察人员ID
     */
    private String inspectorId;
    /**
     * 考察人员姓名
     */
    private String inspectorName;
    /**
     * 记录状态
     */
    private String recStatus;
    /**
     * 审核人ID
     */
    private String auditorId;
    /**
     * 审核人姓名
     */
    private String auditorName;
    /**
     * 审核意见
     */
    private String auditOpinion;
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