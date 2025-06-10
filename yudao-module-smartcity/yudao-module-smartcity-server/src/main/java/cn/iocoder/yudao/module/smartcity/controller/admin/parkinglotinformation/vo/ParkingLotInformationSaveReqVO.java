package cn.iocoder.yudao.module.smartcity.controller.admin.parkinglotinformation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 停车场信息管理新增/修改 Request VO")
@Data
public class ParkingLotInformationSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "26393")
    private Long id;

    @Schema(description = "名称", example = "李四")
    private String name;

    @Schema(description = "位置")
    private String position;

    @Schema(description = "联系电话")
    private String contactNumber;

    @Schema(description = "车位编号")
    private String parkingSpaceNumber;

    @Schema(description = "监控设备数量")
    private String numberDevices;

}