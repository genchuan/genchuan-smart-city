package cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.vehicleaccess.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 车辆通行分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VehicleAccessPageReqVO extends PageParam {

    @Schema(description = "车牌号，支持模糊查询")
    private String plateNo;

    @Schema(description = "车辆类型（小型车/大型车）")
    private String vehicleType;

    @Schema(description = "停车场名称")
    private String parkName;

    @Schema(description = "通行状态（进场中/出场中/已离场）")
    private String accessStatus;

    @Schema(description = "缴费状态（已缴费/未缴费）")
    private String payStatus;

    @Schema(description = "通行开始时间，格式时间戳")
    private String startTime;

    @Schema(description = "通行结束时间，格式时间戳")
    private String endTime;

}
