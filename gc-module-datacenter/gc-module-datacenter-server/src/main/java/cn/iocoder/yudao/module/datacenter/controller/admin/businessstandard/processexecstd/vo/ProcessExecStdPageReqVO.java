package cn.iocoder.yudao.module.datacenter.controller.admin.businessstandard.processexecstd.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 流程执行规范分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProcessExecStdPageReqVO extends PageParam {

    @Schema(description = "规范ID，唯一编码，UUID生成", example = "6934")
    private String processExecStdId;

    @Schema(description = "规范名称，如《事件上报-分派-处置流程规范》", example = "赵六")
    private String stdName;

    @Schema(description = "关联流程ID，关联业务流程ID，业务流程表（sys_business_process）", example = "2204")
    private String relatedProcessId;

    @Schema(description = "关联流程名称，与关联流程ID同步，业务流程表（sys_business_process）", example = "芋艿")
    private String relatedProcessName;

    @Schema(description = "流程步骤（支持富文本，如“1.上报：市民/工作人员上报事件”）")
    private String processSteps;

    @Schema(description = "责任部门代码，责任部门统一社会信用代码，部门信息表（sys_org）")
    private String deptCode;

    @Schema(description = "责任部门名称，与责任部门代码同步，部门信息表（sys_org）", example = "芋艿")
    private String deptName;

    @Schema(description = "时限要求，各环节时限（如“分派：1小时内，处置：24小时内”）")
    private String timeLimit;

    @Schema(description = "执行要求，执行规范的具体要求")
    private String execRequirement;

    @Schema(description = "规范文件ID，关联规范文件ID，文件存储表（sys_file_storage）", example = "20506")
    private String stdFileId;

    @Schema(description = "规范文件路径，规范文件存储路径，文件存储表（sys_file_storage）")
    private String stdFilePath;

    @Schema(description = "规范状态：已发布/未发布，标识规范是否公开", example = "1")
    private String stdStatus;

    @Schema(description = "创建人，创建人ID，用户信息表（sys_user）")
    private String createUser;

    @Schema(description = "创建时间，格式：yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

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