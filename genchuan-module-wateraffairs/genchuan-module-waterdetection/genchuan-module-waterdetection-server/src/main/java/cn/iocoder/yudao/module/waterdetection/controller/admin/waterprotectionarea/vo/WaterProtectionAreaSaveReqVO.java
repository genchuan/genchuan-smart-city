package cn.iocoder.yudao.module.waterdetection.controller.admin.waterprotectionarea.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 水源保护区管理新增/修改 Request VO")
@Data
public class WaterProtectionAreaSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED, example = "31644")
    private Long id;

    @Schema(description = "保护区级别", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "保护区级别不能为空")
    private String protectionLevel;

    @Schema(description = "边界经纬度范围")
    private String boundaryRange;

    @Schema(description = "标识牌编号")
    private String signboardNo;

    @Schema(description = "标识牌位置")
    private String signboardLocation;

    @Schema(description = "安装时间")
    private LocalDateTime installTime;

    @Schema(description = "维护记录")
    private String maintenanceRecord;

    @Schema(description = "污染源治理状态", example = "2")
    private String pollutionStatus;

}