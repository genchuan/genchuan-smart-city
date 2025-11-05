package cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.relmngcompsymbol.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 管理部件图示关联 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RelMngCompSymbolRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2966")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "关联ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "3764")
    @ExcelProperty("关联ID，唯一编码，UUID生成")
    private String mngCompSymbolId;

    @Schema(description = "所属小类ID，关联管理部件小类ID，一个小类关联一个图示，关联管理部件小类表(gc_biz_mng_comp_minor)", requiredMode = Schema.RequiredMode.REQUIRED, example = "18800")
    @ExcelProperty("所属小类ID，关联管理部件小类ID，一个小类关联一个图示，关联管理部件小类表(gc_biz_mng_comp_minor)")
    private String minorId;

    @Schema(description = "所属小类名称，与小类ID同步，不可改，关联管理部件小类表(gc_biz_mng_comp_minor)", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("所属小类名称，与小类ID同步，不可改，关联管理部件小类表(gc_biz_mng_comp_minor)")
    private String minorName;

    @Schema(description = "图示ID，关联图示符号库ID，关联图示符号库表(gc_biz_mng_comp_symbol_lib)", requiredMode = Schema.RequiredMode.REQUIRED, example = "15814")
    @ExcelProperty("图示ID，关联图示符号库ID，关联图示符号库表(gc_biz_mng_comp_symbol_lib)")
    private String symbolId;

    @Schema(description = "图示名称，如“上水井盖图示”，关联图示符号库表(gc_biz_mng_comp_symbol_lib)", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("图示名称，如“上水井盖图示”，关联图示符号库表(gc_biz_mng_comp_symbol_lib)")
    private String symbolName;

    @Schema(description = "图示路径，如“/static/symbols/water_cover.png”，支持PNG/SVG，关联图示符号库表(gc_biz_mng_comp_symbol_lib)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("图示路径，如“/static/symbols/water_cover.png”，支持PNG/SVG，关联图示符号库表(gc_biz_mng_comp_symbol_lib)")
    private String symbolPath;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}