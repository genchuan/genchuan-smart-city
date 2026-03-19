package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexsystem.vo;


import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 指标体系 Response VO")
@Data
@ExcelIgnoreUnannotated
public class IndexSystemRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15573")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "指标体系ID（UUID）", example = "12425")
    @ExcelProperty("指标体系ID（UUID）")
    private String systemId;

    @Schema(description = "体系名称", example = "芋艿")
    @ExcelProperty("体系名称")
    private String name;

    @Schema(description = "体系编码")
    @ExcelProperty("体系编码")
    private String code;

    @Schema(description = "适用对象类型ID（关联sys_object_type.type_id）", example = "21489")
    @ExcelProperty("适用对象类型ID（关联sys_object_type.type_id）")
    private String objectTypeId;

    @Schema(description = "版本号")
    @ExcelProperty("版本号")
    private String version;

    @Schema(description = "描述信息")
    @ExcelProperty("描述信息")
    @TableField("`desc`")
    private String desc;

    @Schema(description = "分类总数", example = "31470")
    @ExcelProperty("分类总数")
    private Integer categoryCount;

    @Schema(description = "指标项总数", example = "24419")
    @ExcelProperty("指标项总数")
    private Integer itemCount;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "14464")
    @ExcelProperty("状态ID（关联sys_status.status_id）")
    private Integer statusId;

    @Schema(description = "最近使用时间", example = "2024-29-56")
    @ExcelProperty("最近使用时间")
    private LocalDateTime lastUseTime;

    @Schema(description = "使用次数", example = "14")
    @ExcelProperty("使用次数")
    private Integer useCount;

    @Schema(description = "框架创建人")
    @ExcelProperty("框架创建人")
    private String creator;

    @Schema(description = "框架更新人")
    @ExcelProperty("框架更新人")
    private String updater;

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
    // ========== 体系基础信息 ==========

    @Schema(description = "适用对象类型名称")
    @ExcelProperty("适用对象类型名称")
    private String objectTypeName;

    @Schema(description = "描述信息")
    @ExcelProperty("描述信息")
    private String description;

    @Schema(description = "状态名称")
    @ExcelProperty("状态名称")
    private String statusName;

    @Schema(description = "变更日志（原始完整内容）")
    @ExcelProperty("变更日志（原始完整内容）")
    private String changeLog;

    @Schema(description = "变更日志（前50字，用于停用列表）")
    @ExcelProperty("变更日志（前50字，用于停用列表）")
    private String changeLogShort;

    // ========== 创建/更新信息 ==========
    @Schema(description = "创建人姓名")
    @ExcelProperty("创建人姓名")
    private String createUserName;

    @Schema(description = "指标分类名称")
    @ExcelProperty("指标分类名称")
    private String categoryName;

    @Schema(description = "指标项名称")
    @ExcelProperty("指标项名称")
    private String itemName;

    // ========== 新增缺失的核心字段 ==========
    @Schema(description = "排序", example = "1")
    private Integer sortNo;

    @Schema(description = "指标类型名称", example = "定量指标")
    @ExcelProperty("指标类型名称")
    private String indexTypeName; // 指标类型（sys_index_type.name）

    @Schema(description = "计算方式名称", example = "求和")
    @ExcelProperty("计算方式名称")
    private String calcWayName; // 计算方式（sys_calc_way.name）

    @Schema(description = "达标阈值", example = "90.00")
    @ExcelProperty("达标阈值")
    private BigDecimal threshold; // 达标阈值（eval_index_item.threshold，建议用BigDecimal）

    @Schema(description = "分类权重", example = "30.00")
    @ExcelProperty("分类权重")
    private BigDecimal categoryWeight; // 分类权重（eval_index_category.weight）

    @Schema(description = "指标项权重", example = "10.00")
    @ExcelProperty("指标项权重")
    private BigDecimal itemWeight; // 指标项权重（eval_index_item.weight）

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "更新人姓名（也可用作停用操作人）")
    @ExcelProperty("更新人姓名（也可用作停用操作人）")
    private String updateUserName;

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