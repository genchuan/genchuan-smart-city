package cn.iocoder.yudao.module.data.controller.admin.code.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 地理编码新增/修改 Request VO")
@Data
public class GeoCodeSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "地理编码")
    private String code;

    @Schema(description = "地点名称")
    private String locationName;

    @Schema(description = "关联行政区划代码")
    private String areaCode;

    @Schema(description = "关联图层类型ID")
    private String layerTypeId;

    @Schema(description = "北斗网格码（6-8级）")
    private String beidouGridCode;

    @Schema(description = "经度（2000国家大地坐标系，精度6位小数）")
    private BigDecimal longitude;

    @Schema(description = "纬度（2000国家大地坐标系，精度6位小数）")
    private BigDecimal latitude;

    @Schema(description = "行政区划代码")
    private String adminCode;

    @Schema(description = "15位标识码（6位行政码+3位街道码+1位图层码+5位顺序码）")
    private String uniqueCode;

    @Schema(description = "关联状态ID")
    private String statusId;

    @Schema(description = "关联检查结果ID")
    private String checkResultId;

    @Schema(description = "编码规则启用状态（布尔值）")
    private Boolean ruleEnableFlag;

    @Schema(description = "编码规则审核状态ID")
    private String ruleAuditStatusId;

    @Schema(description = "父级地理编码ID")
    private String parentGeoCodeId;

    @Schema(description = "变更日志（JSON格式）")
    private String changeLog;

    @Schema(description = "坐标校验标识（布尔值）")
    private Boolean coordVerifyFlag;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

    @Schema(description = "通用扩展字段5")
    private String extCommon5;

    @Schema(description = "通用扩展字段6")
    private String extCommon6;

}