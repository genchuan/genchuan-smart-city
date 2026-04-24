package cn.iocoder.yudao.module.studentmgmt.controller.admin.comparemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 打分 Request VO")
@Data
public class CompareMgmtScoreReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long[] ids;

    @Schema(description = "总得分", requiredMode = Schema.RequiredMode.REQUIRED, example = "90")
    @NotNull(message = "总得分不能为空")
    private BigDecimal totalScore;

    @Schema(description = "打分人", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    private String scoreUser;

}