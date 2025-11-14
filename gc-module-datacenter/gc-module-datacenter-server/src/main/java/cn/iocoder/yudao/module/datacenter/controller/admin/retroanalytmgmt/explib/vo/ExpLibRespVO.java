package cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.explib.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 经验库 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ExpLibRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23262")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "经验ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "31736")
    @ExcelProperty("经验ID，唯一编码，UUID生成")
    private String expId;

    @Schema(description = "经验编号，系统自动生成，格式“EXP+年份（4位）+6位流水号”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("经验编号，系统自动生成，格式“EXP+年份（4位）+6位流水号”")
    private String expNo;

    @Schema(description = "经验标题，如“城市内涝救援冲锋舟预置经验”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("经验标题，如“城市内涝救援冲锋舟预置经验”")
    private String expTitle;

    @Schema(description = "关联事件类型ID，关联事件类型配置表小类ID，事件类型配置表（biz_evt_type_cfg）", requiredMode = Schema.RequiredMode.REQUIRED, example = "29465")
    @ExcelProperty("关联事件类型ID，关联事件类型配置表小类ID，事件类型配置表（biz_evt_type_cfg）")
    private String evtTypeId;

    @Schema(description = "关联事件类型名称，与类型ID同步，事件类型配置表（biz_evt_type_cfg）", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("关联事件类型名称，与类型ID同步，事件类型配置表（biz_evt_type_cfg）")
    private String evtTypeName;

    @Schema(description = "经验类型，经验/教训，标识经验性质", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("经验类型，经验/教训，标识经验性质")
    private String expType;

    @Schema(description = "经验内容，详细经验/教训，如“经验：内涝前24小时，将冲锋舟预置至XX、XX易涝点，缩短响应时间”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("经验内容，详细经验/教训，如“经验：内涝前24小时，将冲锋舟预置至XX、XX易涝点，缩短响应时间”")
    private String expContent;

    @Schema(description = "关联评估ID，关联处置结果评估表，来源评估报告，处置结果评估表（biz_disposal_result_eval）", example = "22319")
    @ExcelProperty("关联评估ID，关联处置结果评估表，来源评估报告，处置结果评估表（biz_disposal_result_eval）")
    private String evalId;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}