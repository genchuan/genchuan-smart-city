package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.offtimerule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 错时规则新增/修改 Request VO")
@Data
public class OfftimeRuleSaveReqVO {

    @Schema(description = "[主键ID] 主键，自增", requiredMode = Schema.RequiredMode.REQUIRED, example = "18794")
    private Long id;

    @Schema(description = "[所属场站] 关联场站信息表 station_info", requiredMode = Schema.RequiredMode.REQUIRED, example = "14476")
    @NotNull(message = "[所属场站] 关联场站信息表 station_info不能为空")
    private Long stationId;

    @Schema(description = "[空闲时段] 错时优惠时段描述", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[空闲时段] 错时优惠时段描述不能为空")
    private String offTime;

    @Schema(description = "[错时费率] 单位：元/小时或元/次", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[错时费率] 单位：元/小时或元/次不能为空")
    private BigDecimal offFee;

    @Schema(description = "[状态] 如：待生效/已生效/已禁用", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[状态] 如：待生效/已生效/已禁用不能为空")
    private String status;

    @Schema(description = "[审核时间] 审核通过的时间")
    private LocalDateTime auditTime;

    @Schema(description = "[审核人] 关联芋道用户表 system_user", example = "30078")
    private Long auditUserId;

    @Schema(description = "[错时订单量] 使用该规则的订单数量", example = "17986")
    private Integer offOrderCount;

    @Schema(description = "[备注] 扩展说明", example = "你猜")
    private String remark;

    @Schema(description = "[备用字段1] 预留扩展")
    private String reserve1;

    @Schema(description = "[备用字段2] 预留扩展")
    private String reserve2;

}
