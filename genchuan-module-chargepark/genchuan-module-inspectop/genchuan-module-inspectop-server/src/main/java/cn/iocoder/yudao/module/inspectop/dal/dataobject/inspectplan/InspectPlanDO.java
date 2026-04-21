package cn.iocoder.yudao.module.inspectop.dal.dataobject.inspectplan;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡检计划 DO
 *
 * @author zhucongquan
 */
@TableName("inspect_plan")
@KeySequence("inspect_plan_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectPlanDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 计划名称
     */
    private String name;
    /**
     * 巡检类型
     */
    private String type;
    /**
     * 巡检范围
     */
    private String scope;
    /**
     * 执行周期
     */
    private String cycle;
    /**
     * 计划描述
     */
    private String description;
    /**
     * 计划状态
     */
    private String status;
    /**
     * 执行进度
     */
    private Integer progress;
    /**
     * 审核人ID
     */
    private Long auditUserId;
    /**
     * 生效时间
     */
    private LocalDateTime effectTime;
    /**
     * 完成时间
     */
    private LocalDateTime finishTime;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}