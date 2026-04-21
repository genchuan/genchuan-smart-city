package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 车位定位分页 Request VO")
@Data
public class SpaceLocationPageReqVO extends PageParam {

    @Schema(description = "用户 ID", example = "1001")
    private Long userId;

    @Schema(description = "车牌号码（支持模糊查询）", example = "闽C12345")
    private String plateNo;

    @Schema(description = "定位结果,关联字典 space_location_location_result", example = "成功",
            allowableValues = {"成功", "失败"})
    private String locationResult;

    @Schema(description = "查询时间范围(长度 2 的数组:[起始时间, 结束时间])。" +
            "前端示例:axios.get(url,{params:{queryTime:[start,end]}}) — 不要 JSON.stringify,最终 HTTP 是两次同名 query:?queryTime=start&queryTime=end",
            example = "[\"2025-04-01 00:00:00\", \"2025-04-14 23:59:59\"]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] queryTime;

}
