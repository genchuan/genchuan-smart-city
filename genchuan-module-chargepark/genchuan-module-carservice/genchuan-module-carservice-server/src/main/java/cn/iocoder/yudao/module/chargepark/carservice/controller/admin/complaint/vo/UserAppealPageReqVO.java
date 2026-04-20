package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 用户申诉分页 Request VO")
@Data
public class UserAppealPageReqVO extends PageParam {

    @Schema(description = "用户 ID", example = "1001")
    private Long userId;

    @Schema(description = "订单 ID")
    private Long orderId;

    @Schema(description = "申诉状态，关联字典 user_appeal_status", example = "待审核")
    private String status;

    @Schema(description = "提交时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] submitTime;

}
