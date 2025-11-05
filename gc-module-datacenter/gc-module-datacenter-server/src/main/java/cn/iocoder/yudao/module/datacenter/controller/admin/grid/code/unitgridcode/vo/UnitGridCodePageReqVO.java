package cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.unitgridcode.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 单元网格编码分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UnitGridCodePageReqVO extends PageParam {

    @Schema(description = "编码ID", example = "8933")
    private String unitCodeId;

    @Schema(description = "单元网格标识码")
    private String unitGridCode;

    @Schema(description = "所属单元网格ID", example = "19876")
    private String unitGridId;

    @Schema(description = "行政区划完整代码")
    private String areaFullCode;

    @Schema(description = "顺序码")
    private String seqCode;

    @Schema(description = "编码生成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] generateTime;

    @Schema(description = "编码状态", example = "1")
    private String codeStatus;

    @Schema(description = "备注", example = "随便")
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