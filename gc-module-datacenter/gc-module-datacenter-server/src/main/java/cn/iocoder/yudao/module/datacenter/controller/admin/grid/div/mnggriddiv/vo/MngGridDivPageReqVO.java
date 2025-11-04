package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.mnggriddiv.vo;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 管理网格划分分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MngGridDivPageReqVO extends PageParam {

    @Schema(description = "管理网格ID", example = "7217")
    private String mngGridId;

    @Schema(description = "管理网格名称", example = "李四")
    private String mngGridName;

    @Schema(description = "所属乡镇ID", example = "10958")
    private String townStreetId;

    @Schema(description = "所含单元网格IDs")
    private String includedUnitIds;

    @Schema(description = "面积(m²)")
    private Integer area;

    @Schema(description = "划分时间")
//    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private String divTime;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

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

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

    @Schema(description = "排序字段：area-面积")
    private String sortField;

    @Schema(description = "排序方向：asc-升序, desc-降序", example = "desc")
    private String sortOrder;

}