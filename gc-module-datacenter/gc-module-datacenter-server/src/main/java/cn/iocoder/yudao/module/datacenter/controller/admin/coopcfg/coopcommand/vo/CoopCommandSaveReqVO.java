package cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopcommand.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 联动指令新增/修改 Request VO")
@Data
public class CoopCommandSaveReqVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6391")
    private Long id;

    @Schema(description = "指令ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "10159")
    @NotEmpty(message = "指令ID，唯一编码，UUID生成不能为空")
    private String commandId;

    @Schema(description = "指令编号，系统自动生成，格式“CMD+年份（4位）+6位流水号”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "指令编号，系统自动生成，格式“CMD+年份（4位）+6位流水号”不能为空")
    private String commandNo;

    @Schema(description = "指令名称，如“XX火灾救援联动指令”", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "指令名称，如“XX火灾救援联动指令”不能为空")
    private String commandName;

    @Schema(description = "关联流程ID，关联联动流程配置表", example = "15538")
    private String flowId;

    @Schema(description = "关联事件编码，关联事件接报登记表")
    private String evtCode;

    @Schema(description = "联动单位ID，关联联动单位信息表", requiredMode = Schema.RequiredMode.REQUIRED, example = "20792")
    @NotEmpty(message = "联动单位ID，关联联动单位信息表不能为空")
    private String unitId;

}