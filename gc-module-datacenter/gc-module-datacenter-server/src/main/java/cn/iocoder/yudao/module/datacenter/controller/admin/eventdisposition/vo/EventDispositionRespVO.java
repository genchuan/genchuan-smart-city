package cn.iocoder.yudao.module.datacenter.controller.admin.eventdisposition.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 监测事件关联处置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EventDispositionRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "事件类型ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事件类型ID")
    private String eventTypeId;

    @Schema(description = "事件类型名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事件类型名称")
    private String eventTypeName;

    @Schema(description = "行政区划编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划编码")
    private String divisionCode;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划名称")
    private String divisionName;

    @Schema(description = "流程模型ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("流程模型ID")
    private String processModelId;

    @Schema(description = "流程模型名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("流程模型名称")
    private String processModelName;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}