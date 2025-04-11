package cn.iocoder.yudao.module.system.controller.admin.riskcontrol.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 风险管控新增/修改 Request VO")
@Data
public class RiskControlSaveReqVO {

    @Schema(description = "风险的唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "29447")
    private Integer id;

    @Schema(description = "风险名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "风险名称不能为空")
    private String riskName;

    @Schema(description = "风险的详细描述", example = "你猜")
    private String riskDescription;

    @Schema(description = "风险等级")
    private String riskLevel;

    @Schema(description = "风险状态", example = "2")
    private String riskStatus;

    @Schema(description = "记录的创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "记录的上次更新时间")
    private LocalDateTime updatedTime;

}