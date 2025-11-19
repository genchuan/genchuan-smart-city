package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectcyclerpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 巡查巡检周期统计新增/修改 Request VO")
@Data
public class InspectCycleRptSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "统计ID")
    private String cycleStatId;

    @Schema(description = "统计维度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计维度不能为空")
    private String statDimension;

    @Schema(description = "所属区域代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属区域代码不能为空")
    private String areaCode;

    @Schema(description = "所属区域名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属区域名称不能为空")
    private String areaName;

    @Schema(description = "周期列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "周期列表不能为空")
    private String cycleList;

    @Schema(description = "巡查次数列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "巡查次数列表不能为空")
    private String inspectCountList;

    @Schema(description = "问题数列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "问题数列表不能为空")
    private String problemCountList;

    @Schema(description = "整改率列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "整改率列表不能为空")
    private String rectifyRateList;

    @Schema(description = "平均问题发现率列表")
    private String avgFoundRateList;

    @Schema(description = "统计人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计人不能为空")
    private String statUser;

    @Schema(description = "统计时间(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计时间(业务)不能为空")
    private LocalDateTime statTimeBiz;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}