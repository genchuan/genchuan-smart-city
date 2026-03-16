package cn.iocoder.yudao.module.facility.controller.admin.road.roadfacility.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 道路设施 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RoadFacilityRespVO {

    @Schema(description = "[主键ID] 主键，道路设施唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "10224")
    @ExcelProperty("[主键ID] 主键，道路设施唯一标识")
    private Long id;

    @Schema(description = "[道路编码] UUID格式", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[道路编码] UUID格式")
    private String roadCode;

    @Schema(description = "[路段名称] 路段名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("[路段名称] 路段名称")
    private String roadName;

    @Schema(description = "[所属区域编码] 12位地区码（GB/T 2260），关联sys_area.full_code", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[所属区域编码] 12位地区码（GB/T 2260），关联sys_area.full_code")
    private String areaCode;

    @Schema(description = "[所在地区名称]", example = "张三")
    @ExcelProperty("[所在地区名称]")
    private String areaName;

    @Schema(description = "[路段长度] 路段长度，数值")
    @ExcelProperty("[路段长度] 路段长度，数值")
    private BigDecimal length;

    @Schema(description = "[路段宽度] 路段宽度，数值")
    @ExcelProperty("[路段宽度] 路段宽度，数值")
    private BigDecimal width;

    @Schema(description = "[建成时间] 建成时间")
    @ExcelProperty("[建成时间] 建成时间")
    private LocalDate buildTime;

    @Schema(description = "[使用状态] 如:正常/维修中/废弃", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("[使用状态] 如:正常/维修中/废弃")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    @ExcelProperty("[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    @ExcelProperty("[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    @ExcelProperty("[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    @ExcelProperty("[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}
