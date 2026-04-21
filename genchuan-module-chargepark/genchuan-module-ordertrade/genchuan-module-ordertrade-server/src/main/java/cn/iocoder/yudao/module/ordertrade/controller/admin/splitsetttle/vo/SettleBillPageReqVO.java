package cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 结算单据分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SettleBillPageReqVO extends PageParam {

    @Schema(description = "单据编号，模糊查询")
    private String billNo;

    @Schema(description = "合作方ID")
    private Long partnerId;

    @Schema(description = "状态：pending_audit/pending_settle/settled/rejected")
    private String status;
}
