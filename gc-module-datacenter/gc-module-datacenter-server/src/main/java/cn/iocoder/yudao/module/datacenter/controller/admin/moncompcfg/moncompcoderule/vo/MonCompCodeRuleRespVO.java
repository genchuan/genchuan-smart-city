package cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompcoderule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 监测部件标识码规则 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MonCompCodeRuleRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3866")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "规则ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "17420")
    @ExcelProperty("规则ID，唯一编码，UUID生成")
    private String codeRuleId;

    @Schema(description = "规则名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("规则名称")
    private String ruleName;

    @Schema(description = "规则说明")
    @ExcelProperty("规则说明")
    private String ruleDesc;

    @Schema(description = "编码格式", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("编码格式")
    private String codeFormat;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}