package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultanalysis.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 评价结果分析分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvalResultAnalysisPageReqVO extends PageParam {

    @Schema(description = "分析ID")
    private String resultAnalysisId;

    @Schema(description = "分析名称")
    private String resultAnalysisName;

    @Schema(description = "分析编码")
    private String resultAnalysisCode;

    @Schema(description = "关联任务ID")
    private String evalTaskId;

    @Schema(description = "关联任务名称")
    private String evalTaskName;

    @Schema(description = "分析维度")
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

    @Schema(description = "分析结果")
    private String analysisResult;

    @Schema(description = "图表类型")
    private String chartType;

    @Schema(description = "生成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] generateTime;

    @Schema(description = "生成人")
    private String generateUser;

    @Schema(description = "创建时间(业务)")
    private LocalDateTime createTimeBiz;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}