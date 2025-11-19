package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.std.evalstditem;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 标准项管理 DO
 *
 * @author zcq
 */
@TableName("sys_eval_std_item")
@KeySequence("sys_eval_std_item_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalStdItemDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 标准项ID
     */
    private String stdItemId;
    /**
     * 标准项名称
     */
    private String stdItemName;
    /**
     * 标准项编码
     */
    private String stdItemCode;
    /**
     * 所属标准分类ID
     */
    private String stdCatId;
    /**
     * 所属标准分类名称
     */
    private String stdCatName;
    /**
     * 标准等级
     */
    private String stdLevel;
    /**
     * 分数下限
     */
    private BigDecimal scoreMin;
    /**
     * 分数上限
     */
    private BigDecimal scoreMax;
    /**
     * 标准描述
     */
    private String stdDesc;
    /**
     * 排序序号
     */
    private Integer sortNum;
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