package cn.iocoder.yudao.module.studentmgmt.controller.admin.targetmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 启用/停用 Request VO")
@Data
public class TargetMgmtEnableReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20828")
    private Integer[] ids;

}