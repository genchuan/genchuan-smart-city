package cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompcoderule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 监测部件标识码规则新增/修改 Request VO")
@Data
public class MonCompCodeRuleSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3866")
    private Long id;

    @Schema(description = "规则ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "17420")
    @NotEmpty(message = "规则ID，唯一编码，UUID生成不能为空")
    private String codeRuleId;

    @Schema(description = "规则名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "规则名称不能为空")
    private String ruleName;

    @Schema(description = "规则说明")
    private String ruleDesc;

    @Schema(description = "编码格式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "编码格式不能为空")
    private String codeFormat;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}