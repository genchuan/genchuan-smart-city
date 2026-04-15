package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "汽车充电 - 自定义报表生成 Request VO")
@Data
public class SharingReportCustomCreateReqVO {

    @Schema(description = "报表名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "2025 年 Q1 合作方分账统计")
    @NotBlank(message = "报表名称不能为空")
    private String reportName;

    @Schema(description = "合作方（可选，为空则统计全部）", example = "XX 能源科技有限公司")
    private String cooperator;

    @Schema(description = "开始时间（秒级时间戳）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1735689600")
    @NotNull(message = "开始时间不能为空")
    private Long timeStart;

    @Schema(description = "结束时间（秒级时间戳）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1743494400")
    @NotNull(message = "结束时间不能为空")
    private Long timeEnd;
}