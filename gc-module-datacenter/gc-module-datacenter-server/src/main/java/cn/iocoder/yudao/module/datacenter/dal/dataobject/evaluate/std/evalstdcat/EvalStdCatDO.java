package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.std.evalstdcat;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 标准分类管理 DO
 *
 * @author zcq
 */
@TableName("sys_eval_std_cat")
@KeySequence("sys_eval_std_cat_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalStdCatDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 标准分类ID
     */
    private String stdCatId;
    /**
     * 标准分类名称
     */
    private String stdCatName;
    /**
     * 分类编码
     */
    private String stdCatCode;
    /**
     * 适用指标体系ID
     */
    private String idxSystemId;
    /**
     * 适用指标体系名称
     */
    private String idxSystemName;
    /**
     * 分类描述
     */
    private String catDesc;
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