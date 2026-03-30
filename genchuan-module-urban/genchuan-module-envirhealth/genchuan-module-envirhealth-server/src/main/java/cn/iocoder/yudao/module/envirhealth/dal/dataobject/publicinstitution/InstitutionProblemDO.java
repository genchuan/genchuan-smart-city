package cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 公共机构问题 DO
 *
 * @author 芋道源码
 */
@TableName("public_institution_problem")
@KeySequence("public_institution_problem_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstitutionProblemDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 问题主键（UUID）
     */
    private String problemId;
    /**
     * 关联public_institution.institution_id
     */
    private String institutionId;
    /**
     * 关联sys_problem_type.id
     */
    private String problemTypeId;
    /**
     * 问题位置
     */
    private String location;
    /**
     * 关联sys_user.id
     */
    private String reportBy;
    /**
     * 上报时间
     */
    private LocalDateTime reportTime;
    /**
     * 问题描述
     */
    private String problemDesc;
    /**
     * 派单状态：待派单/已派单/已处置
     */
    private String dispatchStatus;
    /**
     * 关联sys_dept.id
     */
    private String deptId;
    /**
     * 关联sys_user.id
     */
    private String handleBy;
    /**
     * 超时提醒：是/否
     */
    private String isTimeout;
    /**
     * 处置结果
     */
    private String handleResult;
    /**
     * 通用扩展字段1
     */
    @JsonIgnore
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    @JsonIgnore
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    @JsonIgnore
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    @JsonIgnore
    private String extCommon4;

}