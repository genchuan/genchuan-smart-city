package cn.iocoder.yudao.module.waterdetection.controller.admin.waterusecategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 用水性质分类管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class WaterUseCategoryRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("用户编号")
    private String userCode;

    @Schema(description = "用水性质", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("用水性质")
    private String waterUseType;

    @Schema(description = "用水定额(立方米)")
    @ExcelProperty("用水定额(立方米)")
    private Double waterQuota;

    @Schema(description = "分类日期")
    @ExcelProperty("分类日期")
    private LocalDateTime categoryDate;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}