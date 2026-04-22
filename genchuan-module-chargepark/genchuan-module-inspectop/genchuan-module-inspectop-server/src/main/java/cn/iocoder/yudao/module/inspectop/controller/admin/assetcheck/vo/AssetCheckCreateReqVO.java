package cn.iocoder.yudao.module.inspectop.controller.admin.assetcheck.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资产盘点创建 Request VO")
@Data
public class AssetCheckCreateReqVO {

    @Schema(description = "盘点类型 (定期 / 临时)，关联芋道字典表：asset_check_type", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "盘点类型不能为空")
    private String type;

    @Schema(description = "盘点时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime checkTime;
}