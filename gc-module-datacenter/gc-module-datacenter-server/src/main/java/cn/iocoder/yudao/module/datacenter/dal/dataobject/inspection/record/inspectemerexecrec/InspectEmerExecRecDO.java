package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.record.inspectemerexecrec;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 应急巡查执行记录 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_emer_exec_rec")
@KeySequence("biz_inspect_emer_exec_rec_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectEmerExecRecDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 记录ID
     */
    private String emerExecId;
    /**
     * 记录编码
     */
    private String emerExecCode;
    /**
     * 关联应急计划ID
     */
    private String emerPlanId;
    /**
     * 关联应急计划名称
     */
    private String emerPlanName;
    /**
     * 关联应急事件ID
     */
    private String emerEvtId;
    /**
     * 关联应急事件名称
     */
    private String emerEvtName;
    /**
     * 关联任务ID
     */
    private String taskId;
    /**
     * 关联任务编码
     */
    private String taskCode;
    /**
     * 执行人员ID
     */
    private String personId;
    /**
     * 执行人员姓名
     */
    private String personName;
    /**
     * 执行开始时间
     */
    private LocalDateTime execStartTime;
    /**
     * 执行完成时间
     */
    private LocalDateTime execCompleteTime;
    /**
     * 应急检查项明细
     */
    private String emerCkDetail;
    /**
     * 紧急级别
     */
    private String urgencyLevel;
    /**
     * 应急问题描述
     */
    private String emerProblemDesc;
    /**
     * 处置建议
     */
    private String disposalSugg;
    /**
     * 执行设备编码
     */
    private String deviceCode;
    /**
     * 应急地点坐标X
     */
    private BigDecimal emerLocX;
    /**
     * 应急地点坐标Y
     */
    private BigDecimal emerLocY;
    /**
     * 是否已推送指挥中心
     */
    private Boolean isPushedCmd;
    /**
     * 推送时间
     */
    private LocalDateTime pushTime;
    /**
     * 创建人
     */
    private String bizCreateUser;
    /**
     * 创建时间
     */
    private LocalDateTime bizCreateTime;
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