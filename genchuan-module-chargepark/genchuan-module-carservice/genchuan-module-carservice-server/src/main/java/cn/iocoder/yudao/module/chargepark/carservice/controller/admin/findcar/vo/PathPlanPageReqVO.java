package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 路径规划分页 Request VO")
@Data
public class PathPlanPageReqVO extends PageParam {

    @Schema(description = "用户 ID", example = "1001")
    private Long userId;

    @Schema(description = "起点位置汉字地址(支持模糊查询;匹配 start_location_name 列)", example = "入口")
    private String startLocation;

    @Schema(description = "终点位置汉字地址(支持模糊查询;匹配 end_location_name 列)", example = "A 区")
    private String endLocation;

    @Schema(description = "规划时间范围(长度 2 的数组:[起始时间, 结束时间])。" +
            "前端示例:axios.get(url,{params:{planTime:[start,end]}}) — 不要 JSON.stringify,最终 HTTP 是两次同名 query:?planTime=start&planTime=end",
            example = "[\"2025-04-01 00:00:00\", \"2025-04-14 23:59:59\"]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] planTime;

}
