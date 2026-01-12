package cn.iocoder.yudao.module.industry.controller.admin.park.asset.resourceaccount.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资源台账新增/修改 Request VO")
@Data
public class ParkResourceAccountSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7419")
    private Long id;

    @Schema(description = "台账ID（UUID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "8895")
    @NotEmpty(message = "台账ID（UUID）不能为空")
    private String accountId;

    @Schema(description = "资产类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "资产类型不能为空")
    private String assetType;

    @Schema(description = "关联ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31051")
    @NotEmpty(message = "关联ID不能为空")
    private String assetExtendId;

    @Schema(description = "台账生成日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "台账生成日期不能为空")
    private LocalDate accountDate;

    @Schema(description = "更新日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "更新日期不能为空")
    private LocalDate accountUpdateDate;

    @Schema(description = "台账数据", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "台账数据不能为空")
    private String dataContent;

    @Schema(description = "生成人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "生成人ID不能为空")
    private Long generateBy;

    @Schema(description = "状态：有效/过期", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "状态：有效/过期不能为空")
    private String accountStatus;

    @Schema(description = "业务创建时间")
    private LocalDateTime accountCreateTime;

    @Schema(description = "业务更新时间")
    private LocalDateTime accountUpdateTime;

    @Schema(description = "业务备注", example = "你猜")
    private String accountRemark;

}