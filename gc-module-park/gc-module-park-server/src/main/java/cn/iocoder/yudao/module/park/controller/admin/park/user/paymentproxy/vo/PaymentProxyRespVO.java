package cn.iocoder.yudao.module.park.controller.admin.park.user.paymentproxy.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 代付规则 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PaymentProxyRespVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "25005")
    @ExcelProperty("[主键ID]")
    private Long id;

    @Schema(description = "[规则名称]", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("[规则名称]")
    private String proxyName;

    @Schema(description = "[代付类型] 如:企业代付/政府代付/其他", example = "2")
    @ExcelProperty("[代付类型] 如:企业代付/政府代付/其他")
    private String proxyType;

    @Schema(description = "[付款方ID] 关联park_user.id/park_enterprise_information.enterprise_id", example = "28925")
    @ExcelProperty("[付款方ID] 关联park_user.id/park_enterprise_information.enterprise_id")
    private Long payerId;

    @Schema(description = "[收款方类型] 如:商户/平台", example = "1")
    @ExcelProperty("[收款方类型] 如:商户/平台")
    private String payeeType;

    @Schema(description = "[收款方ID] 关联park_merchant.merchant_id", example = "26776")
    @ExcelProperty("[收款方ID] 关联park_merchant.merchant_id")
    private Long payeeId;

    @Schema(description = "[适用资源ID列表] JSON格式varchar，关联tb_asset_extend.asset_extend_id")
    @ExcelProperty("[适用资源ID列表] JSON格式varchar，关联tb_asset_extend.asset_extend_id")
    private String assetIds;

    @Schema(description = "[状态] 如:启用/禁用", example = "2")
    @ExcelProperty("[状态] 如:启用/禁用")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注]", example = "随便")
    @ExcelProperty("[备注]")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    @ExcelProperty("[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    @ExcelProperty("[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    @ExcelProperty("[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    @ExcelProperty("[通用扩展字段4]")
    private String extCommon4;

}
