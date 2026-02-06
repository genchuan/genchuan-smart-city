package cn.iocoder.yudao.module.park.controller.admin.park.resource.roadsideberthmanage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 路测泊位管理新增/修改 Request VO")
@Data
public class RoadsideBerthManageSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27797")
    private Long id;

    @Schema(description = "车场ID", example = "1")
    private String parkId;

    @Schema(description = "泊位编号")
    private String berthCode;

    @Schema(description = "路段名称", example = "赵六")
    private String roadName;

    @Schema(description = "位置描述")
    private String locationDesc;

    @Schema(description = "泊位类型", example = "2")
    private String berthType;

    @Schema(description = "坐标X")
    private BigDecimal coordinateX;

    @Schema(description = "坐标Y")
    private BigDecimal coordinateY;

    @Schema(description = "当前车辆")
    private String currentCar;

    @Schema(description = "启用状态", example = "1")
    private String berthStatus;

    @Schema(description = "所属行政区划代码")
    private String areaCode;

    @Schema(description = "路侧管理信息")
    private String roadsideInfo;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}
