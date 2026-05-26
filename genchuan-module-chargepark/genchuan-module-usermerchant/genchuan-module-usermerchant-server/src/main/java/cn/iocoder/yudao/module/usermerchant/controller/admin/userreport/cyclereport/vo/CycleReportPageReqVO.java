package cn.iocoder.yudao.module.usermerchant.controller.admin.userreport.cyclereport.vo;

import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.FlexibleTimestampDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 周期报表存储分页 Request VO")
@Data
public class CycleReportPageReqVO extends PageParam {

    @Schema(description = "报表周期（日报/周报/月报/季报/半年报/年报/自定义报表）")
    private String reportCycle;

    @Schema(description = "统计开始时间")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    private LocalDateTime statStartTime;

    @Schema(description = "统计结束时间")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    private LocalDateTime statEndTime;

    @Schema(description = "报表生成状态（待生成/已生成/生成失败）", example = "1")
    private String reportStatus;

}