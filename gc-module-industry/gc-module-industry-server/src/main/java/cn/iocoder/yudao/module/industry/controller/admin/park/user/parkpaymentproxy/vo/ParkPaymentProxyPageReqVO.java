package cn.iocoder.yudao.module.industry.controller.admin.park.user.parkpaymentproxy.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 代付规则分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkPaymentProxyPageReqVO extends PageParam {

    @Schema(description = "[规则名称] 代付规则名称", example = "芋艿")
    private String proxyName;

    @Schema(description = "[代付类型] 如：企业代付/政府代付/指定用户代付", example = "1")
    private String proxyType;

    @Schema(description = "[付款方ID] 可为用户ID或商户ID", example = "11942")
    private Long payerId;

    @Schema(description = "[收款方类型] 如：用户/商户", example = "2")
    private String payeeType;

    @Schema(description = "[适用资源ID列表] JSON 格式，varchar 存储")
    private String assetIds;

    @Schema(description = "[状态] 如：启用/禁用", example = "1")
    private String status;

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

    @Schema(description = "[备注] 代付规则相关备注说明", example = "随便")
    private String remark;

}
