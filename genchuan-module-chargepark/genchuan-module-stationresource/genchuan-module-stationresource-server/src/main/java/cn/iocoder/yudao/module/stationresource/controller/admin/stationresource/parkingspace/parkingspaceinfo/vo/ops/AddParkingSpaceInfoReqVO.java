package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import cn.idev.excel.annotation.ExcelIgnore;

@Data
@Schema(description = "车位信息创建 Request VO")
public class AddParkingSpaceInfoReqVO {

    @Schema(description = "车位编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "SPACE-001")
    @NotEmpty(message = "车位编号不能为空")
    private String spaceNo;

    @Schema(description = "所属场站ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "所属场站不能为空")
    private Long stationId;

    @Schema(description = "所属车库", requiredMode = Schema.RequiredMode.REQUIRED, example = "A座地下车库")
    @NotEmpty(message = "所属车库不能为空")
    private String garage;

    @Schema(description = "车位位置", example = "A区-B01")
//    @NotEmpty(message = "车位位置不能为空")
    private String location;

    @Schema(description = "车位类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "标准车位")
    @NotEmpty(message = "车位类型不能为空")
    private String type;

    @Schema(description = "设备类型,如：地锁/充电桩/摄像头", example = "地锁")
//    @NotEmpty(message = "设备类型不能为空")
    private String deviceType;

    @Schema(description = "备注", example = "近电梯口车位")
    private String remark;

    @Schema(description = "备用字段1", example = "备用信息1")
    @ExcelIgnore
    private String reserve1;

    @Schema(description = "备用字段2", example = "备用信息2")
    @ExcelIgnore
    private String reserve2;

//
}
