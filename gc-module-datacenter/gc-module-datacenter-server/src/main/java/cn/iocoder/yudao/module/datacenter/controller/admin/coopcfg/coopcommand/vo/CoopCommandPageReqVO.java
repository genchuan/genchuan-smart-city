package cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopcommand.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 联动指令分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CoopCommandPageReqVO extends PageParam {

    @Schema(description = "指令ID，唯一编码，UUID生成", example = "10159")
    private String commandId;

    @Schema(description = "指令编号，系统自动生成，格式“CMD+年份（4位）+6位流水号”")
    private String commandNo;

    @Schema(description = "指令名称，如“XX火灾救援联动指令”", example = "张三")
    private String commandName;

    @Schema(description = "关联流程ID，关联联动流程配置表", example = "15538")
    private String flowId;

    @Schema(description = "关联事件编码，关联事件接报登记表")
    private String evtCode;

    @Schema(description = "联动单位ID，关联联动单位信息表", example = "20792")
    private String unitId;

}