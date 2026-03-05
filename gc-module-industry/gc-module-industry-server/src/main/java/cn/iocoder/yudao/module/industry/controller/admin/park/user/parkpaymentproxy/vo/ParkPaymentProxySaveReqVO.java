package cn.iocoder.yudao.module.industry.controller.admin.park.user.parkpaymentproxy.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 代付规则新增/修改 Request VO")
@Data
public class ParkPaymentProxySaveReqVO {

    @Schema(description = "[主键ID] 代付规则唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "24747")
    private Long id;

    @Schema(description = "[规则名称] 代付规则名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "[规则名称] 代付规则名称不能为空")
    private String proxyName;

    @Schema(description = "[代付类型] 如：企业代付/政府代付/指定用户代付", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "[代付类型] 如：企业代付/政府代付/指定用户代付不能为空")
    private String proxyType;

    @Schema(description = "[付款方ID] 可为用户ID或商户ID", example = "11942")
    private Long payerId;

    @Schema(description = "[收款方类型] 如：用户/商户", example = "2")
    private String payeeType;

    @Schema(description = "[适用资源ID列表] JSON 格式，varchar 存储")
    private String assetIds;

    @Schema(description = "[状态] 如：启用/禁用", example = "1")
    private String status;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

    @Schema(description = "[备注] 代付规则相关备注说明", example = "随便")
    private String remark;

}
