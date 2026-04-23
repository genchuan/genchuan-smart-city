package cn.iocoder.yudao.module.inspectop.controller.admin.assetcheck.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "巡查巡检 - 资产盘点图表统计 Request VO")
@Data
public class AssetCheckChartReqVO {

    @Schema(description = "时间范围")
    @Size(max = 2, message = "时间范围数组长度不能超过2")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] timeRange;
}