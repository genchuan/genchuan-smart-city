package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectrectifyreview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查巡检整改结果复核 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectRectifyReviewRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "复核ID")
    @ExcelProperty("复核ID")
    private String rectifyReviewId;

    @Schema(description = "复核编码")
    @ExcelProperty("复核编码")
    private String rectifyReviewCode;

    @Schema(description = "关联验收ID")
    @ExcelProperty("关联验收ID")
    private String acceptId;

    @Schema(description = "关联验收编码")
    @ExcelProperty("关联验收编码")
    private String acceptCode;

    @Schema(description = "关联整改任务ID")
    @ExcelProperty("关联整改任务ID")
    private String rectifyTaskId;

    @Schema(description = "关联问题名称")
    @ExcelProperty("关联问题名称")
    private String problemName;

    @Schema(description = "责任部门名称")
    @ExcelProperty("责任部门名称")
    private String deptName;

    @Schema(description = "整改负责人姓名")
    @ExcelProperty("整改负责人姓名")
    private String leaderUserName;

    @Schema(description = "复核方式")
    @ExcelProperty("复核方式")
    private String reviewMethod;

    @Schema(description = "复核照片URLs")
    @ExcelProperty("复核照片URLs")
    private String reviewPhotoUrls;

    @Schema(description = "复核结果")
    @ExcelProperty("复核结果")
    private String reviewResult;

    @Schema(description = "复核意见")
    @ExcelProperty("复核意见")
    private String reviewOpinion;

    @Schema(description = "复核人")
    @ExcelProperty("复核人")
    private String reviewUser;

    @Schema(description = "复核时间")
    @ExcelProperty("复核时间")
    private LocalDateTime reviewTime;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String createUser;

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