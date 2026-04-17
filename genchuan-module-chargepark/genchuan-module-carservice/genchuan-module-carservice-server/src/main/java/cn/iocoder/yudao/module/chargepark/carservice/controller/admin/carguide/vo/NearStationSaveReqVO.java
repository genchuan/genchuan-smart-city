package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周边场站新增/修改 Request VO")
@Data
public class NearStationSaveReqVO {

    @Schema(description = "主键 ID", example = "1024")
    private Long id;

    @Schema(description = "用户 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotNull(message = "用户 ID 不能为空")
    private Long userId;

    @Schema(description = "查询位置", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "查询位置不能为空")
    private String queryLocation;

    @Schema(description = "查询时间（创建时由后端默认为当前时间）")
    private LocalDateTime queryTime;

    @Schema(description = "周边场站数")
    private Integer stationCount;

    @Schema(description = "空位场站数")
    private Integer emptyStationCount;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}
