package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.point;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 点位新增/修改 Request VO")
@Data
public class PointSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8002")
    private Long id;

    @Schema(description = "点位主键（UUID）", example = "27395")
    private String pointId;

    @Schema(description = "点位名称", example = "李四")
    private String pointName;

    @Schema(description = "关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "点位地址")
    private String pointAddress;

    @Schema(description = "经度")
    private BigDecimal longitude;

    @Schema(description = "纬度")
    private BigDecimal latitude;

    @Schema(description = "状态：启用/停用", example = "2")
    private String status;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}