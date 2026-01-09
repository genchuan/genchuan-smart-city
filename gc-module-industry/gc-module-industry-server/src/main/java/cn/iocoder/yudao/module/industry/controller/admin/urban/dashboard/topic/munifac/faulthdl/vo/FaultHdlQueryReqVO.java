package cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.faulthdl.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 市政设施专题-标记故障处置完成 查询 Request VO")
@Data
public class FaultHdlQueryReqVO {

    @Schema(description = "查询地区-省市县三级shortCode码，6位", example = "110000")
    private String regionShortCode;

    @Schema(description = "统计开始时间（含）,yyyy-MM-dd HH:mm:ss", example = "2025-01-01 09:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @Schema(description = "统计结束时间（含）,yyyy-MM-dd HH:mm:ss", example = "2025-11-01 23:59:59")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

}
