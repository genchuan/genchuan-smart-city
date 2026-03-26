package cn.iocoder.yudao.module.waterdetection.controller.admin.instrumentcalibration.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 仪器零点/量程漂移校验分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InstrumentCalibrationPageReqVO extends PageParam {

    @Schema(description = "仪器ID")
    private String instrumentId;

    @Schema(description = "校验日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] calibrationDate;

    @Schema(description = "零点校正液浓度")
    private Double zeroPointConc;

    @Schema(description = "零点漂移值")
    private Double zeroDrift;

    @Schema(description = "量程校正液浓度")
    private Double spanConc;

    @Schema(description = "量程漂移值")
    private Double spanDrift;

    @Schema(description = "校验结果")
    private String calibrationResult;

    @Schema(description = "操作人员ID")
    private String operatorId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}