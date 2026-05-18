package cn.iocoder.yudao.module.studentmgmt.controller.admin.classassign.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 配置 Request VO")
@Data
public class ClassAssignConfigReqVO {

    @Schema(description = "分班规则", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分班规则不能为空")
    private String ruleContent;
    @Schema(description = "分班学生数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "分班学生数不能为空")
    private Integer studentNum;
    @Schema(description = "备注")
    private String remark;
}