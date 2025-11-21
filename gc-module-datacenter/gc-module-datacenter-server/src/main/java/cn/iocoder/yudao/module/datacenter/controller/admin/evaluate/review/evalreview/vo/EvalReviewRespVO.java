package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalreview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 复核办理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvalReviewRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "复核ID")
    @ExcelProperty("复核ID")
    private String reviewId;

    @Schema(description = "关联申诉ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联申诉ID")
    private String appealId;

    @Schema(description = "申诉编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("申诉编号")
    private String appealCode;

    @Schema(description = "关联任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联任务ID")
    private String evalTaskId;

    @Schema(description = "评价对象ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评价对象ID")
    private String evalObjectId;

    @Schema(description = "评价对象名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评价对象名称")
    private String evalObjectName;

    @Schema(description = "复核人员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("复核人员ID")
    private String reviewUserId;

    @Schema(description = "复核人员姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("复核人员姓名")
    private String reviewUserName;

    @Schema(description = "复核意见", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("复核意见")
    private String reviewOpinion;

    @Schema(description = "核查材料")
    @ExcelProperty("核查材料")
    private String reviewMats;

    @Schema(description = "复核状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("复核状态")
    private String reviewStatus;

    @Schema(description = "分配时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分配时间")
    private LocalDateTime assignTime;

    @Schema(description = "复核完成时间")
    @ExcelProperty("复核完成时间")
    private LocalDateTime reviewCompleteTime;

    @Schema(description = "分配人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分配人ID")
    private String assignUserId;

    @Schema(description = "分配人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分配人姓名")
    private String assignUserName;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}