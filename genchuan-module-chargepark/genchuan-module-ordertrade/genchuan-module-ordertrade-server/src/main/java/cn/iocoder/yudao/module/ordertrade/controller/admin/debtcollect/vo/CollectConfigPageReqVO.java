package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - CollectConfig 分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CollectConfigPageReqVO extends PageParam {

    @Schema(description = "追缴方式")
    private String collectMethod;
    @Schema(description = "状态")
    private String status;
}
