package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 押金方案新增/修改 Request VO")
@Data
public class DepositPlanSaveReqVO {

    @Schema(description = "[主键ID] 主键，自增", requiredMode = Schema.RequiredMode.REQUIRED, example = "10539")
    private Long id;

    @Schema(description = "[所属场站] 关联场站信息表 station_info", requiredMode = Schema.RequiredMode.REQUIRED, example = "652")
    @NotNull(message = "[所属场站] 关联场站信息表 station_info不能为空")
    private Long stationId;

    @Schema(description = "[押金金额] 单位：元", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[押金金额] 单位：元不能为空")
    private BigDecimal depositAmount;

    @Schema(description = "[适用场景] 如：预约停车/预约充电/临时停车", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[适用场景] 如：预约停车/预约充电/临时停车不能为空")
    private String scene;

    @Schema(description = "[状态] 如：待生效/已生效/已禁用", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[状态] 如：待生效/已生效/已禁用不能为空")
    private String status;

    @Schema(description = "[审核时间] 审核通过的时间")
    private LocalDateTime auditTime;

    @Schema(description = "[审核人] 关联芋道用户表 system_user", example = "31342")
    private Long auditUserId;

    @Schema(description = "[押金订单量] 产生押金的订单总数", example = "29840")
    private Integer depositOrderCount;

    @Schema(description = "[备注] 扩展说明", example = "你说的对")
    private String remark;

    @Schema(description = "[备用字段1] 预留扩展")
    private String reserve1;

    @Schema(description = "[备用字段2] 预留扩展")
    private String reserve2;

}
