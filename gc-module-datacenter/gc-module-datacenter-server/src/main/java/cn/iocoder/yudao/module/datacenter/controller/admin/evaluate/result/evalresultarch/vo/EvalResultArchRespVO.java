package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultarch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 结果存档管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvalResultArchRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "存档ID")
    @ExcelProperty("存档ID")
    private String resultArchId;

    @Schema(description = "存档编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("存档编号")
    private String archCode;

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

    @Schema(description = "最终评价等级", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("最终评价等级")
    private String finalEvalLevel;

    @Schema(description = "最终评价得分", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("最终评价得分")
    private BigDecimal finalEvalScore;

    @Schema(description = "存档原因", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("存档原因")
    private String archReason;

    @Schema(description = "存档时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("存档时间")
    private LocalDateTime archTime;

    @Schema(description = "存档附件")
    @ExcelProperty("存档附件")
    private String archAttachs;

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