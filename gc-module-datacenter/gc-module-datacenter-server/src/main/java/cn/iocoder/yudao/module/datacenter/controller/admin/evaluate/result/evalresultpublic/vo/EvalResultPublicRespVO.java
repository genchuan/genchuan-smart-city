package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultpublic.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 结果公示 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvalResultPublicRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "公示ID")
    @ExcelProperty("公示ID")
    private String resultPublicId;

    @Schema(description = "关联审核ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联审核ID")
    private String resultAuditId;

    @Schema(description = "关联任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联任务ID")
    private String evalTaskId;

    @Schema(description = "关联任务名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联任务名称")
    private String evalTaskName;

    @Schema(description = "评价对象ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评价对象ID")
    private String evalObjectId;

    @Schema(description = "评价对象名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评价对象名称")
    private String evalObjectName;

    @Schema(description = "评价等级", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评价等级")
    private String evalLevel;

    @Schema(description = "评价得分", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评价得分")
    private BigDecimal evalScore;

    @Schema(description = "公示范围", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("公示范围")
    private String publicScope;

    @Schema(description = "公示开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("公示开始时间")
    private LocalDateTime publicStartTime;

    @Schema(description = "公示结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("公示结束时间")
    private LocalDateTime publicEndTime;

    @Schema(description = "公示状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("公示状态")
    private String publicStatus;

    @Schema(description = "公示链接")
    @ExcelProperty("公示链接")
    private String publicUrl;

    @Schema(description = "异议数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("异议数量")
    private Integer objectCount;

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