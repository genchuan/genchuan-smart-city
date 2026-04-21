package cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 支付应用分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PayAppPageReqVO extends PageParam {

    @Schema(description = "应用名，模糊查询")
    private String name;

    @Schema(description = "状态：0未生效/1已生效")
    private Integer status;
}
