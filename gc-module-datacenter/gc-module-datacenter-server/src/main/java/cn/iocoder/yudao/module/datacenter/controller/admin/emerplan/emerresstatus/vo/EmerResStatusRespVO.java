package cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerresstatus.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 资源状态统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EmerResStatusRespVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26787")
    @ExcelProperty("自增主键ID")
    private Long id;

    @Schema(description = "统计ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26864")
    @ExcelProperty("统计ID")
    private String statId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期")
    private String statCycle;

    @Schema(description = "统计时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计时间")
    private LocalDateTime statTime;

    @Schema(description = "资源类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("资源类型")
    private String resType;

    @Schema(description = "行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划代码")
    private String regionCode;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("行政区划名称")
    private String regionName;

    @Schema(description = "总数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("总数量")
    private Integer totalQuantity;

    @Schema(description = "可用数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("可用数量")
    private Integer availableQuantity;

    @Schema(description = "异常数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("异常数量")
    private Integer abnQuantity;

}