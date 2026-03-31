package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "窨井盖监测配置编辑 Response VO")
public class ManholeCoverConfigEditRespVO {

    @Schema(description = "配置主键", example = "21008")
    private Long configId;

    @Schema(description = "窨井盖唯一ID", example = "c1d2e3f4-g5h6-7890-cdef-0123456789ab")
    private String coverId;

    @Schema(description = "租户ID", example = "1")
    private String tenantId;

    @Schema(description = "区块链存证哈希", example = "0x123456")
    private String chainHash;
}