package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coremetrics.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 通用大屏核心指标看板 查询 Request VO")
@Data
public class UniversalCoreMetricsQueryReqVO {

    @Schema(description = "查询地区-省市县三级shortCode码，6位", example = "110000")
    private String regionShortCode;

    @Schema(description = "统计周期，如月度 YYYYMM，如202509", example = "202509")
    private String statCycle;
}
