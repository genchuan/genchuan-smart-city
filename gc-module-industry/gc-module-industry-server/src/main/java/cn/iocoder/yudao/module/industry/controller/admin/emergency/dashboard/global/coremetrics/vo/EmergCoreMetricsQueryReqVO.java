package cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.coremetrics.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 应急核心指标 查询 Request VO")
@Data
public class EmergCoreMetricsQueryReqVO {

        @Schema(description = "查询地区-省市县的 short_code")
        private String regionCode;

        @Schema(description = "查询周期，例如 YYYYMM 或 YYYY 或 YYYYQn")
        private String statCycle;

        //统计周期起始日期，格式 yyyy-MM-dd",根据statCycle解析来的
        private String statCycleStartDate;

        //统计周期截止日期，格式 yyyy-MM-dd",根据statCycle解析来的
        private String statCycleEndDate;
}
