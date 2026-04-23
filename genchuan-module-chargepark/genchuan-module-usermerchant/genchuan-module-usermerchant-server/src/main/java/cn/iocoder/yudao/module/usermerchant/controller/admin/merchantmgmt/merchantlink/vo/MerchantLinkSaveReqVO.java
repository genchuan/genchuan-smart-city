package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantlink.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 商户对接新增/修改 Request VO")
@Data
public class MerchantLinkSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4526")
    private Long id;

    @Schema(description = "商户ID，关联merchant_info.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "31982")
    @NotNull(message = "商户ID，关联merchant_info.id不能为空")
    private Long merchantId;

    @Schema(description = "对接类型：数据对接/接口对接/商品同步/核销同步", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "对接类型：数据对接/接口对接/商品同步/核销同步不能为空")
    private String linkType;

    @Schema(description = "接口地址", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.iocoder.cn")
    @NotEmpty(message = "接口地址不能为空")
    private String apiUrl;

    @Schema(description = "接口密钥")
    private String apiKey;

    @Schema(description = "对接状态：未对接/已对接", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "对接状态：未对接/已对接不能为空")
    private String status;

    @Schema(description = "对接生效时间")
    private LocalDateTime effectTime;

    @Schema(description = "最后同步时间")
    private LocalDateTime lastSyncTime;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}