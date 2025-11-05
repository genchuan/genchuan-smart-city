package cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridstreet.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 街道（镇、乡）行政区划配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GridStreetRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "街道ID")
    @ExcelProperty("街道ID")
    private String streetId;

    @Schema(description = "街道代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("街道代码")
    private String streetCode;

    @Schema(description = "街道名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("街道名称")
    private String streetName;

    @Schema(description = "所属县级行政区ID")
    @ExcelProperty("所属县级行政区ID")
    private String countyAdminId;

    @Schema(description = "街道级别(1-街道,2-镇,3-乡)")
    @ExcelProperty("街道级别(1-街道,2-镇,3-乡)")
    private Integer streetLevel;

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