package cn.iocoder.yudao.module.envirhealth.dal.dataobject.task;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 任务 DO
 *
 * @author 芋道源码
 */
@TableName("task")
@KeySequence("task_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 主键（UUID）
     */
    private String taskId;
    /**
     * 关联sys_task_type.sys_task_type_id
     */
    private String taskTypeId;
    /**
     * 关联public_toilet.id（公厕相关任务）
     */
    private String toiletId;
    /**
     * 关联garbage_transfer.id（转运站相关任务）
     */
    private String transferId;
    /**
     * 关联public_institution.id（公共机构相关任务）
     */
    private String institutionId;
    /**
     * 关联commercial_street.id（商业街相关任务）
     */
    private String streetId;
    /**
     * 关联park.id（公园相关任务）
     */
    private String parkId;
    /**
     * 关联urban_village.id（城中村相关任务）
     */
    private String villageId;
    /**
     * 关联market.id（集贸市场相关任务）
     */
    private String marketId;
    /**
     * 关联river.id（河道相关任务）
     */
    private String riverId;
    /**
     * 关联sys_area.area_code
     */
    private String areaCode;
    /**
     * 关联sys_user.id（处置人员）
     */
    private String handleBy;
    /**
     * 处置结果
     */
    private String handleResult;
    /**
     * 佐证材料URL（照片/文档链接，多个用逗号分隔）
     */
    private String proofUrl;
    /**
     * 完成时间
     */
    private LocalDateTime completeTime;
    /**
     * 任务耗时（单位：分钟）
     */
    private BigDecimal handleDuration;
    /**
     * 统计周期（可选值：日/周/月）
     */
    private String statPeriod;
    /**
     * 保洁达标率（仅保洁类任务）
     */
    private BigDecimal cleaningQualifiedRate;
    /**
     * 问题办结率（仅问题处置类任务）
     */
    private BigDecimal problemCompleteRate;
    /**
     * 核查通过率（仅核查类任务）
     */
    private BigDecimal inspectionPassRate;
    /**
     * 进站总量（仅转运作业类任务，单位：吨）
     */
    private BigDecimal totalEntryVolume;
    /**
     * 设备完好率（仅维护类任务）
     */
    private BigDecimal equipmentIntactRate;
    /**
     * 环境达标率（仅预警类任务）
     */
    private BigDecimal environmentQualifiedRate;
    /**
     * 满意度（仅投诉类任务，可选值：满意/基本满意/不满意）
     */
    private String satisfaction;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    private String extCommon4;

}