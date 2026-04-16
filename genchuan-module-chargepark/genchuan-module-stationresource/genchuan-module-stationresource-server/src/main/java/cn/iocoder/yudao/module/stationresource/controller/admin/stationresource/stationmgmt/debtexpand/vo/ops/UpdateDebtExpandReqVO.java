package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 联合追缴拓场配置新增/修改 Request VO")
@Data
public class UpdateDebtExpandReqVO {

    @Schema(description = "[主键ID] 主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7485")
    private Long id;

    @Schema(description = "[合作场站] 关联场站信息表 station_info", requiredMode = Schema.RequiredMode.REQUIRED, example = "27111")
//    @NotNull(message = "[合作场站] 关联场站信息表 station_info不能为空")
    private Long stationId;

    @Schema(description = "[合作类型] 如：社会停车场拓场/联合追缴", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
//    @NotEmpty(message = "[合作类型] 如：社会停车场拓场/联合追缴不能为空")
    private String type;

    @Schema(description = "[追缴范围] 如：本区域/跨区域/全平台", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotEmpty(message = "[追缴范围] 如：本区域/跨区域/全平台不能为空")
    private String range;

    @Schema(description = "[拓场进度] 单位：%")
    private Integer progress;

    @Schema(description = "[状态] 如：未生效/已生效/已禁用", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
//    @NotEmpty(message = "[状态] 如：未生效/已生效/已禁用不能为空")
    private String status;

    @Schema(description = "[审核时间] 审核通过的时间")
    private LocalDateTime auditTime;

    @Schema(description = "[审核人] 关联芋道用户表 system_user", example = "8820")
    private Long auditUserId;

    @Schema(description = "[完成时间] 拓场完成时间")
    private LocalDateTime finishTime;

    @Schema(description = "[追缴完成率] 追缴完成比例")
    private BigDecimal recoveryRate;

    @Schema(description = "[备注] 补充说明", example = "你猜")
    private String remark;

    @Schema(description = "[备用字段1]")
    private String reserve1;

    @Schema(description = "[备用字段2]")
    private String reserve2;

}
