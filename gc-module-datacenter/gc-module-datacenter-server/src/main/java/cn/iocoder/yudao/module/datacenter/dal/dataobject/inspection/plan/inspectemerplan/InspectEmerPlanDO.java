package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.plan.inspectemerplan;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 应急巡查计划信息 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_emer_plan")
@KeySequence("biz_inspect_emer_plan_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectEmerPlanDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 计划ID
     */
    private String emerPlanId;
    /**
     * 计划名称
     */
    private String emerPlanName;
    /**
     * 计划编码
     */
    private String emerPlanCode;
    /**
     * 应急主题
     */
    private String emerTopic;
    /**
     * 关联应急事件ID
     */
    private String emerEvtId;
    /**
     * 关联应急事件名称
     */
    private String emerEvtName;
    /**
     * 所属区域代码
     */
    private String areaCode;
    /**
     * 所属区域名称
     */
    private String areaName;
    /**
     * 关联标准ID
     */
    private String stdId;
    /**
     * 关联标准名称
     */
    private String stdName;
    /**
     * 负责人员IDs
     */
    private String personIds;
    /**
     * 负责人员Names
     */
    private String personNames;
    /**
     * 执行时间
     */
    private LocalDateTime executeTime;
    /**
     * 执行截止时间
     */
    private LocalDateTime executeDeadline;
    /**
     * 计划状态
     */
    private Boolean planStatus;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建时间
     */
    private LocalDateTime bizCreateTime;
    /**
     * 更新人
     */
    private String updateUser;
    /**
     * 更新时间
     */
    private LocalDateTime bizUpdateTime;
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