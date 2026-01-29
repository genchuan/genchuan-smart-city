package cn.iocoder.yudao.module.park.controller.admin.park.user.address.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 地址分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AddressPageReqVO extends PageParam {

    @Schema(description = "[用户ID] 关联park_user.id，可为NULL", example = "27940")
    private Long userId;

    @Schema(description = "[企业ID] 关联park_enterprise_information.enterprise_id，可为NULL", example = "25420")
    private Long enterpriseId;

    @Schema(description = "[收货人姓名]", example = "赵六")
    private String receiverName;

    @Schema(description = "[联系电话]")
    private String phone;

    @Schema(description = "[省份]")
    private String province;

    @Schema(description = "[城市]")
    private String city;

    @Schema(description = "[区县]")
    private String district;

    @Schema(description = "[详细地址]")
    private String detailAddress;

    @Schema(description = "[是否默认地址] 如:0-否/1-是")
    private Boolean isDefault;

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
