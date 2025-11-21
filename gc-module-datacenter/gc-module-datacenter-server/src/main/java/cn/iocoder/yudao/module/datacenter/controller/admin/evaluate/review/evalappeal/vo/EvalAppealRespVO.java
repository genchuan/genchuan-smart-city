package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalappeal.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 申诉管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvalAppealRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "申诉ID")
    @ExcelProperty("申诉ID")
    private String appealId;

    @Schema(description = "申诉编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("申诉编号")
    private String appealCode;

    @Schema(description = "关联公示ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联公示ID")
    private String resultPublicId;

    @Schema(description = "关联任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联任务ID")
    private String evalTaskId;

    @Schema(description = "评价对象ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评价对象ID")
    private String evalObjectId;

    @Schema(description = "评价对象名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评价对象名称")
    private String evalObjectName;

    @Schema(description = "申诉人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("申诉人ID")
    private String appealUserId;

    @Schema(description = "申诉人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("申诉人姓名")
    private String appealUserName;

    @Schema(description = "申诉理由", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("申诉理由")
    private String appealReason;

    @Schema(description = "证明材料")
    @ExcelProperty("证明材料")
    private String proofMats;

    @Schema(description = "申诉状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("申诉状态")
    private String appealStatus;

    @Schema(description = "提交时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("提交时间")
    private LocalDateTime submitTime;

    @Schema(description = "撤销时间")
    @ExcelProperty("撤销时间")
    private LocalDateTime cancelTime;

    @Schema(description = "创建人(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人(业务)")
    private String createUserBiz;

    @Schema(description = "创建时间(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间(业务)")
    private LocalDateTime createTimeBiz;

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