package cn.iocoder.yudao.module.vehiclecharging.controller.admin.ratesetting.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "汽车充电 - 费率设置新增/修改 Request VO")
@Data
public class RateSettingSaveReqVO {

    @Schema(description = "[主键ID] 主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19547")
    private Long id;

    @Schema(description = "[方案编号] 方案编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[方案编号] 方案编号不能为空")
    private String rateCode;

    @Schema(description = "[方案名称] 方案名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "[方案名称] 方案名称不能为空")
    private String rateName;

    @Schema(description = "[适用场景] 适用场景", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[适用场景] 适用场景不能为空")
    private String applyScene;

    @Schema(description = "[费率规则] 费率规则", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[费率规则] 费率规则不能为空")
    private String rateRule;

    @Schema(description = "[生效时间] 生效时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[生效时间] 生效时间不能为空")
    private LocalDateTime effectTime;

    @Schema(description = "[失效时间] 失效时间")
    private LocalDateTime expireTime;

    @Schema(description = "[适用场站] 适用场站")
    private String applyStation;

    @Schema(description = "[适用集团] 适用集团")
    private String applyGroup;

    @Schema(description = "[费率状态]如:未生效/已生效/已失效", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "[费率状态]如:未生效/已生效/已失效不能为空")
    private String rateStatus;

    @Schema(description = "[备注] 备注", example = "你说的对")
    private String remark;

    @Schema(description = "[备用字段1] 备用字段1")
    private String reserve1;

    @Schema(description = "[备用字段2] 备用字段2")
    private String reserve2;

}
