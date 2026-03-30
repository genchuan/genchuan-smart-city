package cn.iocoder.yudao.module.waterdetection.controller.admin.leakagecontrolplan.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 漏损控制方案建议分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LeakageControlPlanPageReqVO extends PageParam {

    @Schema(description = "分区ID")
    private String partitionId;

    @Schema(description = "超标漏损率(%)")
    private Double exceededLeakageRate;

    @Schema(description = "压力数据")
    private String pressureData;

    @Schema(description = "管道平均使用年限(年)")
    private Double pipeAvgAge;

    @Schema(description = "建议方案")
    private String suggestedPlan;

    @Schema(description = "方案实施时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] planImplementTime;

    @Schema(description = "实施后漏损率(%)")
    private Double postImplementRate;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}