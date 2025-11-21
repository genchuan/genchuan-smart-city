package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarntype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 预警告警类型维度统计新增/修改 Request VO")
@Data
public class EarlyWarnTypeSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "统计ID")
    private String typeStatId;

    @Schema(description = "统计周期")
    private String statCycle;

    @Schema(description = "预警类型")
    private String warnType;

    @Schema(description = "预警类型编码")
    private String warnTypeCode;

    @Schema(description = "预警总数")
    private Integer totalWarnCount;

    @Schema(description = "占比")
    private BigDecimal proportion;

    @Schema(description = "已办结数")
    private Integer completedCount;

    @Schema(description = "处置率")
    private BigDecimal handleRate;

    @Schema(description = "主要分布区域名称")
    private String mainRegionName;

    @Schema(description = "主要分布区域数量")
    private Integer mainRegionCount;

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