package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultrpt.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 结果报表管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvalResultRptPageReqVO extends PageParam {

    @Schema(description = "报表ID")
    private String resultRptId;

    @Schema(description = "报表名称")
    private String resultRptName;

    @Schema(description = "报表编码")
    private String resultRptCode;

    @Schema(description = "关联任务ID")
    private String evalTaskId;

    @Schema(description = "关联任务名称")
    private String evalTaskName;

    @Schema(description = "报表类型")
    private String rptType;

    @Schema(description = "统计维度")
    private String statDimension;

    @Schema(description = "行政区划代码")
    private String regionCode;

    @Schema(description = "行政区划名称")
    private String regionName;

    @Schema(description = "报表字段")
    private String rptFields;

    @Schema(description = "生成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] generateTime;

    @Schema(description = "报表状态")
    private String rptStatus;

    @Schema(description = "文件路径")
    private String filePath;

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