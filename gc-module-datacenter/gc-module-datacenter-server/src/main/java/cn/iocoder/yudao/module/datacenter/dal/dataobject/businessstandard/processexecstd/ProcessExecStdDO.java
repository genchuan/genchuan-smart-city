package cn.iocoder.yudao.module.datacenter.dal.dataobject.businessstandard.processexecstd;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 流程执行规范 DO
 *
 * @author 亘川智城
 */
@TableName("biz_process_exec_std")
@KeySequence("biz_process_exec_std_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessExecStdDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 规范ID，唯一编码，UUID生成
     */
    private String processExecStdId;
    /**
     * 规范名称，如《事件上报-分派-处置流程规范》
     */
    private String stdName;
    /**
     * 关联流程ID，关联业务流程ID，业务流程表（sys_business_process）
     */
    private String relatedProcessId;
    /**
     * 关联流程名称，与关联流程ID同步，业务流程表（sys_business_process）
     */
    private String relatedProcessName;
    /**
     * 流程步骤（支持富文本，如“1.上报：市民/工作人员上报事件”）
     */
    private String processSteps;
    /**
     * 责任部门代码，责任部门统一社会信用代码，部门信息表（sys_org）
     */
    private String deptCode;
    /**
     * 责任部门名称，与责任部门代码同步，部门信息表（sys_org）
     */
    private String deptName;
    /**
     * 时限要求，各环节时限（如“分派：1小时内，处置：24小时内”）
     */
    private String timeLimit;
    /**
     * 执行要求，执行规范的具体要求
     */
    private String execRequirement;
    /**
     * 规范文件ID，关联规范文件ID，文件存储表（sys_file_storage）
     */
    private String stdFileId;
    /**
     * 规范文件路径，规范文件存储路径，文件存储表（sys_file_storage）
     */
    private String stdFilePath;
    /**
     * 规范状态：已发布/未发布，标识规范是否公开
     */
    private String stdStatus;
    /**
     * 创建人，创建人ID，用户信息表（sys_user）
     */
    private String createUser;
    /**
     * 更新人，更新人ID，用户信息表（sys_user）
     */
    private String updateUser;
    /**
     * 分类扩展字段1，预留，如“流程类型：事件处置/设备报修”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“适用角色：管理员/处置员”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，如“制定依据：XX业务标准”
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，如“修订时间：2024-10-01”
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