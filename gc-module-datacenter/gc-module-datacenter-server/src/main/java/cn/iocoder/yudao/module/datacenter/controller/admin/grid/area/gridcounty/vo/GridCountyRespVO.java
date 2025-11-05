package cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridcounty.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 县级及以上行政区划配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GridCountyRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "行政区划ID")
    @ExcelProperty("行政区划ID")
    private String adminDivisionId;

    @Schema(description = "行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划代码")
    private String adminCode;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划名称")
    private String adminName;

    @Schema(description = "行政区划级别(1-省级,2-市级,3-县级)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划级别(1-省级,2-市级,3-县级)")
    private Integer adminLevel;

    @Schema(description = "上级行政区划ID")
    @ExcelProperty("上级行政区划ID")
    private String parentAdminId;

    @Schema(description = "生效时间")
    @ExcelProperty("生效时间")
    private LocalDateTime effectiveTime;

    @Schema(description = "失效时间")
    @ExcelProperty("失效时间")
    private LocalDateTime invalidTime;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}