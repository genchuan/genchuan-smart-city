package cn.iocoder.yudao.module.datacenter.controller.admin.polcomplianceck.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 政策合规校验 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PolComplianceCkRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22398")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "校验ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "22915")
    @ExcelProperty("校验ID，唯一编码，UUID生成")
    private String complianceCkId;

    @Schema(description = "关联方案ID，关联决策方案表的方案ID，决策方案表（biz_decision_scheme）", requiredMode = Schema.RequiredMode.REQUIRED, example = "14444")
    @ExcelProperty("关联方案ID，关联决策方案表的方案ID，决策方案表（biz_decision_scheme）")
    private String relSchemeId;

    @Schema(description = "关联方案名称，与方案ID同步，决策方案表（biz_decision_scheme）", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("关联方案名称，与方案ID同步，决策方案表（biz_decision_scheme）")
    private String relSchemeName;

    @Schema(description = "校验政策IDs，关联政策法规库表的政策ID，逗号分隔，政策法规库表（sys_pol_library）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("校验政策IDs，关联政策法规库表的政策ID，逗号分隔，政策法规库表（sys_pol_library）")
    private String ckPolIds;

    @Schema(description = "校验政策名称，与政策IDs同步，逗号分隔，政策法规库表（sys_pol_library）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("校验政策名称，与政策IDs同步，逗号分隔，政策法规库表（sys_pol_library）")
    private String ckPolNames;

    @Schema(description = "校验状态：待校验/校验中/已通过/存在风险，标识校验进度", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("校验状态：待校验/校验中/已通过/存在风险，标识校验进度")
    private String ckStatus;

    @Schema(description = "风险等级：无/低/中/高，校验状态为“存在风险”时必填")
    @ExcelProperty("风险等级：无/低/中/高，校验状态为“存在风险”时必填")
    private String riskLevel;

    @Schema(description = "风险点数量，个，校验出的风险点总数，合规校验结果表（biz_compliance_ck_result）", example = "22118")
    @ExcelProperty("风险点数量，个，校验出的风险点总数，合规校验结果表（biz_compliance_ck_result）")
    private Integer riskPtCount;

    @Schema(description = "合规率，%，(校验项总数-风险点数量)/校验项总数×100，0.00-100.00")
    @ExcelProperty("合规率，%，(校验项总数-风险点数量)/校验项总数×100，0.00-100.00")
    private BigDecimal complianceRate;

    @Schema(description = "校验结论，如“方案存在2个中风险点，需整改后实施”")
    @ExcelProperty("校验结论，如“方案存在2个中风险点，需整改后实施”")
    private String ckConclusion;

    @Schema(description = "校验人，发起校验的用户ID，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("校验人，发起校验的用户ID，用户信息表（sys_user）")
    private String ckUser;

    @Schema(description = "校验时间，格式：yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("校验时间，格式：yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ckTime;

    @Schema(description = "整改建议，针对风险点的整改方向（如“设备高度降至8米以内，补充安全评估报告”）")
    @ExcelProperty("整改建议，针对风险点的整改方向（如“设备高度降至8米以内，补充安全评估报告”）")
    private String rectificationSugg;

    @Schema(description = "复核状态：待复核/复核通过/复核驳回，风险整改后复核状态", example = "2")
    @ExcelProperty("复核状态：待复核/复核通过/复核驳回，风险整改后复核状态")
    private String reviewStatus;

    @Schema(description = "复核人，复核用户ID，复核状态非“待复核”时必填，用户信息表（sys_user）")
    @ExcelProperty("复核人，复核用户ID，复核状态非“待复核”时必填，用户信息表（sys_user）")
    private String reviewUser;

    @Schema(description = "复核时间，格式：yyyy-MM-dd HH:mm:ss，复核状态非“待复核”时必填")
    @ExcelProperty("复核时间，格式：yyyy-MM-dd HH:mm:ss，复核状态非“待复核”时必填")
    private LocalDateTime reviewTime;

    @Schema(description = "分类扩展字段1，预留，如“校验批次”")
    @ExcelProperty("分类扩展字段1，预留，如“校验批次”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“校验批次”")
    @ExcelProperty("分类扩展字段2，预留，如“校验批次”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储政策版本号")
    @ExcelProperty("通用扩展字段1，预留，存储政策版本号")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储政策版本号")
    @ExcelProperty("通用扩展字段2，预留，存储政策版本号")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}