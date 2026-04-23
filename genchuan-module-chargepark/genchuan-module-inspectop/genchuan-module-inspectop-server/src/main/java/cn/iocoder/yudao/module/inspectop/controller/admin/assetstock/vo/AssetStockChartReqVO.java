package cn.iocoder.yudao.module.inspectop.controller.admin.assetstock.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "巡查巡检 - 库存统计图表 Request VO")
@Data
public class AssetStockChartReqVO {

    @Schema(description = "时间范围")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime[] timeRange;
}