package cn.iocoder.yudao.module.smartcity.controller.admin.basicillegal.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 违建基本信息新增/修改 Request VO")
@Data
public class BasicIllegalSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "22624")
    private Long id;

    @Schema(description = "违建编号")
    private String buildingNumber;

    @Schema(description = "违建地址")
    private String buildingAddress;

    @Schema(description = "违建面积")
    private String constructionArea;

    @Schema(description = "违建结构类型")
    private String typesStructures;

    @Schema(description = "违建层数")
    private String numberFloors;

    @Schema(description = "违建用途")
    private String unauthorizedBuildings;

}