package cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.planlaunchresp;

import lombok.*;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 预案启动响应 DO
 *
 * @author 亘川智城
 */
@TableName("biz_plan_launch_resp")
@KeySequence("biz_plan_launch_resp_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanLaunchRespDO extends BaseDO {

    /**
     * 自增主键ID
     */
    @TableId
    private Long id;
    /**
     * 响应ID，唯一编码，UUID
     */
    private String respId;
    /**
     * 响应编号，系统自动生成，格式“RESP+年份（4位）+6位流水号”
     */
    private String respNo;
    /**
     * 关联预案ID，关联预案库表
     */
    private String planId;
    /**
     * 预案名称，与预案ID同步
     */
    private String planName;
    /**
     * 预案版本，与预案ID同步
     */
    private String planVersion;
    /**
     * 关联事件编码，关联事件接报登记表，无事件时为空
     */
    private String evtCode;
    /**
     * 事件名称，与事件编码同步
     */
    private String evtName;
    /**
     * 启动原因，如“XX区域3小时降雨量超150mm，引发内涝，需启动III级响应”
     */
    private String launchReason;
    /**
     * 响应等级，IV级/III级/II级/I级，按事件严重程度选择
     */
    private String respLevel;
    /**
     * 启动人ID，预案启动管理员账号，关联用户信息表（sys_user）
     */
    private String launchUserId;
    /**
     * 启动人姓名，与启动人ID同步
     */
    private String launchUserName;
    /**
     * 启动时间，格式yyyy-MM-dd HH:mm:ss，系统自动生成
     */
    private LocalDateTime launchTime;
    /**
     * 响应状态，启动中/响应完成/响应终止，默认启动中
     */
    private String respStatus;
    /**
     * 终止原因，响应终止时填写，如“内涝已缓解，无需继续响应”
     */
    private String terminateReason;
    /**
     * 终止时间，格式yyyy-MM-dd HH:mm:ss，响应终止时必填
     */
    private LocalDateTime terminateTime;
    /**
     * 需同步的联动单位ID，多个用逗号分隔，关联联动单位信息表（biz_coop_unit_info）
     */
    private String syncUnitIds;
    /**
     * 与单位IDs同步，关联联动单位信息表（biz_coop_unit_info）
     */
    private String syncUnitNames;
    /**
     * 预留，如“响应指挥长”
     */
    private String extCat1;
    /**
     * 预留，如“响应指挥长”
     */
    private String extCat2;
    /**
     * 预留，存储响应部署方案路径
     */
    private String extCommon1;
    /**
     * 预留，存储响应部署方案路径
     */
    private String extCommon2;

}