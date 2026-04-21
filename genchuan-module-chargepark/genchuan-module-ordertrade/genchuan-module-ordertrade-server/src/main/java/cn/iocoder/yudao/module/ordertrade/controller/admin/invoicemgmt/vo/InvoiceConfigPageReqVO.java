package cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 发票配置分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InvoiceConfigPageReqVO extends PageParam {

    @Schema(description = "开票类目，模糊查询")
    private String category;

    @Schema(description = "状态：pending/enabled/disabled")
    private String status;
}
