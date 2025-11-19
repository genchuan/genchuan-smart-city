package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.idx.evalidxweight;

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
 * 指标权重管理 DO
 *
 * @author zcq
 */
@TableName("sys_eval_idx_weight")
@KeySequence("sys_eval_idx_weight_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalIdxWeightDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 权重ID
     */
    private String idxWeightId;
    /**
     * 所属指标体系ID
     */
    private String idxSystemId;
    /**
     * 所属指标体系名称
     */
    private String idxSystemName;
    /**
     * 权重类型
     */
    private String weightType;
    /**
     * 关联ID
     */
    private String relateId;
    /**
     * 关联名称
     */
    private String relateName;
    /**
     * 权重值(%)
     */
    private BigDecimal weightValue;
    /**
     * 权重说明
     */
    private String weightDesc;
    /**
     * 创建人(业务)
     */
    private String createUserBiz;
    /**
     * 创建时间(业务)
     */
    private LocalDateTime createTimeBiz;
    /**
     * 更新人(业务)
     */
    private String updateUserBiz;
    /**
     * 更新时间(业务)
     */
    private LocalDateTime updateTimeBiz;
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