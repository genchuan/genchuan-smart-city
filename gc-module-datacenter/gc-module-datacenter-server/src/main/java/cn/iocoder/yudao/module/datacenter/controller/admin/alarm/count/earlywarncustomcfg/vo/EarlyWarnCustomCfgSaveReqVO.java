package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarncustomcfg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 预警告警自定义统计配置新增/修改 Request VO")
@Data
public class EarlyWarnCustomCfgSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "配置ID")
    private String customStatCfgId;

    @Schema(description = "配置名称")
    private String cfgName;

    @Schema(description = "统计周期")
    private String statCycle;

    @Schema(description = "统计维度")
    private String statDimensions;

    @Schema(description = "筛选条件")
    private String filterConditions;

    @Schema(description = "分组维度")
    private String groupDimensions;

    @Schema(description = "统计指标")
    private String statIndicators;

    @Schema(description = "创建人ID")
    private String createUserId;

    @Schema(description = "创建人姓名")
    private String createUserName;

    @Schema(description = "是否常用")
    private String isFrequentlyUsed;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}