package cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.generaloperguide.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 通用操作指南新增/修改 Request VO")
@Data
public class GeneralOperGuideSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27691")
    private Long id;

    @Schema(description = "指南ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "30631")
    @NotEmpty(message = "指南ID，唯一编码，UUID生成不能为空")
    private String generalOperGuideId;

    @Schema(description = "指南标题，如《系统登录与权限切换指南》", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "指南标题，如《系统登录与权限切换指南》不能为空")
    private String guideTitle;

    @Schema(description = "操作场景，取值如登录/权限/数据操作，操作场景字典表（sys_dict_oper_scenario）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "操作场景，取值如登录/权限/数据操作，操作场景字典表（sys_dict_oper_scenario）不能为空")
    private String operScenario;

    @Schema(description = "操作步骤（支持富文本，含截图插入）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "操作步骤（支持富文本，含截图插入）不能为空")
    private String operSteps;

    @Schema(description = "适用角色，如“所有用户”“管理员”，角色表（sys_role）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "适用角色，如“所有用户”“管理员”，角色表（sys_role）不能为空")
    private String applicableRole;

    @Schema(description = "版本号，如“V1.0”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "版本号，如“V1.0”不能为空")
    private String version;

    @Schema(description = "生效时间，格式：YYYYMMDD", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "生效时间，格式：YYYYMMDD不能为空")
    private LocalDateTime effectiveTime;

    @Schema(description = "指南附件ID，关联附件ID（如操作视频），文件存储表（sys_file_storage）", example = "28872")
    private String guideAttachId;

    @Schema(description = "指南附件路径，附件存储路径，文件存储表（sys_file_storage）")
    private String guideAttachPath;

    @Schema(description = "指南状态：已发布/草稿，标识指南是否公开", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "指南状态：已发布/草稿，标识指南是否公开不能为空")
    private String guideStatus;

    @Schema(description = "创建人，创建人ID，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人，创建人ID，用户信息表（sys_user）不能为空")
    private String createUser;

    @Schema(description = "更新人，更新人ID，用户信息表（sys_user）")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“指南类型：文字/视频”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“难度等级：简单/中等”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，如“关联功能：数据导入”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，如“查看次数”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}