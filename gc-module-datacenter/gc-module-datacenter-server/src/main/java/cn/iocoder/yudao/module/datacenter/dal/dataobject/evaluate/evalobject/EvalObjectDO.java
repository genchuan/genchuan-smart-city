package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.evalobject;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 评价对象管理 DO
 *
 * @author zcq
 */
@TableName("sys_eval_object")
@KeySequence("sys_eval_object_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalObjectDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 评价对象ID
     */
    private String evalObjectId;
    /**
     * 评价对象名称
     */
    private String evalObjectName;
    /**
     * 对象编码
     */
    private String objectCode;
    /**
     * 所属行政区划代码
     */
    private String regionCode;
    /**
     * 所属行政区划名称
     */
    private String regionName;
    /**
     * 关联网格ID
     */
    private String gridId;
    /**
     * 关联网格名称
     */
    private String gridName;
    /**
     * 关联部门ID
     */
    private String deptId;
    /**
     * 关联部门名称
     */
    private String deptName;
    /**
     * 负责人ID
     */
    private String leaderId;
    /**
     * 负责人姓名
     */
    private String leaderName;
    /**
     * 负责人联系方式
     */
    private String leaderContact;
    /**
     * 对象状态
     */
    private String objectStatus;
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