package cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.explib.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 经验库分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExpLibPageReqVO extends PageParam {

    @Schema(description = "经验ID，唯一编码，UUID生成", example = "31736")
    private String expId;

    @Schema(description = "经验编号，系统自动生成，格式“EXP+年份（4位）+6位流水号”")
    private String expNo;

    @Schema(description = "经验标题，如“城市内涝救援冲锋舟预置经验”")
    private String expTitle;

    @Schema(description = "关联事件类型ID，关联事件类型配置表小类ID，事件类型配置表（biz_evt_type_cfg）", example = "29465")
    private String evtTypeId;

    @Schema(description = "关联事件类型名称，与类型ID同步，事件类型配置表（biz_evt_type_cfg）", example = "张三")
    private String evtTypeName;

    @Schema(description = "经验类型，经验/教训，标识经验性质", example = "1")
    private String expType;

    @Schema(description = "经验内容，详细经验/教训，如“经验：内涝前24小时，将冲锋舟预置至XX、XX易涝点，缩短响应时间”")
    private String expContent;

    @Schema(description = "关联评估ID，关联处置结果评估表，来源评估报告，处置结果评估表（biz_disposal_result_eval）", example = "22319")
    private String evalId;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}