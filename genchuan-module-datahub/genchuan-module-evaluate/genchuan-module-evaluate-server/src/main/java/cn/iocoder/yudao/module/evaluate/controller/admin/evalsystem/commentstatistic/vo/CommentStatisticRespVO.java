package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.commentstatistic.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;


@Schema(description = "管理后台 - 巡查巡检统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CommentStatisticRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5509")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "指标项ID(关联指标项表的主键id  eval_index_item.id)", example = "12354")
    @ExcelProperty("指标项ID(关联指标项表的主键id  eval_index_item.id)")
    private Long itemId;

    @Schema(description = "街道：评价对象ID (关联eval_object.id)", example = "19141")
    @ExcelProperty("街道：评价对象ID (关联eval_object.id)")
    private Long objectId;

    @Schema(description = "统计指标项数量", example = "6666")
    @ExcelProperty("统计指标项数量")
    private Long count;

    @Schema(description = "关联到规则中回填的分数")
    @ExcelProperty("关联到规则中回填的分数")
    private Long score;

    @Schema(description = "地址编码")
    @ExcelProperty("地址编码")
    private String addressCoding;

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

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "变更日志")
    @ExcelProperty("变更日志")
    private String changeLog;

    @Schema(description = "状态: 1：待审核中，2：审核通过，3：不用审核", example = "1")
    @ExcelProperty("状态: 1：待审核中，2：审核通过，3：不用审核")
    private String status;

}