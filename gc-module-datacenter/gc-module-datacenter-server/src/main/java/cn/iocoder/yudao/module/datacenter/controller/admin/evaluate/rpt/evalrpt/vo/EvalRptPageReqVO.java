package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrpt.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 评价报告分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvalRptPageReqVO extends PageParam {

    @Schema(description = "报告ID")
    private String evalRptId;

    @Schema(description = "报告名称")
    private String evalRptName;

    @Schema(description = "报告编码")
    private String evalRptCode;

    @Schema(description = "关联模板ID")
    private String rptTemplateId;

    @Schema(description = "关联模板名称")
    private String rptTemplateName;

    @Schema(description = "关联存档ID")
    private String resultArchId;

    @Schema(description = "评价对象ID")
    private String evalObjectId;

    @Schema(description = "评价对象名称")
    private String evalObjectName;

    @Schema(description = "报告文件路径")
    private String rptFilePath;

    @Schema(description = "报告状态")
    private String rptStatus;

    @Schema(description = "生成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] generateTime;

    @Schema(description = "补充内容")
    private String suppleContent;

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