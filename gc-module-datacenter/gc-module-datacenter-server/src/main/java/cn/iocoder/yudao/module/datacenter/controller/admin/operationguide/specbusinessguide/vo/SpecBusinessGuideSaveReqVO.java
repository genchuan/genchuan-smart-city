package cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.specbusinessguide.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 专项业务指南新增/修改 Request VO")
@Data
public class SpecBusinessGuideSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7055")
    private Long id;

    @Schema(description = "指南ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "30041")
    @NotEmpty(message = "指南ID，唯一编码，UUID生成不能为空")
    private String specBusinessGuideId;

    @Schema(description = "指南标题，如《城管事件上报处置指南》", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "指南标题，如《城管事件上报处置指南》不能为空")
    private String guideTitle;

    @Schema(description = "业务领域代码，取值如01=城管，02=交通，03=环保，业务领域字典表（sys_dict_business_field）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "业务领域代码，取值如01=城管，02=交通，03=环保，业务领域字典表（sys_dict_business_field）不能为空")
    private String businessFieldCode;

    @Schema(description = "业务领域名称，与业务领域代码同步，业务领域字典表（sys_dict_business_field）", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "业务领域名称，与业务领域代码同步，业务领域字典表（sys_dict_business_field）不能为空")
    private String businessFieldName;

    @Schema(description = "关联业务表名，关联专项业务表中文名称（如“城管事件表”），业务表字典表（sys_dict_business_table）", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "关联业务表名，关联专项业务表中文名称（如“城管事件表”），业务表字典表（sys_dict_business_table）不能为空")
    private String relatedTableName;

    @Schema(description = "关联业务表编码，关联专项业务表英文名称（如“biz_urban_evt”），业务表字典表（sys_dict_business_table）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联业务表编码，关联专项业务表英文名称（如“biz_urban_evt”），业务表字典表（sys_dict_business_table）不能为空")
    private String relatedTableCode;

    @Schema(description = "业务流程说明，如“上报→分派→处置→结案”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "业务流程说明，如“上报→分派→处置→结案”不能为空")
    private String businessProcessDesc;

    @Schema(description = "操作步骤（支持富文本，如“1.上报：录入事件类型、发生地点；2.分派：选择处置部门”）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "操作步骤（支持富文本，如“1.上报：录入事件类型、发生地点；2.分派：选择处置部门”）不能为空")
    private String operSteps;

    @Schema(description = "业务数据录入规范（如“事件类型需选择字典表枚举值，发生地点需含经纬度”）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "业务数据录入规范（如“事件类型需选择字典表枚举值，发生地点需含经纬度”）不能为空")
    private String dataSpec;

    @Schema(description = "适用角色，如“城管工作人员”，角色表（sys_role）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "适用角色，如“城管工作人员”，角色表（sys_role）不能为空")
    private String applicableRole;

    @Schema(description = "指南附件ID，关联附件ID（如操作视频、示例表格），文件存储表（sys_file_storage）", example = "29374")
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

    @Schema(description = "分类扩展字段1，预留，如“业务优先级：高/中/低”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“数据来源：人工上报/设备采集”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，如“关联政策：GB/T 30428.2”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，如“查看次数：120次”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}