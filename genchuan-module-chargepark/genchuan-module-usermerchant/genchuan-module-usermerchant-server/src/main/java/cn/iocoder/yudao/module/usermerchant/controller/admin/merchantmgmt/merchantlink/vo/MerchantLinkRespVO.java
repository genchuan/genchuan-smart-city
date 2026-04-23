package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantlink.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 商户对接 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MerchantLinkRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4526")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "商户ID，关联merchant_info.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "31982")
    @ExcelProperty("商户ID，关联merchant_info.id")
    private Long merchantId;

    @Schema(description = "对接类型：数据对接/接口对接/商品同步/核销同步", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("对接类型：数据对接/接口对接/商品同步/核销同步")
    private String linkType;

    @Schema(description = "接口地址", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.iocoder.cn")
    @ExcelProperty("接口地址")
    private String apiUrl;

    @Schema(description = "接口密钥")
    @ExcelProperty("接口密钥")
    private String apiKey;

    @Schema(description = "对接状态：未对接/已对接", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("对接状态：未对接/已对接")
    private String status;

    @Schema(description = "对接生效时间")
    @ExcelProperty("对接生效时间")
    private LocalDateTime effectTime;

    @Schema(description = "最后同步时间")
    @ExcelProperty("最后同步时间")
    private LocalDateTime lastSyncTime;

    @Schema(description = "备注", example = "随便")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}