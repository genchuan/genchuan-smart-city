package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.itemtype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 管理事项类别新增/修改 Request VO")
@Data
public class ItemTypeSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28168")
    private Long id;

    @Schema(description = "上级事项类别ID", example = "18680")
    private Long parentTypeId;

    @Schema(description = "唯一事项编码")
    private String itemCode;

    @Schema(description = "事项名称", example = "赵六")
    private String itemName;

    @Schema(description = "所属业务域")
    private String bizDomain;

    @Schema(description = "处理流程配置")
    private String processConfig;

    @Schema(description = "可处理角色ID列表")
    private String handleRoleIds;

    @Schema(description = "状态：启用/停用", example = "2")
    private String itemStatus;

    @Schema(description = "业务备注", example = "你猜")
    private String itemRemark;

}
