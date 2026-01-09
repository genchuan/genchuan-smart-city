package cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Schema(description = "管理后台 - 文旅核心指标查询 Request VO")
public class CultureCoreMetricsQueryReqVO {

    @Schema(description = "时间周期筛选（today/yesterday/recent7/recent30）", example = "recent7")
    private String timeCycle;

    @Schema(hidden = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @Schema(hidden = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
}
