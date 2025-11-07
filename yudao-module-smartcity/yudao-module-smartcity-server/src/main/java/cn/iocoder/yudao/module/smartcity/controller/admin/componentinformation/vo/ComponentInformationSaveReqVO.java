package cn.iocoder.yudao.module.smartcity.controller.admin.componentinformation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 部件信息新增/修改 Request VO")
@Data
public class ComponentInformationSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "27515")
    private Long id;

    @Schema(description = "部件编号")
    private String partNumber;

    @Schema(description = "部件名称", example = "李四")
    private String componentName;

    @Schema(description = "部件类型", example = "1")
    private String partType;

    @Schema(description = "所属区域")
    private String belongingRegion;

    @Schema(description = "安装位置")
    private String installationPosition;

    @Schema(description = "经纬度坐标")
    private String latitudeLongitude;

    @Schema(description = "建设日期")
    private String constructionDate;

    @Schema(description = "管理部门")
    private String administrativeDepartment;

    @Schema(description = "维护单位")
    private String maintenanceUnit;

    @Schema(description = "联系电话")
    private String contactNumber;

    @Schema(description = "部件状态", example = "2")
    private String componentStatus;

    @Schema(description = "使用寿命")
    private String serviceLife;

    @Schema(description = "关联事件记录")
    private String relatedEventRecords;

}