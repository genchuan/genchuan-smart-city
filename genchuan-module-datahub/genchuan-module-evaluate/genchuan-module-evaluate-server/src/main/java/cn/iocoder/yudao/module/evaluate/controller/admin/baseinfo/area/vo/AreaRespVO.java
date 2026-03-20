package cn.iocoder.yudao.module.evaluate.controller.admin.baseinfo.area.vo;

//import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
//import com.alibaba.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 区域编码 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AreaRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17233")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "区域编码（业务主键）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("区域编码（业务主键）")
    private String areaCode;

    @Schema(description = "区域名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("区域名称")
    private String areaName;

    @Schema(description = "上级区域编码（关联sys_area.area_code）")
    @ExcelProperty("上级区域编码（关联sys_area.area_code）")
    private String parentCode;

    @Schema(description = "区域层级（1-省级，2-市级，3-区级/县级等）")
    @ExcelProperty("区域层级（1-省级，2-市级，3-区级/县级等）")
    private Integer level;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "17097")
    @ExcelProperty("状态ID（关联sys_status.status_id）")
    private Integer statusId;

    @Schema(description = "业务创建时间")
    @ExcelProperty("业务创建时间")
    private LocalDateTime bizCreateTime;

    @Schema(description = "业务更新时间")
    @ExcelProperty("业务更新时间")
    private LocalDateTime bizUpdateTime;

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