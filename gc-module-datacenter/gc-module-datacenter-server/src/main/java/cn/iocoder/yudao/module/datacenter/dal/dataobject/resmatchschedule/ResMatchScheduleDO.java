package cn.iocoder.yudao.module.datacenter.dal.dataobject.resmatchschedule;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 资源匹配调度 DO
 *
 * @author 亘川智城
 */
@TableName("biz_res_match_schedule")
@KeySequence("biz_res_match_schedule_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResMatchScheduleDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 调度ID，唯一编码，UUID生成
     */
    private String resScheduleId;
    /**
     * 关联方案ID，关联决策方案表的方案ID，决策方案表（biz_decision_scheme）
     */
    private String relSchemeId;
    /**
     * 关联方案名称，与方案ID同步，决策方案表（biz_decision_scheme）
     */
    private String relSchemeName;
    /**
     * 资源需求明细，JSON格式存储资源需求（如[{"res_type_id":"id1","res_type_name":"传感器","demand_count":3}]），资源类型表（sys_res_type）
     */
    private String resDemandDetail;
    /**
     * 匹配资源明细，JSON格式存储匹配结果（如[{"res_id":"id1","res_name":"传感器-001","allocate_count":1}]），资源信息表（biz_res_info）
     */
    private String matchedResDetail;
    /**
     * 调度状态：待匹配/匹配中/匹配成功/匹配失败/调度完成，标识调度进度
     */
    private String scheduleStatus;
    /**
     * 匹配失败原因，调度状态为“匹配失败”时必填（如“传感器库存仅2台，需求3台”）
     */
    private String matchFailReason;
    /**
     * 调度工单ID，关联资源调度工单表的工单ID，资源调度工单表（biz_res_schedule_wo）
     */
    private String scheduleWoId;
    /**
     * 调度人，发起调度的用户ID，用户信息表（sys_user）
     */
    private String scheduleUser;
    /**
     * 调度时间，格式：yyyy-MM-dd HH:mm:ss
     */
    private LocalDateTime scheduleTime;
    /**
     * 完成时间，格式：yyyy-MM-dd HH:mm:ss，调度状态为“调度完成”时必填
     */
    private LocalDateTime completeTime;
    /**
     * 资源负责人，资源管理负责人ID，用户信息表（sys_user）
     */
    private String resMngr;
    /**
     * 资源负责人姓名，与负责人ID同步，用户信息表（sys_user）
     */
    private String resMngrName;
    /**
     * 分类扩展字段1，预留，如“调度批次”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“调度批次”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储资源使用期限
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储资源使用期限
     */
    private String extCommon2;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}