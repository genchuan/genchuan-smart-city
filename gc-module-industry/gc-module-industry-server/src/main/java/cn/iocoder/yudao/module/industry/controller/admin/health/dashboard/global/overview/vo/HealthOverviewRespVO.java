package cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.overview.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 卫健全域数据概览 Response VO")
@Data
public class HealthOverviewRespVO {

    @Schema(description = "区域医疗机构总数", requiredMode = Schema.RequiredMode.REQUIRED, example = "120")
    @ExcelProperty("医疗机构总数")
    private Integer medicalInstCount;

    @Schema(description = "重点人群总数", requiredMode = Schema.RequiredMode.REQUIRED, example = "500")
    @ExcelProperty("重点人群总数")
    private Integer keyPopTotal;

    @Schema(description = "卫健服务总人次", requiredMode = Schema.RequiredMode.REQUIRED, example = "3000")
    @ExcelProperty("卫健服务总人次")
    private Integer totalSvcVolume;

    @Schema(description = "预警事件总数", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    @ExcelProperty("预警事件总数")
    private Integer earlyWarningTotal;

    @Schema(description = "床位使用率（百分比）", requiredMode = Schema.RequiredMode.REQUIRED, example = "85")
    @ExcelProperty("床位使用率")
    private BigDecimal bedUtilizationRate;

}
