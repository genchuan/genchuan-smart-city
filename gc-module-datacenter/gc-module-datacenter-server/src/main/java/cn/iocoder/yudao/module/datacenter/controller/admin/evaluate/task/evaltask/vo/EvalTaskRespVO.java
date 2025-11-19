package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.task.evaltask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 任务管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvalTaskRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "任务ID")
    @ExcelProperty("任务ID")
    private String evalTaskId;

    @Schema(description = "任务名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("任务名称")
    private String evalTaskName;

    @Schema(description = "任务编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("任务编码")
    private String evalTaskCode;

    @Schema(description = "关联模板ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联模板ID")
    private String taskTemplateId;

    @Schema(description = "关联模板名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联模板名称")
    private String taskTemplateName;

    @Schema(description = "评价对象范围", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评价对象范围")
    private String objectScope;

    @Schema(description = "关联对象IDs")
    @ExcelProperty("关联对象IDs")
    private String relateObjectIds;

    @Schema(description = "关联对象Names")
    @ExcelProperty("关联对象Names")
    private String relateObjectNames;

    @Schema(description = "任务开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("任务开始时间")
    private LocalDateTime taskStartTime;

    @Schema(description = "任务结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("任务结束时间")
    private LocalDateTime taskEndTime;

    @Schema(description = "任务状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("任务状态")
    private String taskStatus;

    @Schema(description = "数据采集方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("数据采集方式")
    private String dataCollectMethod;

    @Schema(description = "任务描述")
    @ExcelProperty("任务描述")
    private String taskDesc;

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