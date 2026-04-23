package cn.iocoder.yudao.module.inspectop.controller.admin.assetcheck.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "巡查巡检 - 资产盘点 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssetCheckRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "盘点类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("盘点类型")
    private String type;

    @Schema(description = "盘点时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("盘点时间")
    private LocalDateTime checkTime;

    @Schema(description = "盘点进度")
    @ExcelProperty("盘点进度")
    private Integer progress;

    @Schema(description = "盘点状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("盘点状态")
    private String status;

    @Schema(description = "确认人ID")
    @ExcelProperty("确认人ID")
    private Long confirmUserId;

    @Schema(description = "确认时间")
    @ExcelProperty("确认时间")
    private LocalDateTime confirmTime;

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