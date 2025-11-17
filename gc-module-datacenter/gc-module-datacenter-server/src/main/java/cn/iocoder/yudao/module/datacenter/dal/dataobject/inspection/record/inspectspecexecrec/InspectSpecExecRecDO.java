package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.record.inspectspecexecrec;

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
 * 专项巡查执行记录 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_spec_exec_rec")
@KeySequence("biz_inspect_spec_exec_rec_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectSpecExecRecDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 记录ID
     */
    private String specExecId;
    /**
     * 记录编码
     */
    private String specExecCode;
    /**
     * 关联专项计划ID
     */
    private String specPlanId;
    /**
     * 关联专项计划名称
     */
    private String specPlanName;
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
     * 关联标准ID
     */
    private String stdId;
    /**
     * 关联标准名称
     */
    private String stdName;
    /**
     * 执行开始时间
     */
    private LocalDateTime execStartTime;
    /**
     * 执行完成时间
     */
    private LocalDateTime execCompleteTime;
    /**
     * 检查项执行明细
     */
    private String ckItemDetail;
    /**
     * 不合格项数量
     */
    private Integer unqualifiedCount;
    /**
     * 不合格项详情
     */
    private String unqualifiedDetail;
    /**
     * 执行评分
     */
    private Integer execScore;
    /**
     * 专项执行备注
     */
    private String specExecRemark;
    /**
     * 执行设备编码
     */
    private String deviceCode;
    /**
     * 执行地点坐标X
     */
    private BigDecimal execLocX;
    /**
     * 执行地点坐标Y
     */
    private BigDecimal execLocY;
    /**
     * 创建人
     */
    private String bizCreateUser;
    /**
     * 创建时间
     */
    private LocalDateTime bizCreateTime;
    /**
     * 更新人
     */
    private String bizUpdateUser;
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