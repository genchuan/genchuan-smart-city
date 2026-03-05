package cn.iocoder.yudao.module.industry.controller.admin.park.asset.resourceaccount.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 资源台账 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkResourceAccountRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7419")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "台账ID（UUID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "8895")
    @ExcelProperty("台账ID（UUID）")
    private String accountId;

    @Schema(description = "资产类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("资产类型")
    private String assetType;

    @Schema(description = "关联ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31051")
    @ExcelProperty("关联ID")
    private String assetExtendId;

    @Schema(description = "台账生成日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("台账生成日期")
    private LocalDate accountDate;

    @Schema(description = "更新日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新日期")
    private LocalDate accountUpdateDate;

    @Schema(description = "台账数据", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("台账数据")
    private String dataContent;

    @Schema(description = "生成人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("生成人ID")
    private Long generateBy;

    @Schema(description = "状态：有效/过期", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("状态：有效/过期")
    private String accountStatus;

    @Schema(description = "业务创建时间")
    @ExcelProperty("业务创建时间")
    private LocalDateTime accountCreateTime;

    @Schema(description = "业务更新时间")
    @ExcelProperty("业务更新时间")
    private LocalDateTime accountUpdateTime;

    @Schema(description = "业务备注", example = "你猜")
    @ExcelProperty("业务备注")
    private String accountRemark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}