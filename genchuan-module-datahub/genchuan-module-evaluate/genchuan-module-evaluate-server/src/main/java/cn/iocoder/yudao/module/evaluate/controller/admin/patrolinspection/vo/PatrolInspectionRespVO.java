package cn.iocoder.yudao.module.evaluate.controller.admin.patrolinspection.vo;


import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.*;

@Schema(description = "管理后台 - 巡查巡检 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PatrolInspectionRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "32747")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "巡检人ID(关联sys_user.id)", example = "7549")
    @ExcelProperty("巡检人ID(关联sys_user.id)")
    private Long userId;

    @Schema(description = "巡检人名称", example = "张三")
    @ExcelProperty("巡检人名称")
    private String userName;

    @Schema(description = "体系ID (关联eval_index_system.id)", example = "14827")
    @ExcelProperty("体系ID (关联eval_index_system.id)")
    private Long systemId;

    @Schema(description = "体系名称", example = "食品安全体系")
    @ExcelProperty("体系名称")
    private String systemName;

    @Schema(description = "评价对象ID (关联eval_object.id)", example = "27902")
    @ExcelProperty("评价对象ID (关联eval_object.id)")
    private Long objectId;

    @Schema(description = "评价对象名称", example = "某某餐饮店")
    @ExcelProperty("评价对象名称")
    private String objectName;

    @Schema(description = "指标项ID(关联eval_index_item.id)", example = "6841")
    @ExcelProperty("指标项ID(关联eval_index_item)")
    private Long itemId;

    @Schema(description = "指标项名称", example = "环境卫生")
    @ExcelProperty("指标项名称")
    private String itemName;

    @Schema(description = "规则分类ID(关联eval_index_category.id)", example = "27032")
    @ExcelProperty("规则分类ID")
    private Long categoryId;

    @Schema(description = "规则分类名称", example = "加分规则")
    @ExcelProperty("规则分类名称")
    private String categoryName;

    @Schema(description = "评价说明")
    @ExcelProperty("评价说明")
    private String details;

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

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "变更日志")
    @ExcelProperty("变更日志")
    private String changeLog;

    @Schema(description = "状态: 1：待审核中，2：审核通过，3：不用审核", example = "2")
    @ExcelProperty("状态: 1：待审核中，2：审核通过，3：不用审核")
    private String status;

    @Schema(description = "图片URL")
    @ExcelProperty("图片URL")
    private String imageUrl;

    @Schema(description = "地址编码")
    @ExcelProperty("地址编码")
    private String addressCoding;

    @Schema(description = "评分规则ID(关联eval_comment_rule.id)")
    @ExcelProperty("评分规则ID")
    private Long ruleId;

    @Schema(description = "评分规则名称", example = "优秀加分")
    @ExcelProperty("评分规则名称")
    private String ruleName;

}