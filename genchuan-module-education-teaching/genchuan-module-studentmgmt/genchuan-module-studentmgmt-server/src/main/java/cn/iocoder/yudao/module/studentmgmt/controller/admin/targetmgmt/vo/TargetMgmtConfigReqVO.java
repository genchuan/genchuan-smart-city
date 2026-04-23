package cn.iocoder.yudao.module.studentmgmt.controller.admin.targetmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 指标管理配置 Request VO")
@Data
public class TargetMgmtConfigReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20828")
    private Long[] ids;


    @Schema(description = "指标总分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "指标总分不能为空")
    private BigDecimal totalScore;

    @Schema(description = "预警阈值")
    private BigDecimal warnThreshold;

    @Schema(description = "评价人类型：教职工/家长/领导", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "评价人类型：教职工/家长/领导不能为空")
    private String evaluatorType;

    @Schema(description = "计分方式：累计赋分/接口赋分", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "计分方式：累计赋分/接口赋分不能为空")
    private String scoreType;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}