package cn.iocoder.yudao.module.industry.controller.admin.park.asset.passrule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 通行规则新增/修改 Request VO")
@Data
public class ParkPassRuleSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20418")
    private Long id;

    @Schema(description = "通行规则ID（UUID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "24778")
    @NotEmpty(message = "通行规则ID（UUID）不能为空")
    private String passRuleId;

    @Schema(description = "规则名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "规则名称不能为空")
    private String ruleName;

    @Schema(description = "关联出入口ID", example = "15051")
    private String entryExitId;

    @Schema(description = "允许车辆类型")
    private String allowCarTypes;

    @Schema(description = "禁止车辆类型")
    private String forbidCarTypes;

    @Schema(description = "高峰时段规则")
    private String peakTimeRule;

    @Schema(description = "平峰时段规则")
    private String offPeakTimeRule;

    @Schema(description = "状态：启用/禁用", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "状态：启用/禁用不能为空")
    private String status;

    @Schema(description = "业务创建时间")
    private LocalDateTime passRuleCreateTime;

    @Schema(description = "业务更新时间")
    private LocalDateTime passRuleUpdateTime;

    @Schema(description = "业务备注", example = "你说的对")
    private String passRuleRemark;

}