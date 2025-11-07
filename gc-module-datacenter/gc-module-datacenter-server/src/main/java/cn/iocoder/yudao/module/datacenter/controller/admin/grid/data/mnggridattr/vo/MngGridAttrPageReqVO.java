package cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.mnggridattr.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 管理网格属性分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MngGridAttrPageReqVO extends PageParam {

    @Schema(description = "属性ID")
    private String mgAttrId;

    @Schema(description = "管理网格ID")
    private String mngGridId;

    @Schema(description = "管理网格编码")
    private String mgGridCode;

    @Schema(description = "面积(m²)")
    private Integer area;

    @Schema(description = "初始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] initTime;

    @Schema(description = "终止时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] endTime;

    @Schema(description = "所含单元网格列表")
    private String unitGridList;

    @Schema(description = "网格员ID")
    private String gridUserId;

    @Schema(description = "网格员姓名")
    private String gridUserName;

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