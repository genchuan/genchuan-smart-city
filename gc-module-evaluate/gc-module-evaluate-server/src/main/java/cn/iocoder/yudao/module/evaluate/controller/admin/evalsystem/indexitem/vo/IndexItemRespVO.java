package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexitem.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 指标项 Response VO")
@Data
@ExcelIgnoreUnannotated
public class IndexItemRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26265")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "指标项ID（UUID）", example = "21615")
    @ExcelProperty("指标项ID（UUID）")
    private String itemId;

    @Schema(description = "指标分类ID（关联eval_index_category.category_id）", example = "13142")
    @ExcelProperty("指标分类ID（关联eval_index_category.category_id）")
    private String categoryId;

    @Schema(description = "指标项名称", example = "张三")
    @ExcelProperty("指标项名称")
    private String name;

    @Schema(description = "指标类型ID（关联sys_index_type.type_id）", example = "10496")
    @ExcelProperty("指标类型ID（关联sys_index_type.type_id）")
    private String indexTypeId;

    @Schema(description = "计算方式ID（关联sys_calc_way.way_id）", example = "28359")
    @ExcelProperty("计算方式ID（关联sys_calc_way.way_id）")
    private String calcWayId;

    @Schema(description = "达标阈值")
    @ExcelProperty("达标阈值")
    private BigDecimal threshold;

    @Schema(description = "指标项权重")
    @ExcelProperty("指标项权重")
    private BigDecimal weight;

    @Schema(description = "排序序号")
    @ExcelProperty("排序序号")
    private Integer sortNo;

    @Schema(description = "创建人，关联sys_user.user_id")
    @ExcelProperty("创建人，关联sys_user.user_id")
    private String createBy;

    @Schema(description = "更新人，关联sys_user.user_id")
    @ExcelProperty("更新人，关联sys_user.user_id")
    private String updateBy;

    @Schema(description = "创建时间（业务字段）")
    @ExcelProperty("创建时间（业务字段）")
    private LocalDateTime bizCreateTime;

    @Schema(description = "更新时间（业务字段）")
    @ExcelProperty("更新时间（业务字段）")
    private LocalDateTime bizUpdateTime;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "status_id=1的记录数（传指定statusId时，仅该状态有值，其余为0）")
    @ExcelProperty("status_id=1的记录数")
    private Long status1Count;

    @Schema(description = "status_id=2的记录数")
    @ExcelProperty("status_id=2的记录数")
    private Long status2Count;

    @Schema(description = "符合条件的总记录数（过滤deleted=1后）")
    @ExcelProperty("符合条件的总记录数（过滤deleted=1后）")
    private Long totalCount;
}