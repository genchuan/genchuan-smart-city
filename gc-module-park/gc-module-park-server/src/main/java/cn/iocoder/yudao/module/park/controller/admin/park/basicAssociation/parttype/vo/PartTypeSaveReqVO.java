package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.parttype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 管理部件类别新增/修改 Request VO")
@Data
public class PartTypeSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9865")
    private Long id;

    @Schema(description = "上级部件类别ID", example = "22716")
    private Long parentTypeId;

    @Schema(description = "唯一类别编码")
    private String typeCode;

    @Schema(description = "类别名称", example = "芋艿")
    private String typeName;

    @Schema(description = "类别描述")
    private String typeDesc;

    @Schema(description = "所属业务域：基础关联域/停车资源域/设备运维域")
    private String bizDomain;

    @Schema(description = "状态：启用/停用", example = "1")
    private String typeStatus;

    @Schema(description = "业务备注", example = "随便")
    private String typeRemark;

}
