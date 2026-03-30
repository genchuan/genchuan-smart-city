package cn.iocoder.yudao.module.park.controller.admin.park.user.supplier.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 供应商分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SupplierPageReqVO extends PageParam {

    @Schema(description = "[供应商名称]", example = "芋艿")
    private String supplierName;

    @Schema(description = "[联系人]")
    private String contactPerson;

    @Schema(description = "[联系电话]")
    private String contactPhone;

    @Schema(description = "[地址]")
    private String address;

    @Schema(description = "[经营范围]")
    private String businessScope;

    @Schema(description = "[资质证明] JSON格式varchar")
    private String qualification;

    @Schema(description = "[状态] 如:合作中/暂停合作/已终止", example = "1")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

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
