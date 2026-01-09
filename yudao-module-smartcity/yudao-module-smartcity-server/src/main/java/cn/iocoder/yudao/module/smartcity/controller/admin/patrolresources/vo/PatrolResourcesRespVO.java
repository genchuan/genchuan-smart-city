package cn.iocoder.yudao.module.smartcity.controller.admin.patrolresources.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查资源 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PatrolResourcesRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "999")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "资源名称", example = "张三")
    @ExcelProperty("资源名称")
    private String resourceName;

    @Schema(description = "资源编号")
    @ExcelProperty("资源编号")
    private String resourceNumber;

    @Schema(description = "资源类型", example = "1")
    @ExcelProperty("资源类型")
    private String resourceType;

    @Schema(description = "地理位置")
    @ExcelProperty("地理位置")
    private String geographicLocation;

    @Schema(description = "区域划分")
    @ExcelProperty("区域划分")
    private String regionalDivision;

    @Schema(description = "规格型号")
    @ExcelProperty("规格型号")
    private String specificationAndModel;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}