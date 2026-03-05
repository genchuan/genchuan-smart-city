package cn.iocoder.yudao.module.industry.controller.admin.park.asset.lot.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalTime;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 车场信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkLotRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5709")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "关联ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10180")
    @ExcelProperty("关联ID")
    private String assetExtendId;

    @Schema(description = "总车位数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("总车位数")
    private Integer totalSpace;

    @Schema(description = "当前可用车位数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("当前可用车位数")
    private Integer availableSpace;

    @Schema(description = "车场类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("车场类型")
    private String parkType;

    @Schema(description = "开放时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("开放时间")
    private LocalTime openTime;

    @Schema(description = "关闭时间")
    @ExcelProperty("关闭时间")
    private LocalTime closeTime;

    @Schema(description = "运营商户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21469")
    @ExcelProperty("运营商户ID")
    private String managementMerchantId;

    @Schema(description = "默认费率策略ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14321")
    @ExcelProperty("默认费率策略ID")
    private String feeStrategyId;

    @Schema(description = "业务创建时间")
    @ExcelProperty("业务创建时间")
    private LocalDateTime lotCreateTime;

    @Schema(description = "业务更新时间")
    @ExcelProperty("业务更新时间")
    private LocalDateTime lotUpdateTime;

    @Schema(description = "业务备注", example = "随便")
    @ExcelProperty("业务备注")
    private String lotRemark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}