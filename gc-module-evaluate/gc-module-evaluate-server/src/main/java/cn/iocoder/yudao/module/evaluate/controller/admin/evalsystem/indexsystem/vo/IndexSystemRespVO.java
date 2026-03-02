package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexsystem.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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

    @Schema(description = "更新人ID（关联sys_user.user_id）")
    @ExcelProperty("更新人ID（关联sys_user.user_id）")
    private String updateBy;

    @Schema(description = "创建人ID（关联sys_user.user_id）")
    @ExcelProperty("创建人ID（关联sys_user.user_id）")
    private String createBy;

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

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "更新人姓名（也可用作停用操作人）")
    @ExcelProperty("更新人姓名（也可用作停用操作人）")
    private String updateUserName;
}