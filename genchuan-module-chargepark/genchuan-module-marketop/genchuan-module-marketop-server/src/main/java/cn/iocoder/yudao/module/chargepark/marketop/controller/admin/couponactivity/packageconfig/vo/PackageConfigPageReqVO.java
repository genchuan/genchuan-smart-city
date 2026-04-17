package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.packageconfig.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 券包配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PackageConfigPageReqVO extends PageParam {

    @Schema(description = "券包名称")
    private String name;

    @Schema(description = "券包类型")
    private String type;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "适用范围")
    private String scope;

}
