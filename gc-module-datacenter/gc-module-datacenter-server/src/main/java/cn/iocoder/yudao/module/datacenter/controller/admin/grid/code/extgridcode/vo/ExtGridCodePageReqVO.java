package cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.extgridcode.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 扩展网格编码分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExtGridCodePageReqVO extends PageParam {

    @Schema(description = "编码ID")
    private String extCodeId;

    @Schema(description = "扩展网格标识码")
    private String extGridCode;

    @Schema(description = "所属扩展网格ID")
    private String extGridId;

    @Schema(description = "关联行政区划ID")
    private Long areaId;

    @Schema(description = "行政区划完整代码")
    private String areaFullCode;

    @Schema(description = "顺序码")
    private String seqCode;

    @Schema(description = "类型码(G/Z/S/Q)")
    private String typeCode;

    @Schema(description = "编码生成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] generateTime;

    @Schema(description = "编码状态")
    private String codeStatus;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}