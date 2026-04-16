package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 规则配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RuleConfigPageReqVO extends PageParam {

    @Schema(description = "规则名称")
    private String name;

    @Schema(description = "规则类型")
    private String type;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "适用场景")
    private String scene;

}
