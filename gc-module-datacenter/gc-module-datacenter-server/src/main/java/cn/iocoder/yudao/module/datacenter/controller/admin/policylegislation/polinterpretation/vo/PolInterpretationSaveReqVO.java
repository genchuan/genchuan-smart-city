package cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.polinterpretation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 政策解读信息新增/修改 Request VO")
@Data
public class PolInterpretationSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20156")
    private Long id;

    @Schema(description = "解读ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "105")
    @NotEmpty(message = "解读ID，唯一编码，UUID生成不能为空")
    private String polInterpretationId;

    @Schema(description = "关联政策类型，取值为国家政策/地方法规，标识关联政策类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "关联政策类型，取值为国家政策/地方法规，标识关联政策类型不能为空")
    private String relatedPolType;

    @Schema(description = "关联政策ID，关联国家政策/地方法规ID，国家政策信息表（biz_national_pol）/地方法规信息表（biz_local_regulation）", requiredMode = Schema.RequiredMode.REQUIRED, example = "31843")
    @NotEmpty(message = "关联政策ID，关联国家政策/地方法规ID，国家政策信息表（biz_national_pol）/地方法规信息表（biz_local_regulation）不能为空")
    private String relatedPolId;

    @Schema(description = "关联政策标题，与关联政策ID同步，国家政策信息表（biz_national_pol）/地方法规信息表（biz_local_regulation）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联政策标题，与关联政策ID同步，国家政策信息表（biz_national_pol）/地方法规信息表（biz_local_regulation）不能为空")
    private String relatedPolTitle;

    @Schema(description = "解读标题，如《“十四五”智慧城市发展规划解读》", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "解读标题，如《“十四五”智慧城市发展规划解读》不能为空")
    private String interpretationTitle;

    @Schema(description = "解读详细内容（支持富文本）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "解读详细内容（支持富文本）不能为空")
    private String interpretationContent;

    @Schema(description = "解读人ID，解读人账号，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "4289")
    @NotEmpty(message = "解读人ID，解读人账号，用户信息表（sys_user）不能为空")
    private String interpreterId;

    @Schema(description = "解读人姓名，与解读人ID同步，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "解读人姓名，与解读人ID同步，用户信息表（sys_user）不能为空")
    private String interpreterName;

    @Schema(description = "解读时间，格式：YYYYMMDD", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "解读时间，格式：YYYYMMDD不能为空")
    private LocalDateTime interpretationTime;

    @Schema(description = "解读状态：已发布/未发布，标识解读是否公开", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "解读状态：已发布/未发布，标识解读是否公开不能为空")
    private String interpretationStatus;

    @Schema(description = "附件ID，关联解读附件ID，文件存储表（sys_file_storage）", example = "13739")
    private String attachId;

    @Schema(description = "附件路径，解读附件存储路径，文件存储表（sys_file_storage）")
    private String attachPath;

    @Schema(description = "创建人，创建人ID，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人，创建人ID，用户信息表（sys_user）不能为空")
    private String createUser;

    @Schema(description = "更新人，更新人ID，用户信息表（sys_user）")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“解读类型：官方/专家”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“解读范围：全国/地方”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，如“解读来源：政府官网”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，如“关联业务：设备部署”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}