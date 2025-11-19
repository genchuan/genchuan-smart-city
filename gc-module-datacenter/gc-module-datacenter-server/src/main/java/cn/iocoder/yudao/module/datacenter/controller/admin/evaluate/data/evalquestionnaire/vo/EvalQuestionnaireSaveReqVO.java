package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalquestionnaire.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 问卷调查管理新增/修改 Request VO")
@Data
public class EvalQuestionnaireSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "问卷ID")
    private String questionnaireId;

    @Schema(description = "问卷名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "问卷名称不能为空")
    private String questionnaireName;

    @Schema(description = "问卷编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "问卷编码不能为空")
    private String questionnaireCode;

    @Schema(description = "关联任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联任务ID不能为空")
    private String evalTaskId;

    @Schema(description = "关联任务名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联任务名称不能为空")
    private String evalTaskName;

    @Schema(description = "关联指标项ID")
    private String idxItemId;

    @Schema(description = "关联指标项名称")
    private String idxItemName;

    @Schema(description = "调查对象范围", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "调查对象范围不能为空")
    private String targetScope;

    @Schema(description = "发放方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "发放方式不能为空")
    private String releaseMethod;

    @Schema(description = "问卷链接")
    private String questionnaireUrl;

    @Schema(description = "问卷二维码")
    private String questionnaireQrcode;

    @Schema(description = "开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "开始时间不能为空")
    private LocalDateTime startTime;

    @Schema(description = "结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "结束时间不能为空")
    private LocalDateTime endTime;

    @Schema(description = "问卷状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "问卷状态不能为空")
    private String questionnaireStatus;

    @Schema(description = "填写人数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "填写人数不能为空")
    private Integer fillCount;

    @Schema(description = "问卷内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "问卷内容不能为空")
    private String questionnaireContent;

    @Schema(description = "创建人(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人(业务)不能为空")
    private String createUserBiz;

    @Schema(description = "创建时间(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间(业务)不能为空")
    private LocalDateTime createTimeBiz;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}