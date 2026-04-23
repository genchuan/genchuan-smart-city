package cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.paycheck.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;

@Schema(description = "管理后台 - 缴费核验统计 Response VO")
@Data
public class PayCheckChartRespVO {

    @Schema(description = "核验成功率趋势，折线图数据")
    private List<CheckSuccessTrend> checkSuccessTrend;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Data
    @Schema(description = "核验成功率趋势")
    public static class CheckSuccessTrend {
        @Schema(description = "日期", example = "2025-04-07")
        private String date;
        @Schema(description = "成功率", example = "99.2")
        private Double rate;
    }

    @Data
    @Schema(description = "卡片数据")
    public static class CardData {
        @Schema(description = "核验成功率", example = "99.3")
        private Double checkSuccessRate;
        @Schema(description = "平均核验时长（小时）", example = "0.15")
        private Double avgCheckDuration;
    }

}