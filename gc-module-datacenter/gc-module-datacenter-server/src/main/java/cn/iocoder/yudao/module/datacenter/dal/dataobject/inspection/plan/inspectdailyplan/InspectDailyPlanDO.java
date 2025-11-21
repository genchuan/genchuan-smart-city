package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.plan.inspectdailyplan;

import lombok.*;

import java.time.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 日常巡查计划信息 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_daily_plan")
@KeySequence("biz_inspect_daily_plan_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectDailyPlanDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 计划ID
     */
    private String dailyPlanId;
    /**
     * 计划名称
     */
    private String dailyPlanName;
    /**
     * 计划编码
     */
    private String dailyPlanCode;
    /**
     * 所属区域代码
     */
    private String areaCode;
    /**
     * 所属区域名称
     */
    private String areaName;
    /**
     * 关联路线ID
     */
    private String routeId;
    /**
     * 关联路线名称
     */
    private String routeName;
    /**
     * 关联标准ID
     */
    private String stdId;
    /**
     * 关联标准名称
     */
    private String stdName;
    /**
     * 负责人员ID
     */
    private String personId;
    /**
     * 负责人员姓名
     */
    private String personName;
    /**
     * 计划周期
     */
    private String planCycle;
    /**
     * 执行开始时间
     */
    private LocalTime executeStartTime;
    /**
     * 执行结束时间
     */
    private LocalTime executeEndTime;
    /**
     * 计划状态
     */
    private Boolean planStatus;
    /**
     * 开始日期
     */
    private LocalDate startDate;
    /**
     * 结束日期
     */
    private LocalDate endDate;
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