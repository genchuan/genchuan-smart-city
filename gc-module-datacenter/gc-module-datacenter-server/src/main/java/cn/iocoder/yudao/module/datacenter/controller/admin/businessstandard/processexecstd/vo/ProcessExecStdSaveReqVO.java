package cn.iocoder.yudao.module.datacenter.controller.admin.businessstandard.processexecstd.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 流程执行规范新增/修改 Request VO")
@Data
public class ProcessExecStdSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "578")
    private Long id;

    @Schema(description = "规范ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "6934")
    @NotEmpty(message = "规范ID，唯一编码，UUID生成不能为空")
    private String processExecStdId;

    @Schema(description = "规范名称，如《事件上报-分派-处置流程规范》", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "规范名称，如《事件上报-分派-处置流程规范》不能为空")
    private String stdName;

    @Schema(description = "关联流程ID，关联业务流程ID，业务流程表（sys_business_process）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2204")
    @NotEmpty(message = "关联流程ID，关联业务流程ID，业务流程表（sys_business_process）不能为空")
    private String relatedProcessId;

    @Schema(description = "关联流程名称，与关联流程ID同步，业务流程表（sys_business_process）", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "关联流程名称，与关联流程ID同步，业务流程表（sys_business_process）不能为空")
    private String relatedProcessName;

    @Schema(description = "流程步骤（支持富文本，如“1.上报：市民/工作人员上报事件”）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "流程步骤（支持富文本，如“1.上报：市民/工作人员上报事件”）不能为空")
    private String processSteps;

    @Schema(description = "责任部门代码，责任部门统一社会信用代码，部门信息表（sys_org）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "责任部门代码，责任部门统一社会信用代码，部门信息表（sys_org）不能为空")
    private String deptCode;

    @Schema(description = "责任部门名称，与责任部门代码同步，部门信息表（sys_org）", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "责任部门名称，与责任部门代码同步，部门信息表（sys_org）不能为空")
    private String deptName;

    @Schema(description = "时限要求，各环节时限（如“分派：1小时内，处置：24小时内”）")
    private String timeLimit;

    @Schema(description = "执行要求，执行规范的具体要求")
    private String execRequirement;

    @Schema(description = "规范文件ID，关联规范文件ID，文件存储表（sys_file_storage）", example = "20506")
    private String stdFileId;

    @Schema(description = "规范文件路径，规范文件存储路径，文件存储表（sys_file_storage）")
    private String stdFilePath;

    @Schema(description = "规范状态：已发布/未发布，标识规范是否公开", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "规范状态：已发布/未发布，标识规范是否公开不能为空")
    private String stdStatus;

    @Schema(description = "创建人，创建人ID，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人，创建人ID，用户信息表（sys_user）不能为空")
    private String createUser;

    @Schema(description = "更新人，更新人ID，用户信息表（sys_user）")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“流程类型：事件处置/设备报修”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“适用角色：管理员/处置员”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，如“制定依据：XX业务标准”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，如“修订时间：2024-10-01”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}