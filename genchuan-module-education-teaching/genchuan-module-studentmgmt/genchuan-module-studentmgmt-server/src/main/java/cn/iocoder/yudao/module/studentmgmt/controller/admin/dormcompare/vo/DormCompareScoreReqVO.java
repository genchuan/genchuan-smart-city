package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcompare.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 宿舍评比打分 Request VO")
@Data
public class DormCompareScoreReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4709")
    private Long id;

    @Schema(description = "得分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "得分不能为空")
    private BigDecimal score;

}