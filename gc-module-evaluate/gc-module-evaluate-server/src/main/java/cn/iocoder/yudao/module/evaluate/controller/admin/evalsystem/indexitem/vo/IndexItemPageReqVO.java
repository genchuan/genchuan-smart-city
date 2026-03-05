package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexitem.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 指标项分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IndexItemPageReqVO extends PageParam {

    @Schema(description = "指标项ID（UUID）", example = "21615")
    private String itemId;

    @Schema(description = "指标分类ID（关联eval_index_category.category_id）", example = "13142")
    private String categoryId;

    @Schema(description = "指标项名称", example = "张三")
    private String name;

    @Schema(description = "指标类型ID（关联sys_index_type.type_id）", example = "10496")
    private String indexTypeId;

    @Schema(description = "计算方式ID（关联sys_calc_way.way_id）", example = "28359")
    private String calcWayId;

    @Schema(description = "达标阈值")
    private BigDecimal threshold;

    @Schema(description = "指标项权重")
    private BigDecimal weight;

    @Schema(description = "排序序号")
    private Integer sortNo;

    @Schema(description = "创建人，关联sys_user.user_id")
    private String createBy;

    @Schema(description = "更新人，关联sys_user.user_id")
    private String updateBy;

    @Schema(description = "创建时间（业务字段）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bizCreateTime;

    @Schema(description = "更新时间（业务字段）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bizUpdateTime;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}