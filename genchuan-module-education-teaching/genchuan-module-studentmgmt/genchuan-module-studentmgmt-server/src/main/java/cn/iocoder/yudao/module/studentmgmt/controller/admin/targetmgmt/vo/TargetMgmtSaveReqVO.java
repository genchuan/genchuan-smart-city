package cn.iocoder.yudao.module.studentmgmt.controller.admin.targetmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 指标管理新增/修改 Request VO")
@Data
public class TargetMgmtSaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20828")
    private Long id;

    @Schema(description = "指标名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "指标名称不能为空")
    private String targetName;

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

    @Schema(description = "启用时间")
    private LocalDateTime enableTime;

    @Schema(description = "停用时间")
    private LocalDateTime disableTime;

    @Schema(description = "状态：未启用/已启用", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "状态：未启用/已启用不能为空")
    private String status;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}