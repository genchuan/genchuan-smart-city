package cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.dispatchplan;

import lombok.*;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 调度方案 DO
 *
 * @author 亘川智城
 */
@TableName("biz_dispatch_plan")
@KeySequence("biz_dispatch_plan_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DispatchPlanDO extends BaseDO {

    /**
     * 自增主键ID
     */
    @TableId
    private Long id;
    /**
     * 方案ID，唯一编码，UUID生成
     */
    private String planId;
    /**
     * 方案编号，系统自动生成，格式“DISP+年份（4位）+6位流水号”
     */
    private String planNo;
    /**
     * 关联响应ID，关联预案启动响应表
     */
    private String respId;
    /**
     * 响应编号，与响应ID同步，关联预案启动响应表（biz_plan_launch_resp）
     */
    private String respNo;
    /**
     * 方案名称，如“XX内涝救援资源调度方案”
     */
    private String planName;
    /**
     * 调度需求描述，如“需调配5辆救援车、200件救生衣至XX内涝点”
     */
    private String demandDesc;
    /**
     * 资源类型，应急物资/救援队伍/救援车辆/救援设备，标识调度资源类型
     */
    private String resType;
    /**
     * 资源ID，关联对应资源表（物资/队伍等），应急物资信息表（biz_emer_mat_info）/救援队伍信息表（biz_emer_team_info）等
     */
    private String resId;
    /**
     * 资源名称，与资源ID同步，对应资源表
     */
    private String resName;
    /**
     * 调度数量，调度资源数量（物资/设备按单位，队伍按人数，车辆按辆）
     */
    private Integer dispatchQuantity;
    /**
     * 调配目的地，如“XX路与XX路交叉口内涝点”
     */
    private String dispatchDest;
    /**
     * 目的地坐标X，度，2000国家大地坐标系经度
     */
    private BigDecimal destCoordX;
    /**
     * 目的地坐标Y，度，2000国家大地坐标系纬度
     */
    private BigDecimal destCoordY;
    /**
     * 负责人ID，调度负责人账号，关联用户信息表（sys_user）
     */
    private String chargerId;
    /**
     * 负责人姓名，与负责人ID同步，关联用户信息表（sys_user）
     */
    private String chargerName;
    /**
     * 要求完成时间，格式yyyy-MM-dd HH:mm:ss
     */
    private LocalDateTime requireCompleteTime;
    /**
     * 方案状态，待执行/执行中/已完成/已取消，默认待执行
     */
    private String planStatus;
    /**
     * 创建人，方案制定人账号，关联用户信息表（sys_user）
     */
    private String createUser;
    /**
     * 分类扩展字段1，预留，如“调度优先级”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“调度优先级”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储调度路线规划路径
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储调度路线规划路径
     */
    private String extCommon2;

}