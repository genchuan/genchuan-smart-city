package cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkeinvoice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 电子发票新增/修改 Request VO")
@Data
public class ParkEInvoiceSaveReqVO {

    @Schema(description = "[主键ID] 电子发票记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "828")
    private Long id;

    @Schema(description = "[发票号码] 电子发票唯一号码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[发票号码] 电子发票唯一号码不能为空")
    private String invoiceNo;

    @Schema(description = "[订单ID] 关联订单唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "16430")
    @NotNull(message = "[订单ID] 关联订单唯一标识不能为空")
    private Long orderId;

    @Schema(description = "[订单类型] 订单业务类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "[订单类型] 订单业务类型不能为空")
    private String orderType;

    @Schema(description = "[用户ID] 用户唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "24450")
    @NotNull(message = "[用户ID] 用户唯一标识不能为空")
    private Long userId;

    @Schema(description = "[发票类型] 如：普通发票/增值税电子普通发票", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "[发票类型] 如：普通发票/增值税电子普通发票不能为空")
    private String invoiceType;

    @Schema(description = "[发票抬头] 发票抬头信息", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[发票抬头] 发票抬头信息不能为空")
    private String title;

    @Schema(description = "[纳税人识别号] 纳税人识别号", example = "16625")
    private Long taxpayerId;

    @Schema(description = "[发票金额] 发票开具金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[发票金额] 发票开具金额不能为空")
    private BigDecimal amount;

    @Schema(description = "[发票内容] 发票内容说明")
    private String invoiceContent;

    @Schema(description = "[发票状态] 如：待开具/已开具/已红冲/已作废", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[发票状态] 如：待开具/已开具/已红冲/已作废不能为空")
    private String status;

    @Schema(description = "[开具时间] 发票实际开具时间")
    private LocalDateTime issueTime;

    @Schema(description = "[发票PDF地址] 发票PDF存储地址", example = "https://www.iocoder.cn")
    private String pdfUrl;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

    @Schema(description = "[备注] 电子发票相关备注说明", example = "随便")
    private String remark;

}
