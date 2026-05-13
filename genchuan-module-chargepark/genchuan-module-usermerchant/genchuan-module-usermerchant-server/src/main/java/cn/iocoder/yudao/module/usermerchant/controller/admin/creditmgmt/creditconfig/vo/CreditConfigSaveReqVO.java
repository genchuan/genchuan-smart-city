package cn.iocoder.yudao.module.usermerchant.controller.admin.creditmgmt.creditconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 信用配置新增/修改 Request VO")
@Data
public class CreditConfigSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29133")
    private Long id;

    @Schema(description = "加减分规则", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "加减分规则不能为空")
    private String ruleDesc;

    @Schema(description = "等级阈值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "等级阈值不能为空")
    private String levelThreshold;

    @Schema(description = "配置状态：未生效/已生效", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "配置状态：未生效/已生效不能为空")
    private String status;

    @Schema(description = "生效时间")
    private LocalDateTime effectTime;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}