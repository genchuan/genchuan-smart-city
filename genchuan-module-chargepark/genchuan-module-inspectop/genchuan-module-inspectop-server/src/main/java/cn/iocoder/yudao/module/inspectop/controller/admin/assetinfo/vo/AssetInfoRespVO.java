package cn.iocoder.yudao.module.inspectop.controller.admin.assetinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 资产信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssetInfoRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "资产名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("资产名称")
    private String name;

    @Schema(description = "资产类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("资产类型")
    private String type;

    @Schema(description = "采购时间")
    @ExcelProperty("采购时间")
    private LocalDateTime purchaseTime;

    @Schema(description = "资产状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("资产状态")
    private String status;

    @Schema(description = "所属场站ID")
    @ExcelProperty("所属场站ID")
    private Long stationId;

    @Schema(description = "场站名称")
    @ExcelProperty("场站名称")
    private String stationName;

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

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}