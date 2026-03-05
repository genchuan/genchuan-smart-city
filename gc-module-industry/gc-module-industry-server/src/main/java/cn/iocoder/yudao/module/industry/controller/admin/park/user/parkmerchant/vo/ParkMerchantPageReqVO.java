package cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmerchant.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 商户分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkMerchantPageReqVO extends PageParam {

    @Schema(description = "商户名称[唯一商户名称，如组织名称、公司名称等]", example = "王五")
    private String merchantName;

    @Schema(description = "商户编码[唯一商户编码]")
    private String merchantCode;

    @Schema(description = "联系人姓名[商户对外对内的联系人姓名，可重名]")
    private String contactPerson;

    @Schema(description = "联系电话[商户联系电话]")
    private String contactPhone;

    @Schema(description = "商户地址[商户经营或办公地址]")
    private String address;

    @Schema(description = "经营范围[商户经营范围，如停车场运营/车辆进出管理]")
    private String businessScope;

    @Schema(description = "状态[正常/停业/注销]", example = "1")
    private String status;

    @Schema(description = "分账比例[默认分账比例，百分比数值]")
    private BigDecimal settlementRatio;

    @Schema(description = "创建时间[记录创建时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "创建人[数据创建人]")
    private String createBy;

    @Schema(description = "更新人[数据最后更新人]")
    private String updateBy;

    @Schema(description = "备注[商户相关备注说明]", example = "你说的对")
    private String remark;

    @Schema(description = "通用扩展字段1[预留扩展字段]")
    private String extCommon1;

    @Schema(description = "通用扩展字段2[预留扩展字段]")
    private String extCommon2;

    @Schema(description = "通用扩展字段3[预留扩展字段]")
    private String extCommon3;

    @Schema(description = "通用扩展字段4[预留扩展字段]")
    private String extCommon4;

}
