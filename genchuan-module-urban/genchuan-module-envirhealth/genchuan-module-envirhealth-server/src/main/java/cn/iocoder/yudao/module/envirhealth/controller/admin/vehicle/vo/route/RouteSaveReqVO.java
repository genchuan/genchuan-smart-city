package cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.route;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 路线新增/修改 Request VO")
@Data
public class RouteSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9613")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "1342")
    private String sysRouteId;

    @Schema(description = "路线名称", example = "王五")
    private String name;

    @Schema(description = "路线编码")
    private String code;

    @Schema(description = "起点（含经度、纬度、具体地址）")
    private String startPoint;

    @Schema(description = "终点（含经度、纬度、具体地址）")
    private String endPoint;

    @Schema(description = "路线经纬度坐标")
    private String routePoints;

    @Schema(description = "路线长度（单位：公里）")
    private BigDecimal length;

    @Schema(description = "状态（可选值：0-禁用/1-启用）", example = "1")
    private Integer status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}