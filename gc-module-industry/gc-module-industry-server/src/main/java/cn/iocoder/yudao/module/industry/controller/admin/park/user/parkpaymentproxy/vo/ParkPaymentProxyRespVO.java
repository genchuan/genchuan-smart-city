package cn.iocoder.yudao.module.industry.controller.admin.park.user.parkpaymentproxy.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 代付规则 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkPaymentProxyRespVO {

    @Schema(description = "[主键ID] 代付规则唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "24747")
    @ExcelProperty("[主键ID] 代付规则唯一标识")
    private Long id;

    @Schema(description = "[规则名称] 代付规则名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("[规则名称] 代付规则名称")
    private String proxyName;

    @Schema(description = "[代付类型] 如：企业代付/政府代付/指定用户代付", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("[代付类型] 如：企业代付/政府代付/指定用户代付")
    private String proxyType;

    @Schema(description = "[付款方ID] 可为用户ID或商户ID", example = "11942")
    @ExcelProperty("[付款方ID] 可为用户ID或商户ID")
    private Long payerId;

    @Schema(description = "[收款方类型] 如：用户/商户", example = "2")
    @ExcelProperty("[收款方类型] 如：用户/商户")
    private String payeeType;

    @Schema(description = "[适用资源ID列表] JSON 格式，varchar 存储")
    @ExcelProperty("[适用资源ID列表] JSON 格式，varchar 存储")
    private String assetIds;

    @Schema(description = "[状态] 如：启用/禁用", example = "1")
    @ExcelProperty("[状态] 如：启用/禁用")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    @ExcelProperty("[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    @ExcelProperty("[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    @ExcelProperty("[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    @ExcelProperty("[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

    @Schema(description = "[备注] 代付规则相关备注说明", example = "随便")
    @ExcelProperty("[备注] 代付规则相关备注说明")
    private String remark;

}
