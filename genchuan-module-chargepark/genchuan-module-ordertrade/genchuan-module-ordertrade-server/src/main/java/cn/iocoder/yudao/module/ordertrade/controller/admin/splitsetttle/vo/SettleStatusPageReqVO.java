package cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 结算状态分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SettleStatusPageReqVO extends PageParam {

    @Schema(description = "关联结算单据ID")
    private Long billId;

    @Schema(description = "单据编号")
    private String billNo;

    @Schema(description = "状态：normal/abnormal")
    private String status;

    @Schema(description = "创建时间开始")
    private LocalDateTime createTimeStart;

    @Schema(description = "创建时间结束")
    private LocalDateTime createTimeEnd;
}
