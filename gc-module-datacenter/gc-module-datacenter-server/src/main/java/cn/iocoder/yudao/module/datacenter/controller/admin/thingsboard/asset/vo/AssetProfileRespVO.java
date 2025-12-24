package cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.thingsboard.server.common.data.id.AssetProfileId;
import org.thingsboard.server.common.data.id.TenantId;

import java.util.Map;

@Schema(description = "管理后台 - 资产配置 Response VO")
@Data
public class AssetProfileRespVO {

    @Schema(description = "资产配置ID")
    private AssetProfileId id;

    @Schema(description = "创建时间")
    private Long createdTime;

    @Schema(description = "租户ID")
    private TenantId tenantId;

    @Schema(description = "资产配置名称")
    private String name;

    @Schema(description = "资产配置描述")
    private String description;

    @Schema(description = "是否默认配置")
    private Boolean isDefault;

    @Schema(description = "默认规则链ID")
    private String defaultRuleChainId;

    @Schema(description = "默认队列名称")
    private String defaultQueueName;

    @Schema(description = "附加信息")
    private Map<String, Object> additionalInfo;
}