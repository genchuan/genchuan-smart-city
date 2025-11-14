package cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.dispatchcommand;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 调度指令 DO
 *
 * @author 亘川智城
 */
@TableName("biz_dispatch_command")
@KeySequence("biz_dispatch_command_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DispatchCommandDO extends BaseDO {

    /**
     * 自增主键ID
     */
    @TableId
    private Long id;
    /**
     * 指令ID，唯一编码，UUID生成
     */
    private String commandId;
    /**
     * 指令编号，系统自动生成，格式“CMD_DISP+年份（4位）+6位流水号”
     */
    private String commandNo;
    /**
     * 关联方案ID，关联调度方案表
     */
    private String planId;
    /**
     * 方案编号，与方案ID同步，关联调度方案表（biz_dispatch_plan）
     */
    private String planNo;
    /**
     * 执行单位ID，关联联动单位信息表
     */
    private String execUnitId;
    /**
     * 执行单位名称，与执行单位ID同步，联动单位信息表（biz_coop_unit_info）
     */
    private String execUnitName;
    /**
     * 任务内容，从方案同步，可补充细节，如“派遣2辆救援车，携带救生设备，1小时内到达XX内涝点”
     */
    private String taskContent;
    /**
     * 执行要求，如“注意行车安全，到达后立即反馈”
     */
    private String execRequirement;
    /**
     * 指令状态，待下发/已下发/已接收/执行中/已完成/已驳回，默认待下发
     */
    private String commandStatus;
    /**
     * 下发人ID，指令下发人账号，已下发时必填，关联用户信息表（sys_user）
     */
    private String issueUserId;
    /**
     * 下发人姓名，与下发人ID同步，关联用户信息表（sys_user）
     */
    private String issueUserName;
    /**
     * 下发时间，格式yyyy-MM-dd HH:mm:ss，已下发时必填
     */
    private LocalDateTime issueTime;
    /**
     * 接收人ID，执行单位接收人账号，已接收时必填，关联用户信息表（sys_user）
     */
    private String receiveUserId;
    /**
     * 接收人姓名，与接收人ID同步，关联用户信息表（sys_user）
     */
    private String receiveUserName;
    /**
     * 接收时间，格式yyyy-MM-dd HH:mm:ss，已接收时必填
     */
    private LocalDateTime receiveTime;
    /**
     * 执行反馈，如“已派遣2辆救援车，预计30分钟后到达”
     */
    private String execFeedback;
    /**
     * 完成时间，格式yyyy-MM-dd HH:mm:ss，已完成时必填
     */
    private LocalDateTime completeTime;
    /**
     * 分类扩展字段1，预留，如“指令紧急程度”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“指令紧急程度”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储执行现场照片路径
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储执行现场照片路径
     */
    private String extCommon2;

}