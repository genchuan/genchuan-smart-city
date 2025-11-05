package cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.relmngcompsymbol.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 管理部件图示关联新增/修改 Request VO")
@Data
public class RelMngCompSymbolSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2966")
    private Long id;

    @Schema(description = "关联ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "3764")
    @NotEmpty(message = "关联ID，唯一编码，UUID生成不能为空")
    private String mngCompSymbolId;

    @Schema(description = "所属小类ID，关联管理部件小类ID，一个小类关联一个图示，关联管理部件小类表(gc_biz_mng_comp_minor)", requiredMode = Schema.RequiredMode.REQUIRED, example = "18800")
    @NotEmpty(message = "所属小类ID，关联管理部件小类ID，一个小类关联一个图示，关联管理部件小类表(gc_biz_mng_comp_minor)不能为空")
    private String minorId;

    @Schema(description = "所属小类名称，与小类ID同步，不可改，关联管理部件小类表(gc_biz_mng_comp_minor)", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "所属小类名称，与小类ID同步，不可改，关联管理部件小类表(gc_biz_mng_comp_minor)不能为空")
    private String minorName;

    @Schema(description = "图示ID，关联图示符号库ID，关联图示符号库表(gc_biz_mng_comp_symbol_lib)", requiredMode = Schema.RequiredMode.REQUIRED, example = "15814")
    @NotEmpty(message = "图示ID，关联图示符号库ID，关联图示符号库表(gc_biz_mng_comp_symbol_lib)不能为空")
    private String symbolId;

    @Schema(description = "图示名称，如“上水井盖图示”，关联图示符号库表(gc_biz_mng_comp_symbol_lib)", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "图示名称，如“上水井盖图示”，关联图示符号库表(gc_biz_mng_comp_symbol_lib)不能为空")
    private String symbolName;

    @Schema(description = "图示路径，如“/static/symbols/water_cover.png”，支持PNG/SVG，关联图示符号库表(gc_biz_mng_comp_symbol_lib)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "图示路径，如“/static/symbols/water_cover.png”，支持PNG/SVG，关联图示符号库表(gc_biz_mng_comp_symbol_lib)不能为空")
    private String symbolPath;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}