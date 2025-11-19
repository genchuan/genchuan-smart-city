package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultanalysis.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 评价结果分析新增/修改 Request VO")
@Data
public class EvalResultAnalysisSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "分析ID")
    private String resultAnalysisId;

    @Schema(description = "分析名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分析名称不能为空")
    private String resultAnalysisName;

    @Schema(description = "分析编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分析编码不能为空")
    private String resultAnalysisCode;

    @Schema(description = "关联任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联任务ID不能为空")
    private String evalTaskId;

    @Schema(description = "关联任务名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联任务名称不能为空")
    private String evalTaskName;

    @Schema(description = "分析维度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分析维度不能为空")
    private String analysisDimension;

    @Schema(description = "行政区划代码")
    private String regionCode;

    @Schema(description = "行政区划名称")
    private String regionName;

    @Schema(description = "指标项ID")
    private String idxItemId;

    @Schema(description = "指标项名称")
    private String idxItemName;

    @Schema(description = "时间范围")
    private String timeRange;

    @Schema(description = "分析结果", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分析结果不能为空")
    private String analysisResult;

    @Schema(description = "图表类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "图表类型不能为空")
    private String chartType;

    @Schema(description = "生成时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "生成时间不能为空")
    private LocalDateTime generateTime;

    @Schema(description = "生成人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "生成人不能为空")
    private String generateUser;

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