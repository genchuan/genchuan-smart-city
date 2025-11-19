package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.idx.evalidxitem;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 指标项管理 DO
 *
 * @author zcq
 */
@TableName("sys_eval_idx_item")
@KeySequence("sys_eval_idx_item_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalIdxItemDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 指标项ID
     */
    private String idxItemId;
    /**
     * 指标项名称
     */
    private String idxItemName;
    /**
     * 指标项编码
     */
    private String idxItemCode;
    /**
     * 所属指标分类ID
     */
    private String idxCatId;
    /**
     * 所属指标分类名称
     */
    private String idxCatName;
    /**
     * 指标类型
     */
    private String idxType;
    /**
     * 计算方式
     */
    private String calcMethod;
    /**
     * 数据来源表
     */
    private String dataSourceTable;
    /**
     * 数据来源字段
     */
    private String dataSourceField;
    /**
     * 达标阈值
     */
    private String qualifiedThreshold;
    /**
     * 指标描述
     */
    private String idxDesc;
    /**
     * 启用状态
     */
    private String enableStatus;
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