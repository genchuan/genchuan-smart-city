package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantlink.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 商户对接分页 Request VO")
@Data
public class MerchantLinkPageReqVO extends PageParam {

    @Schema(description = "商户ID，关联merchant_info.id", example = "31982")
    private Long merchantId;

    @Schema(description = "对接类型：数据对接/接口对接/商品同步/核销同步", example = "2")
    private String linkType;

    @Schema(description = "接口地址", example = "https://www.iocoder.cn")
    private String apiUrl;

    @Schema(description = "接口密钥")
    private String apiKey;

    @Schema(description = "对接状态：未对接/已对接", example = "1")
    private String status;

    @Schema(description = "对接生效时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] effectTime;

    @Schema(description = "最后同步时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastSyncTime;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}