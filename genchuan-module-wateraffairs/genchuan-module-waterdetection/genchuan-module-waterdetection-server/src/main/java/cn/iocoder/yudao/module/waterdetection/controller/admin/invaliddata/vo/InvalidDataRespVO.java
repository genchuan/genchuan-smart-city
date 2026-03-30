package cn.iocoder.yudao.module.waterdetection.controller.admin.invaliddata.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 不合格数据处理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InvalidDataRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "数据ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("数据ID")
    private String dataId;

    @Schema(description = "仪器ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("仪器ID")
    private String instrumentId;

    @Schema(description = "监测值", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("监测值")
    private Double monitorValue;

    @Schema(description = "采集时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("采集时间")
    private LocalDateTime collectionTime;

    @Schema(description = "数据状态(有效/无效)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("数据状态(有效/无效)")
    private String dataStatus;

    @Schema(description = "无效原因")
    @ExcelProperty("无效原因")
    private String invalidReason;

    @Schema(description = "剔除标记(0未剔除1已剔除)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("剔除标记(0未剔除1已剔除)")
    private Boolean isExcluded;

    @Schema(description = "处理人员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("处理人员ID")
    private String processorId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}