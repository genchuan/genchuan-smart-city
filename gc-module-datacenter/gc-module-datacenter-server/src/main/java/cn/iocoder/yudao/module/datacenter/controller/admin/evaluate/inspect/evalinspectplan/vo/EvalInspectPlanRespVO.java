package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspect.evalinspectplan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 考察计划管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvalInspectPlanRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "计划ID")
    @ExcelProperty("计划ID")
    private String inspectPlanId;

    @Schema(description = "计划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("计划名称")
    private String inspectPlanName;

    @Schema(description = "计划编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("计划编码")
    private String inspectPlanCode;

    @Schema(description = "关联任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联任务ID")
    private String evalTaskId;

    @Schema(description = "关联任务名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联任务名称")
    private String evalTaskName;

    @Schema(description = "考察对象ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("考察对象ID")
    private String evalObjectId;

    @Schema(description = "考察对象名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("考察对象名称")
    private String evalObjectName;

    @Schema(description = "考察人员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("考察人员ID")
    private String inspectorId;

    @Schema(description = "考察人员姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("考察人员姓名")
    private String inspectorName;

    @Schema(description = "考察时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("考察时间")
    private LocalDateTime inspectTime;

    @Schema(description = "考察方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("考察方式")
    private String inspectMethod;

    @Schema(description = "考察内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("考察内容")
    private String inspectContent;

    @Schema(description = "计划状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("计划状态")
    private String planStatus;

    @Schema(description = "创建人(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人(业务)")
    private String createUserBiz;

    @Schema(description = "创建时间(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间(业务)")
    private LocalDateTime createTimeBiz;

    @Schema(description = "更新人(业务)")
    @ExcelProperty("更新人(业务)")
    private String updateUserBiz;

    @Schema(description = "更新时间(业务)")
    @ExcelProperty("更新时间(业务)")
    private LocalDateTime updateTimeBiz;

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