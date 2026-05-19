package cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingspace.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Schema(description = "管理后台 - 车位信息新增 Request VO")
@Data
public class ParkingSpaceCreateReqVO {

    @Schema(description = "车位编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "车位编号不能为空")
    private String spaceCode;

    @Schema(description = "停车场名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "停车场名称不能为空")
    private String parkName;

    @Schema(description = "车位类型（固定/临时）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "车位类型不能为空")
    private String spaceType;

    @Schema(description = "租用信息")
    private String rentInfo;

}
