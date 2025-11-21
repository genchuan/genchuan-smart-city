package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmatter.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 按管理事项预警告警统计新增/修改 Request VO")
@Data
public class EarlyWarnMatterSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "统计ID")
    private String warnMngMatterStatId;

    @Schema(description = "统计周期")
    private String statCycle;

    @Schema(description = "事项大类ID")
    private String matterMajorId;

    @Schema(description = "事项大类名称")
    private String matterMajorName;

    @Schema(description = "事项小类ID")
    private String matterMinorId;

    @Schema(description = "事项小类名称")
    private String matterMinorName;

    @Schema(description = "预警总数")
    private Integer totalWarnCount;

    @Schema(description = "已办结数")
    private Integer completedCount;

    @Schema(description = "超时未处置数")
    private Integer overtimeCount;

    @Schema(description = "处置率")
    private BigDecimal handleRate;

    @Schema(description = "主要处置部门代码")
    private String mainDeptCode;

    @Schema(description = "主要处置部门名称")
    private String mainDeptName;

    @Schema(description = "统计人")
    private String statUserId;

    @Schema(description = "统计时间")
    private LocalDateTime statTime;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}