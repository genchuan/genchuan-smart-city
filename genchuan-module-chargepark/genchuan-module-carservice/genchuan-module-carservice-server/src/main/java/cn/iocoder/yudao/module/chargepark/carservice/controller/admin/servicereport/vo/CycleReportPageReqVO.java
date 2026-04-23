package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周期报表 分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class CycleReportPageReqVO extends PageParam {

    @Schema(description = "报表周期", example = "月报",
            allowableValues = {"日报", "周报", "月报", "季报", "半年报", "年报", "自定义报表"})
    private String reportCycle;

    @Schema(description = "统计开始时间", example = "2026-03-01 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime statStartTime;

    @Schema(description = "统计结束时间", example = "2026-03-31 23:59:59")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime statEndTime;

    @Schema(description = "生成状态", example = "已生成",
            allowableValues = {"已生成", "生成中", "生成失败"})
    private String generateStatus;

}
