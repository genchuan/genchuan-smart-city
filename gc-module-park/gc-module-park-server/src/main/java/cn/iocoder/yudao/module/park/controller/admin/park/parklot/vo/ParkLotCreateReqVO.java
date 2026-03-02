package cn.iocoder.yudao.module.park.controller.admin.park.parklot.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.checkerframework.checker.units.qual.Length;

import java.util.Date;


@Schema(description = "管理后台 -创建停车场ReqVO接口")
@Data
public class ParkLotCreateReqVO extends PageParam {

    /**
     * 车场UUID（全局唯一）
     */
    @NotBlank(message = "车场ID不能为空")
    private String lotId;

    /**
     * 关联资产扩展ID
     */
    @NotNull(message = "关联资产扩展ID不能为空")
    @Positive(message = "关联资产扩展ID必须为正数")
    private Long assetExtendId;

    /**
     * 12位地区码
     */
    @NotBlank(message = "12位地区码不能为空")
    @Pattern(regexp = "^\\d{12}$", message = "地区码必须为12位数字")
    private String regionFullCode;

    /**
     * 总车位数
     */
    @NotNull(message = "总车位数不能为空")
    @Min(value = 0, message = "总车位数不能小于0")
    private Integer totalSpace;

    /**
     * 可用车位数
     */
    @NotNull(message = "可用车位数不能为空")
    @Min(value = 0, message = "可用车位数不能小于0")
    private Integer availableSpace;

    /**
     * 车场类型（地面/地下/立体/路侧）
     */
    @NotBlank(message = "车场类型不能为空")
    @Pattern(regexp = "^(地面|地下|立体|路侧)$", message = "车场类型只能是：地面、地下、立体、路侧")
    private String parkType;

    /**
     * 开放时间（HH:mm:ss）
     */
    @NotBlank(message = "开放时间不能为空")
    @Pattern(regexp = "^\\d{2}:\\d{2}:\\d{2}$", message = "开放时间格式必须为：HH:mm:ss")
    private String openTime;

    /**
     * 关闭时间（HH:mm:ss，可为空表示24小时营业）
     */
    @Pattern(regexp = "^\\d{2}:\\d{2}:\\d{2}$", message = "关闭时间格式必须为：HH:mm:ss")
    private String closeTime;

    /**
     * 运营商户ID
     */
    @NotBlank(message = "运营商户ID不能为空")
    private String managementMerchantId;

    /**
     * 费率策略ID
     */
    private String feeStrategyId;

    /**
     * 业务创建时间
     */
    private Date lotCreateTime;

    /**
     * 业务更新时间
     */
    private Date lotUpdateTime;

    /**
     * 车场名称/备注
     */
    @NotBlank(message = "车场名称不能为空")
    private String lotRemark;

    private String creator;

    /**
     * 更新者
     */
    private String updater;
    /**
     * 扩展字段1
     */
    private String extCommon1;

    /**
     * 扩展字段2
     */
    private String extCommon2;

    /**
     * 扩展字段3
     */
    private String extCommon3;

    /**
     * 扩展字段4
     */
    private String extCommon4;

    /**
     * 租户ID（默认1）
     */
    @NotNull(message = "租户ID不能为空")
    @Positive(message = "租户ID必须为正数")
    private Long tenantId = 1L;
}
