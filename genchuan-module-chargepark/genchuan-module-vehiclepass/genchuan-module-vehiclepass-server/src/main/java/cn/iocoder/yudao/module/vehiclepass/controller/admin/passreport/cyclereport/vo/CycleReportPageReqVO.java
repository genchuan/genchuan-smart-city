package cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Schema(description = "管理后台 - 周期报表分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class CycleReportPageReqVO extends PageParam {

    @Schema(description = "报表周期：日报/周报/月报/季报/半年报/年报/自定义报表")
    private String reportCycle;

    @Schema(description = "场站ID")
    private Long stationId;

    @Schema(description = "场站名称，支持模糊查询")
    private String stationName;

    @Schema(description = "报表生成状态")
    private String reportStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "统计开始时间")
    private LocalDateTime beginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "统计结束时间")
    private LocalDateTime endTime;

    @Schema(description = "租户ID")
    private Long tenantId;

}