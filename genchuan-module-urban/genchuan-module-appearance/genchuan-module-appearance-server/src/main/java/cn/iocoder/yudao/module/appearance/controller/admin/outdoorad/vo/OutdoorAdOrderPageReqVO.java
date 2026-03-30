package cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 广告整改工单分页 Ad Order Page Request VO")
@Data
@ToString(callSuper = true)
public class OutdoorAdOrderPageReqVO extends PageParam {
    @Schema(description = "工单编码")
    private String orderCode;

    @Schema(description = "关联广告ID")
    private String outdoorAdId;

    @Schema(description = "关联广告名称")
    private String adName;

    @Schema(description = "工单状态")
    private String orderStatus;

    @Schema(description = "处理人ID")
    private String handlerUserId;

    @Schema(description = "工单创建开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime startCreateTime;

    @Schema(description = "工单创建结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endCreateTime;
}
