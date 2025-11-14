package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.monptsummaryrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 监测点位汇总报表新增/修改 Request VO")
@Data
public class MonPtSummaryRptSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "报表ID")
    private String rptId;

    @Schema(description = "统计周期")
    private String statCycle;

    @Schema(description = "统计周期名称")
    private String statCycleName;

    @Schema(description = "行政区划代码")
    private String adminCode;

    @Schema(description = "行政区划名称")
    private String adminName;

    @Schema(description = "点位总数量")
    private Integer totalPtCount;

    @Schema(description = "空气质量监测点位数")
    private Integer airPtCount;

    @Schema(description = "交通流量监测点位数")
    private Integer trafPtCount;

    @Schema(description = "井盖状态监测点位数")
    private Integer manholePtCount;

    @Schema(description = "其他类型点位数")
    private Integer otherPtCount;

    @Schema(description = "新增点位数量")
    private Integer newPtCount;

    @Schema(description = "停用点位数量")
    private Integer disablePtCount;

    @Schema(description = "点位覆盖率")
    private BigDecimal ptCoverageRate;

    @Schema(description = "报表生成时间")
    private LocalDateTime rptCreateTime;

    @Schema(description = "报表生成人")
    private Long rptCreateUser;

    @Schema(description = "报表备注")
    private String rptRemark;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}