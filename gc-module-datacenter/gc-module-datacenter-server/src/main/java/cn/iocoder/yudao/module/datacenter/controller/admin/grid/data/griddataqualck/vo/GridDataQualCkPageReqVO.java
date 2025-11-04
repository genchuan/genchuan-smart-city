package cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.griddataqualck.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 网格数据质量检查分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GridDataQualCkPageReqVO extends PageParam {

    @Schema(description = "质量检查ID", example = "10584")
    private String qualCkId;

    @Schema(description = "网格ID", example = "31757")
    private String gridId;

    @Schema(description = "网格类型", example = "2")
    private String gridType;

    @Schema(description = "网格编码")
    private String gridCode;

    @Schema(description = "检查项")
    private String ckItem;

    @Schema(description = "检查结果")
    private String ckResult;

    @Schema(description = "错误描述")
    private String errorDesc;

    @Schema(description = "整改建议")
    private String rectifySugg;

    @Schema(description = "检查时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] ckTime;

    @Schema(description = "检查人员", example = "25277")
    private String ckUserId;

    @Schema(description = "整改状态", example = "1")
    private String rectifyStatus;

    @Schema(description = "整改时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] rectifyTime;

    @Schema(description = "整改人员", example = "21785")
    private String rectifyUserId;

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