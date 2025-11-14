package cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.taskoverallmng;

import lombok.*;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 任务统筹管理 DO
 *
 * @author 亘川智城
 */
@TableName("biz_task_overall_mng")
@KeySequence("biz_task_overall_mng_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskOverallMngDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 统筹ID，唯一编码，采用UUID生成
     */
    private String overallId;
    /**
     * 任务编号，关联调度指令/跨域协调编号，如“CMD_DISP2025000001”
     */
    private String taskNo;
    /**
     * 任务名称，如“XX内涝救援冲锋舟调度”
     */
    private String taskName;
    /**
     * 任务类型：物资调度/队伍调度/车辆调度/设备调度/跨域协调，标识任务类型
     */
    private String taskType;
    /**
     * 关联方案ID，关联调度方案表（biz_dispatch_plan）
     */
    private String planId;
    /**
     * 关联指令ID，关联调度指令表（biz_dispatch_command）
     */
    private String commandId;
    /**
     * 关联跨域ID，关联跨域调度协调表（biz_cross_region_dispatch）
     */
    private String crossId;
    /**
     * 任务状态：待执行/执行中/已完成/已取消/超时，根据关联表状态同步
     */
    private String taskStatus;
    /**
     * 优先级：高/中/低，按事件等级设定（特急→高，紧急→中，一般→低）
     */
    private String priority;
    /**
     * 任务负责人账号，关联用户信息表（sys_user）
     */
    private String chargerId;
    /**
     * 任务负责人姓名，与负责人ID同步，关联用户信息表（sys_user）
     */
    private String chargerName;
    /**
     * 计划完成时间，格式：yyyy-MM-dd HH:mm:ss
     */
    private LocalDateTime planCompleteTime;
    /**
     * 实际完成时间，格式：yyyy-MM-dd HH:mm:ss，已完成时必填
     */
    private LocalDateTime actualCompleteTime;
    /**
     * 延迟时长，如“30分钟”“2小时”，超时任务填写
     */
    private String delayEndure;
    /**
     * 分类扩展字段1，预留，如“任务来源(本地/跨域)”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“任务来源(本地/跨域)”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储任务关联文档路径
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储任务关联文档路径
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