package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 空位推送分页 Request VO")
@Data
public class SpacePushPageReqVO extends PageParam {

    @Schema(description = "用户 ID", example = "1001")
    private Long userId;

    @Schema(description = "场站 ID")
    private Long stationId;

    @Schema(description = "推送状态，关联字典 space_push_status", example = "待推送")
    private String status;

    @Schema(description = "推送时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] pushTime;

}
