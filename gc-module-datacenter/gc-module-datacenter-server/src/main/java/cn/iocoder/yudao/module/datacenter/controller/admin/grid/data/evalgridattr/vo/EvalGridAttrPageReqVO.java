package cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.evalgridattr.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 评价网格属性分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvalGridAttrPageReqVO extends PageParam {

    @Schema(description = "属性ID")
    private String evalAttrId;

    @Schema(description = "评价网格ID")
    private String evalGridId;

    @Schema(description = "评价网格编码")
    private String evalGridCode;

    @Schema(description = "面积(m²)")
    private Integer area;

    @Schema(description = "评价网格类型(A/B/C)")
    private String gridType;

    @Schema(description = "初始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] initTime;

    @Schema(description = "终止时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] endTime;

    @Schema(description = "所含管理网格列表")
    private String mgGridList;

    @Schema(description = "关联评价指标ID")
    private String idxId;

    @Schema(description = "关联评价指标名称")
    private String idxName;

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