package cn.iocoder.yudao.module.studentmgmt.controller.admin.leavehandle.vo;

import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.ChartTrendVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 毕业生离校指标统计 response VO")
@Data
public class LeaveHandleIndexRespVO {

    @Schema(description = "退宿完成率")
    private BigDecimal checkoutRate;
    @Schema(description = "家长确认率")
    private BigDecimal parentConfirmRate;
    @Schema(description = "离校办理完成率")
    private BigDecimal handleFinishRate;
    @Schema(description = "每日离校人数趋势")
    private List<ChartTrendVO> dailyLeaveCount;


}