package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.heart.inspectsupvtask;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检重点督办任务 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_supv_task")
@KeySequence("biz_inspect_supv_task_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectSupvTaskDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 督办任务ID
     */
    private String supvTaskId;
    /**
     * 督办任务编码
     */
    private String supvTaskCode;
    /**
     * 关联整改任务ID
     */
    private String rectifyTaskId;
    /**
     * 关联整改任务编码
     */
    private String rectifyTaskCode;
    /**
     * 关联问题ID
     */
    private String problemId;
    /**
     * 关联问题名称
     */
    private String problemName;
    /**
     * 督办人ID
     */
    private String supvUserId;
    /**
     * 督办人姓名
     */
    private String supvUserName;
    /**
     * 督办频次
     */
    private String supvFrequency;
    /**
     * 督办截止时间
     */
    private LocalDateTime supvDeadlineTime;
    /**
     * 督办要求
     */
    private String supvRequirement;
    /**
     * 督办任务状态
     */
    private String supvStatus;
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