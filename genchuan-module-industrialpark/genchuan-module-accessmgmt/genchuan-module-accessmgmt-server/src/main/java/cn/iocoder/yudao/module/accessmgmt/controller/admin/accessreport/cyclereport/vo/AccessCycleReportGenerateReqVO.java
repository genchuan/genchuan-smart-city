package cn.iocoder.yudao.module.accessmgmt.controller.admin.accessreport.cyclereport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "管理后台 - 通行周期报表生成 Request VO")
@Data
public class AccessCycleReportGenerateReqVO {

    @Schema(description = "周期类型（日报/周报/月报/季报/半年报/年报/自定义）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "周期类型不能为空")
    private String cycleType;

    @Schema(description = "开始时间，格式时间戳（自定义时必填）")
    private String startTime;

    @Schema(description = "结束时间，格式时间戳（自定义时必填）")
    private String endTime;

}
