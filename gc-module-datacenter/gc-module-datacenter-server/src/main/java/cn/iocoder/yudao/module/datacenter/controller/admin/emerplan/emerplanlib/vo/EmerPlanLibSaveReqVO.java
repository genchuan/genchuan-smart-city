package cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerplanlib.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 预案库新增/修改 Request VO")
@Data
public class EmerPlanLibSaveReqVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6079")
    private Long id;

    @Schema(description = "预案ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "14139")
    @NotEmpty(message = "预案ID，唯一编码，UUID生成不能为空")
    private String planId;

    @Schema(description = "预案编号，系统自动生成，格式“PLAN+年份(4位)+6位流水号”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "预案编号，系统自动生成，格式“PLAN+年份(4位)+6位流水号”不能为空")
    private String planNo;

    @Schema(description = "预案标准名称，如“XX市2025年城市内涝应急预案”", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "预案标准名称，如“XX市2025年城市内涝应急预案”不能为空")
    private String planName;

    @Schema(description = "分类ID，关联预案分类配置表", requiredMode = Schema.RequiredMode.REQUIRED, example = "16865")
    @NotEmpty(message = "分类ID，关联预案分类配置表不能为空")
    private String catId;

    @Schema(description = "分类名称，拼接“大类 - 中类 - 小类”名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "分类名称，拼接“大类 - 中类 - 小类”名称不能为空")
    private String catName;

    @Schema(description = "适用区域代码，关联行政区划表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "适用区域代码，关联行政区划表不能为空")
    private String applyRegionCode;

    @Schema(description = "适用区域名称，与区域代码同步", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "适用区域名称，与区域代码同步不能为空")
    private String applyRegionName;

    @Schema(description = "预案版本，如“V1.0”“V2.1”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "预案版本，如“V1.0”“V2.1”不能为空")
    private String planVersion;

    @Schema(description = "生效时间，格式YYYYMMDD，预案生效日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "生效时间，格式YYYYMMDD，预案生效日期不能为空")
    private LocalDate effectiveTime;

    @Schema(description = "失效时间，格式YYYYMMDD，预案失效日期")
    private LocalDate expireTime;

    @Schema(description = "预案文档路径，PDF格式预案存储路径", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "预案文档路径，PDF格式预案存储路径不能为空")
    private String planDocPath;

}