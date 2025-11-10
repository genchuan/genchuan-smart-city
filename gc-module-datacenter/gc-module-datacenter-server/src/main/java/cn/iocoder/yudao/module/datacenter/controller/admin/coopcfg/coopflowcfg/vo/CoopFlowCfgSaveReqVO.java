package cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopflowcfg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 联动流程配置新增/修改 Request VO")
@Data
public class CoopFlowCfgSaveReqVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8922")
    private Long id;

    @Schema(description = "配置ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "4783")
    @NotEmpty(message = "配置ID，唯一编码，UUID生成不能为空")
    private String flowCfgId;

    @Schema(description = "配置名称，如“火灾救援联动流程配置”", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "配置名称，如“火灾救援联动流程配置”不能为空")
    private String flowCfgName;

    @Schema(description = "流程步骤，详细描述联动流程的各个步骤")
    private String flowSteps;

    @Schema(description = "创建人账号，关联用户信息表")
    private String createUser;

    @Schema(description = "更新人账号，关联用户信息表")
    private String updateUser;

}