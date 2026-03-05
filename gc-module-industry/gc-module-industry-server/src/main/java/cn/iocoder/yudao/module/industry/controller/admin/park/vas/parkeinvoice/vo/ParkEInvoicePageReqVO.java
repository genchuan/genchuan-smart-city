package cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkeinvoice.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 电子发票分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkEInvoicePageReqVO extends PageParam {

    @Schema(description = "[发票号码] 电子发票唯一号码")
    private String invoiceNo;

    @Schema(description = "[订单ID] 关联订单唯一标识", example = "16430")
    private Long orderId;

    @Schema(description = "[订单类型] 订单业务类型", example = "1")
    private String orderType;

    @Schema(description = "[用户ID] 用户唯一标识", example = "24450")
    private Long userId;

    @Schema(description = "[发票类型] 如：普通发票/增值税电子普通发票", example = "1")
    private String invoiceType;

    @Schema(description = "[发票抬头] 发票抬头信息")
    private String title;

    @Schema(description = "[纳税人识别号] 纳税人识别号", example = "16625")
    private Long taxpayerId;

    @Schema(description = "[发票金额] 发票开具金额")
    private BigDecimal amount;

    @Schema(description = "[发票内容] 发票内容说明")
    private String invoiceContent;

    @Schema(description = "[发票状态] 如：待开具/已开具/已红冲/已作废", example = "2")
    private String status;

    @Schema(description = "[开具时间] 发票实际开具时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] issueTime;

    @Schema(description = "[发票PDF地址] 发票PDF存储地址", example = "https://www.iocoder.cn")
    private String pdfUrl;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

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
