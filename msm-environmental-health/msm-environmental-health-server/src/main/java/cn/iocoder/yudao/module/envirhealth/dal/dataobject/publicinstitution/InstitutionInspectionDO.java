package cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 公共机构核查 DO
 *
 * @author 芋道源码
 */
@TableName("public_institution_inspection")
@KeySequence("public_institution_inspection_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstitutionInspectionDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 核查主键（UUID）
     */
    private String inspectionId;
    /**
     * 关联public_institution.institution_id
     */
    private String institutionId;
    /**
     * 关联task.task_id
     */
    private String taskId;
    /**
     * 关联sys_task_type.id
     */
    private String taskTypeId;
    /**
     * 关联sys_user.id
     */
    private String reportBy;
    /**
     * 上报时间
     */
    private LocalDateTime reportTime;
    /**
     * 上报结果
     */
    private String reportResult;
    /**
     * 核查状态：待核查/达标/不达标
     */
    private String inspectionStatus;
    /**
     * 关联sys_user.id
     */
    private String inspectBy;
    /**
     * 核查时间
     */
    private LocalDateTime inspectionTime;
    /**
     * 整改要求
     */
    private String reformRequire;
    /**
     * 核查照片URL，JSON
     */
    private String inspectionPhoto;
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