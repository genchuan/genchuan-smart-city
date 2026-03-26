package cn.iocoder.yudao.module.waterdetection.controller.admin.invaliddata.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 不合格数据处理新增/修改 Request VO")
@Data
public class InvalidDataSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "数据ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "数据ID不能为空")
    private String dataId;

    @Schema(description = "仪器ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "仪器ID不能为空")
    private String instrumentId;

    @Schema(description = "监测值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "监测值不能为空")
    private Double monitorValue;

    @Schema(description = "采集时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "采集时间不能为空")
    private LocalDateTime collectionTime;

    @Schema(description = "数据状态(有效/无效)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "数据状态(有效/无效)不能为空")
    private String dataStatus;

    @Schema(description = "无效原因")
    private String invalidReason;

    @Schema(description = "剔除标记(0未剔除1已剔除)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "剔除标记(0未剔除1已剔除)不能为空")
    private Boolean isExcluded;

    @Schema(description = "处理人员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "处理人员ID不能为空")
    private String processorId;

}