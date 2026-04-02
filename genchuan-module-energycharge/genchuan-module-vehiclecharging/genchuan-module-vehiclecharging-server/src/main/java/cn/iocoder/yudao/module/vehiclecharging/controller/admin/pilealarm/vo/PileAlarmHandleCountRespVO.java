package cn.iocoder.yudao.module.vehiclecharging.controller.admin.pilealarm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "告警处置统计 - 响应结果")
public class PileAlarmHandleCountRespVO {

    @Schema(description = "未派单数量", example = "10")
    private Integer unDisCount;

    @Schema(description = "已派单数量", example = "2")
    private Integer disCount;

    @Schema(description = "处置中数量", example = "0")
    private Integer handlingCount;

    @Schema(description = "已销单数量", example = "108")
    private Integer closedCount;

    @Schema(description = "总数量", example = "120")
    private Integer totalCount;

}