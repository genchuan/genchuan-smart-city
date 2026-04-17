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

    @Schema(description = "预约类型，关联字典 reserve_list_reserve_type", example = "停车预约")
    private String reserveType;

    @Schema(description = "预约状态，关联字典 reserve_list_status", example = "待审核")
    private String status;

    @Schema(description = "预约时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] reserveTime;

}
