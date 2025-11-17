package cn.iocoder.yudao.module.datacenter.controller.admin.businessstandard.processexecstd.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 流程执行规范 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ProcessExecStdRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "578")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "规范ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "6934")
    @ExcelProperty("规范ID，唯一编码，UUID生成")
    private String processExecStdId;

    @Schema(description = "规范名称，如《事件上报-分派-处置流程规范》", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("规范名称，如《事件上报-分派-处置流程规范》")
    private String stdName;

    @Schema(description = "关联流程ID，关联业务流程ID，业务流程表（sys_business_process）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2204")
    @ExcelProperty("关联流程ID，关联业务流程ID，业务流程表（sys_business_process）")
    private String relatedProcessId;

    @Schema(description = "关联流程名称，与关联流程ID同步，业务流程表（sys_business_process）", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("关联流程名称，与关联流程ID同步，业务流程表（sys_business_process）")
    private String relatedProcessName;

    @Schema(description = "流程步骤（支持富文本，如“1.上报：市民/工作人员上报事件”）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("流程步骤（支持富文本，如“1.上报：市民/工作人员上报事件”）")
    private String processSteps;

    @Schema(description = "责任部门代码，责任部门统一社会信用代码，部门信息表（sys_org）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("责任部门代码，责任部门统一社会信用代码，部门信息表（sys_org）")
    private String deptCode;

    @Schema(description = "责任部门名称，与责任部门代码同步，部门信息表（sys_org）", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("责任部门名称，与责任部门代码同步，部门信息表（sys_org）")
    private String deptName;

    @Schema(description = "时限要求，各环节时限（如“分派：1小时内，处置：24小时内”）")
    @ExcelProperty("时限要求，各环节时限（如“分派：1小时内，处置：24小时内”）")
    private String timeLimit;

    @Schema(description = "执行要求，执行规范的具体要求")
    @ExcelProperty("执行要求，执行规范的具体要求")
    private String execRequirement;

    @Schema(description = "规范文件ID，关联规范文件ID，文件存储表（sys_file_storage）", example = "20506")
    @ExcelProperty("规范文件ID，关联规范文件ID，文件存储表（sys_file_storage）")
    private String stdFileId;

    @Schema(description = "规范文件路径，规范文件存储路径，文件存储表（sys_file_storage）")
    @ExcelProperty("规范文件路径，规范文件存储路径，文件存储表（sys_file_storage）")
    private String stdFilePath;

    @Schema(description = "规范状态：已发布/未发布，标识规范是否公开", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("规范状态：已发布/未发布，标识规范是否公开")
    private String stdStatus;

    @Schema(description = "创建人，创建人ID，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人，创建人ID，用户信息表（sys_user）")
    private String createUser;

    @Schema(description = "创建时间，格式：yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间，格式：yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "更新人，更新人ID，用户信息表（sys_user）")
    @ExcelProperty("更新人，更新人ID，用户信息表（sys_user）")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“流程类型：事件处置/设备报修”")
    @ExcelProperty("分类扩展字段1，预留，如“流程类型：事件处置/设备报修”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“适用角色：管理员/处置员”")
    @ExcelProperty("分类扩展字段2，预留，如“适用角色：管理员/处置员”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，如“制定依据：XX业务标准”")
    @ExcelProperty("通用扩展字段1，预留，如“制定依据：XX业务标准”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，如“修订时间：2024-10-01”")
    @ExcelProperty("通用扩展字段2，预留，如“修订时间：2024-10-01”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}