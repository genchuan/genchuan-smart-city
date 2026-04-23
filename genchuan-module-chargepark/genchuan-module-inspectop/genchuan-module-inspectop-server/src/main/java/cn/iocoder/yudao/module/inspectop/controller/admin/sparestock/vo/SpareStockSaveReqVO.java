package cn.iocoder.yudao.module.inspectop.controller.admin.sparestock.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "巡查巡检 - 备件仓储新增/修改 Request VO")
@Data
public class SpareStockSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "备件ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "备件ID不能为空")
    private Long spareId;

    @Schema(description = "备件名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "备件名称不能为空")
    private String spareName;

    @Schema(description = "当前库存", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "当前库存不能为空")
    private Integer currentStock;

    @Schema(description = "库存状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "库存状态不能为空")
    private String status;

    @Schema(description = "入库时间")
    private LocalDateTime inTime;

    @Schema(description = "出库时间")
    private LocalDateTime outTime;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}