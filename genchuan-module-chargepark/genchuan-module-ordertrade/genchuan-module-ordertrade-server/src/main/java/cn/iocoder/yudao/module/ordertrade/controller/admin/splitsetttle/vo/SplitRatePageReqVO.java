package cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 分账比例分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SplitRatePageReqVO extends PageParam {

    @Schema(description = "合作方ID")
    private Long partnerId;

    @Schema(description = "分账模式：fixed/ladder")
    private String splitMode;

    @Schema(description = "状态：pending/enabled/disabled")
    private String status;
}
