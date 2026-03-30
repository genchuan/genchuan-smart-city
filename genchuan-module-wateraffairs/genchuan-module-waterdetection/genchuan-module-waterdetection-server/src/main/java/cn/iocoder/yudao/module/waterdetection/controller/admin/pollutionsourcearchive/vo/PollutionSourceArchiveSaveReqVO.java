package cn.iocoder.yudao.module.waterdetection.controller.admin.pollutionsourcearchive.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周边污染源档案管理新增/修改 Request VO")
@Data
public class PollutionSourceArchiveSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "污染源编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "污染源编号不能为空")
    private String pollutionNo;

    @Schema(description = "污染源类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "污染源类型不能为空")
    private String pollutionType;

    @Schema(description = "经度")
    private Double longitude;

    @Schema(description = "纬度")
    private Double latitude;

    @Schema(description = "污染程度")
    private String pollutionLevel;

    @Schema(description = "治理措施")
    private String treatmentMeasures;

    @Schema(description = "治理状态")
    private String treatmentStatus;

    @Schema(description = "排查时间")
    private LocalDateTime inspectionTime;

}