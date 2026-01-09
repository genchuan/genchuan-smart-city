package cn.iocoder.yudao.module.industry.controller.admin.park.asset.lot.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalTime;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 车场信息新增/修改 Request VO")
@Data
public class ParkLotSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5709")
    private Long id;

    @Schema(description = "关联ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10180")
    @NotEmpty(message = "关联ID不能为空")
    private String assetExtendId;

    @Schema(description = "总车位数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "总车位数不能为空")
    private Integer totalSpace;

    @Schema(description = "当前可用车位数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "当前可用车位数不能为空")
    private Integer availableSpace;

    @Schema(description = "车场类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "车场类型不能为空")
    private String parkType;

    @Schema(description = "开放时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "开放时间不能为空")
    private LocalTime openTime;

    @Schema(description = "关闭时间")
    private LocalTime closeTime;

    @Schema(description = "运营商户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21469")
    @NotEmpty(message = "运营商户ID不能为空")
    private String managementMerchantId;

    @Schema(description = "默认费率策略ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14321")
    @NotEmpty(message = "默认费率策略ID不能为空")
    private String feeStrategyId;

    @Schema(description = "业务创建时间")
    private LocalDateTime lotCreateTime;

    @Schema(description = "业务更新时间")
    private LocalDateTime lotUpdateTime;

    @Schema(description = "业务备注", example = "随便")
    private String lotRemark;

}