package cn.iocoder.yudao.module.park.controller.admin.park.user.merchant.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 商户分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MerchantPageReqVO extends PageParam {

    @Schema(description = "[商户名称]", example = "李四")
    private String merchantName;

    @Schema(description = "[商户编码] 唯一商户编码")
    private String merchantCode;

    @Schema(description = "[联系人]")
    private String contactPerson;

    @Schema(description = "[联系电话]")
    private String contactPhone;

    @Schema(description = "[商户地址]")
    private String address;

    @Schema(description = "[经营范围]")
    private String businessScope;

    @Schema(description = "[区域编码] 关联park_area.area_code")
    private String regionCode;

    @Schema(description = "[统一社会信用代码]")
    private String creditCode;

    @Schema(description = "[状态] 如:正常/禁用/待审核/已驳回", example = "2")
    private String status;

    @Schema(description = "[结算账户]", example = "2425")
    private String settlementAccount;

    @Schema(description = "[入驻时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[备注]", example = "你猜")
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
