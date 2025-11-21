package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultsystemdock;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 结果系统对接 DO
 *
 * @author zcq
 */
@TableName("sys_eval_result_system_dock")
@KeySequence("sys_eval_result_system_dock_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalResultSystemDockDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 对接ID
     */
    private String resultDockId;
    /**
     * 对接编号
     */
    private String resultDockCode;
    /**
     * 关联存档ID
     */
    private String resultArchId;
    /**
     * 外部系统ID
     */
    private String externalSystemId;
    /**
     * 外部系统名称
     */
    private String externalSystemName;
    /**
     * 对接方式
     */
    private String dockMethod;
    /**
     * 对接频率
     */
    private String dockFrequency;
    /**
     * 数据映射规则ID
     */
    private String mapRuleId;
    /**
     * 对接状态
     */
    private String dockStatus;
    /**
     * 对接时间
     */
    private LocalDateTime dockTime;
    /**
     * 对接数据
     */
    private String dockData;
    /**
     * 失败原因
     */
    private String failReason;
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