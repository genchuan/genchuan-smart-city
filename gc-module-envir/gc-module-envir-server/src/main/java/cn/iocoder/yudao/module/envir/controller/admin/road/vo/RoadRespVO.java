package cn.iocoder.yudao.module.envir.controller.admin.road.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 道路 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RoadRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16336")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "13641")
    @ExcelProperty("业务主键（UUID）")
    private String sysRoadId;

    @Schema(description = "道路名称", example = "王五")
    @ExcelProperty("道路名称")
    private String name;

    @Schema(description = "道路编码")
    @ExcelProperty("道路编码")
    private String code;

    @Schema(description = "道路长度")
    @ExcelProperty("道路长度")
    private BigDecimal length;

    @Schema(description = "道路宽度")
    @ExcelProperty("道路宽度")
    private BigDecimal width;

    @Schema(description = "道路类型", example = "1")
    @ExcelProperty("道路类型")
    private String roadType;

    @Schema(description = "状态：启用/禁用", example = "1")
    @ExcelProperty("状态：启用/禁用")
    private Integer status;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}