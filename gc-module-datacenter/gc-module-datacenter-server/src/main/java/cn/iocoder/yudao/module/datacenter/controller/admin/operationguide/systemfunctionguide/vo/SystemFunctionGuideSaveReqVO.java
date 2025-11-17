package cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.systemfunctionguide.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 系统功能指南新增/修改 Request VO")
@Data
public class SystemFunctionGuideSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "147")
    private Long id;

    @Schema(description = "指南ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "14500")
    @NotEmpty(message = "指南ID，唯一编码，UUID生成不能为空")
    private String systemFunctionGuideId;

    @Schema(description = "指南标题，如《地理编码坐标系配置指南》", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "指南标题，如《地理编码坐标系配置指南》不能为空")
    private String guideTitle;

    @Schema(description = "关联功能ID，关联系统功能ID，系统功能表（sys_system_function）", requiredMode = Schema.RequiredMode.REQUIRED, example = "21517")
    @NotEmpty(message = "关联功能ID，关联系统功能ID，系统功能表（sys_system_function）不能为空")
    private String relatedFunctionId;

    @Schema(description = "关联功能名称，与关联功能ID同步，系统功能表（sys_system_function）", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "关联功能名称，与关联功能ID同步，系统功能表（sys_system_function）不能为空")
    private String relatedFunctionName;

    @Schema(description = "功能用途说明，如“配置坐标系，确保地理数据统一”")
    private String functionPurpose;

    @Schema(description = "操作步骤（支持富文本，含截图）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "操作步骤（支持富文本，含截图）不能为空")
    private String operSteps;

    @Schema(description = "常见问题及解决方案，如“坐标系配置失败：检查代码唯一性”")
    private String commonProblems;

    @Schema(description = "适用角色，如“管理员”，角色表（sys_role）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "适用角色，如“管理员”，角色表（sys_role）不能为空")
    private String applicableRole;

    @Schema(description = "指南附件ID，关联附件ID（如操作视频），文件存储表（sys_file_storage）", example = "29800")
    private String guideAttachId;

    @Schema(description = "指南附件路径，附件存储路径，文件存储表（sys_file_storage）")
    private String guideAttachPath;

    @Schema(description = "指南状态：已发布/草稿，标识指南是否公开", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "指南状态：已发布/草稿，标识指南是否公开不能为空")
    private String guideStatus;

    @Schema(description = "创建人，创建人ID，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人，创建人ID，用户信息表（sys_user）不能为空")
    private String createUser;

    @Schema(description = "更新人，更新人ID，用户信息表（sys_user）")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“功能模块：地理编码/网格管理”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“更新频率：季度/年度”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，如“关联政策：GB/T 2260”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，如“查看次数”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}