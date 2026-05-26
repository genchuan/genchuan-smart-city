package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 无牌入场分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class MyUnplateEnterPageReqVO extends PageParam {

    @Schema(description = "车辆类型")
    private String carType;

    @Schema(description = "审核状态")
    private String status;

    @Schema(description = "场站ID")
    private Long stationId;

    @Schema(description = "场站名称，支持模糊查询")
    private String stationName;

    @Schema(description = "联系电话")
    private String phone;
}