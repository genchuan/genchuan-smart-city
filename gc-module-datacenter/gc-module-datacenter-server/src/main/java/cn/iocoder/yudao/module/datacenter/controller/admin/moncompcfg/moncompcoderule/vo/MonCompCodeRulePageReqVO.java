package cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompcoderule.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

import java.time.LocalDateTime;


@Schema(description = "管理后台 - 监测部件标识码规则分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MonCompCodeRulePageReqVO extends PageParam {

    @Schema(description = "规则ID，唯一编码，UUID生成", example = "17420")
    private String codeRuleId;

    @Schema(description = "规则名称", example = "王五")
    private String ruleName;

    @Schema(description = "规则说明")
    private String ruleDesc;

    @Schema(description = "编码格式")
    private String codeFormat;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}