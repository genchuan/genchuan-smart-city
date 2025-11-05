package cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.relmngcompsymbol.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 管理部件图示关联分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RelMngCompSymbolPageReqVO extends PageParam {

    @Schema(description = "关联ID，唯一编码，UUID生成", example = "3764")
    private String mngCompSymbolId;

    @Schema(description = "所属小类ID，关联管理部件小类ID，一个小类关联一个图示，关联管理部件小类表(gc_biz_mng_comp_minor)", example = "18800")
    private String minorId;

    @Schema(description = "所属小类名称，与小类ID同步，不可改，关联管理部件小类表(gc_biz_mng_comp_minor)", example = "芋艿")
    private String minorName;

    @Schema(description = "图示ID，关联图示符号库ID，关联图示符号库表(gc_biz_mng_comp_symbol_lib)", example = "15814")
    private String symbolId;

    @Schema(description = "图示名称，如“上水井盖图示”，关联图示符号库表(gc_biz_mng_comp_symbol_lib)", example = "李四")
    private String symbolName;

    @Schema(description = "图示路径，如“/static/symbols/water_cover.png”，支持PNG/SVG，关联图示符号库表(gc_biz_mng_comp_symbol_lib)")
    private String symbolPath;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}