package cn.iocoder.yudao.module.inspectop.controller.admin.assetcheck.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "巡查巡检 - 资产盘点新增/修改 Request VO")
@Data
public class AssetCheckSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "盘点类型", requiredMode = Schema.RequiredMode.REQUIRED)
    private String type;

    @Schema(description = "盘点时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime checkTime;

    @Schema(description = "盘点进度")
    private Integer progress;

    @Schema(description = "盘点状态", requiredMode = Schema.RequiredMode.REQUIRED)
    private String status;

    @Schema(description = "确认人ID")
    private Long confirmUserId;

    @Schema(description = "确认时间")
    private LocalDateTime confirmTime;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}