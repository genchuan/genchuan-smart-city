package cn.iocoder.yudao.module.park.controller.admin.park.user.paymentproxy.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 代付规则新增/修改 Request VO")
@Data
public class PaymentProxySaveReqVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "25005")
    private Long id;

    @Schema(description = "[规则名称]", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "[规则名称]不能为空")
    private String proxyName;

    @Schema(description = "[代付类型] 如:企业代付/政府代付/其他", example = "2")
    private String proxyType;

    @Schema(description = "[付款方ID] 关联park_user.id/park_enterprise_information.enterprise_id", example = "28925")
    private Long payerId;

    @Schema(description = "[收款方类型] 如:商户/平台", example = "1")
    private String payeeType;

    @Schema(description = "[收款方ID] 关联park_merchant.merchant_id", example = "26776")
    private Long payeeId;

    @Schema(description = "[适用资源ID列表] JSON格式varchar，关联tb_asset_extend.asset_extend_id")
    private String assetIds;

    @Schema(description = "[状态] 如:启用/禁用", example = "2")
    private String status;

    @Schema(description = "[备注]", example = "随便")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}
