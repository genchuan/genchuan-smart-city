package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 预约列表分页 Request VO")
@Data
public class ReserveListPageReqVO extends PageParam {

    @Schema(description = "用户 ID", example = "1001")
    private Long userId;

    @Schema(description = "场站 ID")
    private Long stationId;

    @Schema(description = "车位 ID")
    private Long spaceId;

    @Schema(description = "预约类型,关联字典 reserve_list_reserve_type", example = "停车预约",
            allowableValues = {"停车预约", "充电预约"})
    private String reserveType;

    @Schema(description = "预约状态,关联字典 reserve_list_status", example = "待审核",
            allowableValues = {"待审核", "已生效", "已完成", "已取消"})
    private String status;

    @Schema(description = "预约时间范围(长度 2 的数组:[起始时间, 结束时间])。" +
            "前端示例:axios.get(url,{params:{reserveTime:[start,end]}}) — 不要 JSON.stringify,最终 HTTP 是两次同名 query:?reserveTime=start&reserveTime=end",
            example = "[\"2025-04-01 00:00:00\", \"2025-04-14 23:59:59\"]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] reserveTime;

}
