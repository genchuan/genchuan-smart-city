package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalquestionnaire.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 问卷调查管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvalQuestionnaireRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "问卷ID")
    @ExcelProperty("问卷ID")
    private String questionnaireId;

    @Schema(description = "问卷名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("问卷名称")
    private String questionnaireName;

    @Schema(description = "问卷编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("问卷编码")
    private String questionnaireCode;

    @Schema(description = "关联任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联任务ID")
    private String evalTaskId;

    @Schema(description = "关联任务名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联任务名称")
    private String evalTaskName;

    @Schema(description = "关联指标项ID")
    @ExcelProperty("关联指标项ID")
    private String idxItemId;

    @Schema(description = "关联指标项名称")
    @ExcelProperty("关联指标项名称")
    private String idxItemName;

    @Schema(description = "调查对象范围", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("调查对象范围")
    private String targetScope;

    @Schema(description = "发放方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发放方式")
    private String releaseMethod;

    @Schema(description = "问卷链接")
    @ExcelProperty("问卷链接")
    private String questionnaireUrl;

    @Schema(description = "问卷二维码")
    @ExcelProperty("问卷二维码")
    private String questionnaireQrcode;

    @Schema(description = "开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("开始时间")
    private LocalDateTime startTime;

    @Schema(description = "结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("结束时间")
    private LocalDateTime endTime;

    @Schema(description = "问卷状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("问卷状态")
    private String questionnaireStatus;

    @Schema(description = "填写人数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("填写人数")
    private Integer fillCount;

    @Schema(description = "问卷内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("问卷内容")
    private String questionnaireContent;

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