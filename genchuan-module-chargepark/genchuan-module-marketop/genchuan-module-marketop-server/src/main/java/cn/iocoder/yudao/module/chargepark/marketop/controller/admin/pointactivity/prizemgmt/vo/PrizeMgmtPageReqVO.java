package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 奖品管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PrizeMgmtPageReqVO extends PageParam {

    @Schema(description = "奖品名称")
    private String name;

    @Schema(description = "奖品类型")
    private String type;

    @Schema(description = "状态")
    private String status;

}
