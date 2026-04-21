package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.decisionanalysis.stationopreport.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "场站运营报表分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class StationOpReportPageReqVO extends PageParam {

    @Schema(description = "报表类型：日报/周报/月报/季报/半年报/年报/自定义报表", example = "月报")
    private String reportType;

    @Schema(description = "开始时间，时间戳(毫秒)", example = "1775011986000")
    private LocalDateTime startTime;

    @Schema(description = "结束时间，时间戳(毫秒)", example = "1777603986000")
    private LocalDateTime endTime;
}
