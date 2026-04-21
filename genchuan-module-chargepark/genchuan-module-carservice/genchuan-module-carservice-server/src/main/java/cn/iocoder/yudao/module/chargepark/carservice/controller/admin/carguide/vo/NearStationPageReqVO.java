package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 周边场站分页 Request VO")
@Data
public class NearStationPageReqVO extends PageParam {

    @Schema(description = "用户 ID", example = "1001")
    private Long userId;

    @Schema(description = "查询位置汉字地址（模糊查询）", example = "泉州")
    private String queryLocationName;

    @Schema(description = "查询时间范围（数组 2 个元素：起始时间、结束时间）",
            example = "[\"2025-04-01 00:00:00\", \"2025-04-14 23:59:59\"]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] queryTime;

}
