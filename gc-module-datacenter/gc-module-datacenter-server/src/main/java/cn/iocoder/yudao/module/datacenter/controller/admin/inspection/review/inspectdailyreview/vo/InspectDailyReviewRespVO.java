package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectdailyreview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查巡检日常结果复核 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectDailyReviewRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "复核ID")
    @ExcelProperty("复核ID")
    private String dailyReviewId;

    @Schema(description = "复核编码")
    @ExcelProperty("复核编码")
    private String dailyReviewCode;

    @Schema(description = "关联日常记录ID")
    @ExcelProperty("关联日常记录ID")
    private String dailyExecId;

    @Schema(description = "关联日常记录编码")
    @ExcelProperty("关联日常记录编码")
    private String dailyExecCode;

    @Schema(description = "巡查人员ID")
    @ExcelProperty("巡查人员ID")
    private String execUserId;

    @Schema(description = "巡查人员姓名")
    @ExcelProperty("巡查人员姓名")
    private String execUserName;

    @Schema(description = "巡查区域名称")
    @ExcelProperty("巡查区域名称")
    private String execAreaName;

    @Schema(description = "复核内容")
    @ExcelProperty("复核内容")
    private String reviewContent;

    @Schema(description = "复核结果")
    @ExcelProperty("复核结果")
    private String reviewResult;

    @Schema(description = "复核意见")
    @ExcelProperty("复核意见")
    private String reviewOpinion;

    @Schema(description = "复核依据")
    @ExcelProperty("复核依据")
    private String reviewBasis;

    @Schema(description = "复核人")
    @ExcelProperty("复核人")
    private String reviewUser;

    @Schema(description = "复核时间")
    @ExcelProperty("复核时间")
    private LocalDateTime reviewTime;

    @Schema(description = "整改要求")
    @ExcelProperty("整改要求")
    private String rectifyRequirement;

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