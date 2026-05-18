package cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingspace.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 车位信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkingSpacePageReqVO extends PageParam {

    @Schema(description = "车位编号，支持模糊查询")
    private String spaceCode;

    @Schema(description = "停车场名称，支持模糊查询")
    private String parkName;

    @Schema(description = "车位类型（固定/临时）")
    private String spaceType;

    @Schema(description = "车位状态（空闲/占用/预约中）")
    private String spaceStatus;

}
