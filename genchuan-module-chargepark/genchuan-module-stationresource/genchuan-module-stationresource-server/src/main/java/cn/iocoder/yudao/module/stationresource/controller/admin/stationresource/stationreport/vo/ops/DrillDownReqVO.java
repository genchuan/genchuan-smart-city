package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 场站资源报表 — 钻取请求 VO
 *
 * @author vrvliang
 * @version V1.0 2026-05-11 10:07
 */
@Schema(description = "场站资源报表 — 钻取请求")
@Data
public class DrillDownReqVO {

    @Schema(description = "卡片指标：总片区数/覆盖场站数/总场站数/正常运营数/总车位数/可用车位数/生效规则数/订单量/营收/追缴完成率/押金订单量",
            requiredMode = Schema.RequiredMode.REQUIRED, example = "总片区数")
    @NotBlank(message = "卡片指标不能为空")
    private String metric;

    @Schema(description = "报表周期：日报/周报/月报/季报/半年报/年报/自定义报表", example = "日报")
    private String reportCycle;

    @Schema(description = "报表开始时间",hidden = true)
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime reportStartTime;

    @Schema(description = "报表结束时间",hidden = true)
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime reportEndTime;

    @Schema(description = "页码", example = "1")
    private Integer pageNo ;

    @Schema(description = "每页条数", example = "10")
    private Integer pageSize ;
}
