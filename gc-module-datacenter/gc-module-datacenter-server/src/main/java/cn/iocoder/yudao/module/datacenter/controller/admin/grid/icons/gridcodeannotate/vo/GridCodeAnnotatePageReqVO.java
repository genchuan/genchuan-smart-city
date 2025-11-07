package cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridcodeannotate.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 网格标识码注记配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GridCodeAnnotatePageReqVO extends PageParam {

    @Schema(description = "注记ID", example = "20109")
    private String annotateId;

    @Schema(description = "比例尺")
    private String scale;

    @Schema(description = "网格类型", example = "1")
    private String gridType;

    @Schema(description = "字高(mm)")
    private BigDecimal fontHeight;

    @Schema(description = "字型", example = "2")
    private String fontType;

    @Schema(description = "颜色C值(%)")
    private BigDecimal colorC;

    @Schema(description = "颜色M值(%)")
    private BigDecimal colorM;

    @Schema(description = "颜色Y值(%)")
    private BigDecimal colorY;

    @Schema(description = "颜色K值(%)")
    private BigDecimal colorK;

    @Schema(description = "创建人", example = "13374")
    private String createUserId;

    @Schema(description = "备注", example = "你猜")
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