package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "服务运营报表 - 同比/环比对比响应 VO")
@Data
public class ServiceOpReportCompareRespVO {

    @Schema(description = "时间尺度标签", example = "日报")
    private String periodLabel;

    @Schema(description = "对比类型 yoy=同比 / mom=环比", example = "yoy")
    private String compareType;

    @Schema(description = "本期报表")
    private TimeReportRespVO current;

    @Schema(description = "上期报表(同比为去年同期,环比为上一周期)")
    private TimeReportRespVO previous;

}
