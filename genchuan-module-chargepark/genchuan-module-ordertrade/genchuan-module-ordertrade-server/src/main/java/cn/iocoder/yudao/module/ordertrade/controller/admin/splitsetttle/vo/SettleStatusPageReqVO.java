package cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 结算状态分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SettleStatusPageReqVO extends PageParam {

    @Schema(description = "关联结算单据ID")
    private Long billId;

    @Schema(description = "状态：normal/abnormal")
    private String status;
}
