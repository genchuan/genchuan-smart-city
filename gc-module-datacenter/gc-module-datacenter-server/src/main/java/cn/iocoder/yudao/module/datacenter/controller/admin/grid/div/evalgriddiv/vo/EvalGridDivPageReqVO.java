package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.evalgriddiv.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 评价网格划分分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvalGridDivPageReqVO extends PageParam {

    @Schema(description = "评价网格ID", example = "15263")
    private String evalGridId;

    @Schema(description = "评价网格名称", example = "赵六")
    private String evalGridName;

    @Schema(description = "所属乡镇ID", example = "10750")
    private String townStreetId;

    @Schema(description = "评价网格类型(A/B/C)", example = "2")
    private String gridType;

    @Schema(description = "所含管理网格IDs")
    private String includedMgIds;

    @Schema(description = "面积(m²)")
    private Integer area;

    @Schema(description = "划分时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] divTime;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

    @Schema(description = "备注", example = "你说的对")
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

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

    // 新增：排序字段配置
    @Schema(description = "排序字段", example = "area")
    private String sortField;

    @Schema(description = "排序方向", example = "asc", allowableValues = {"asc", "desc"})
    private String sortOrder;

}