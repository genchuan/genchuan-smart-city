package cn.iocoder.yudao.module.park.controller.admin.park.marketing.smoothstopcard.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 畅停卡新增/修改 Request VO")
@Data
public class SmoothStopCardSaveReqVO {

    @Schema(description = "[主键ID] 畅停卡唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "24819")
    private Long id;

    @Schema(description = "[卡名称]", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "[卡名称]不能为空")
    private String cardName;

    @Schema(description = "[卡种类型]如:日卡/周卡/月卡/季卡/年卡", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "[卡种类型]如:日卡/周卡/月卡/季卡/年卡不能为空")
    private String cardType;

    @Schema(description = "[有效天数]", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[有效天数]不能为空")
    private Integer validDays;

    @Schema(description = "[生效时间]", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[生效时间]不能为空")
    private LocalDateTime effectiveTime;

    @Schema(description = "[失效时间]", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[失效时间]不能为空")
    private LocalDateTime expireTime;

    @Schema(description = "[持有者ID] 关联park_user.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "27719")
    @NotNull(message = "[持有者ID] 关联park_user.id不能为空")
    private Long holderId;

    @Schema(description = "[可绑定车牌数]", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[可绑定车牌数]不能为空")
    private Integer bindCarLimit;

    @Schema(description = "[已绑定车牌号] 逗号分隔的字符串")
    private String boundCarNumbers;

    @Schema(description = "[适用车场ID列表] 逗号分隔的字符串，关联park_lot.id")
    private String applyLotIds;

    @Schema(description = "[卡种描述]", example = "你说的对")
    private String description;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

    @Schema(description = "[备注]", example = "你猜")
    private String remark;

}
