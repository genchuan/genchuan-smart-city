package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 库存管控 Response VO")
@Data
public class StockControlRespVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "卡种ID")
    private Long cardId;

    @Schema(description = "当前库存")
    private Integer currentStock;

    @Schema(description = "预警阈值")
    private Integer warnThreshold;

    @Schema(description = "库存状态")
    private String status;

    @Schema(description = "告警状态")
    private String warnStatus;

    @Schema(description = "同步时间")
    private LocalDateTime syncTime;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
